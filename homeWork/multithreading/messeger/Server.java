package multithreading.messeger;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class Server {
    private static LinkedBlockingDeque<Message> messages = new LinkedBlockingDeque();// хранит сообщения
    private static ConcurrentHashMap<Integer, Socket> clientSockets = new ConcurrentHashMap<>();// хранит id адреса клиентов


    public static LinkedBlockingDeque<Message> getMessages() {
        return messages;
    }

    public static ConcurrentHashMap<Integer, Socket> getClientSockets() {
        return clientSockets;
    }

    public static void addMessage(Message message) {// добавление сообщения
        System.out.println("Сообщение добавленно" + Server.messages.add(message));//// TODO: 14.12.2019
    }

    public static void addClient(int id, Socket socket) throws IOException {// добавляет адрес клиента
        Server.clientSockets.put(id, socket);
        WriterThreadServer.addClient(id, socket);
        //добавляем сокет для создания выход. потока (была проблема с созданиями новых потоков при отправке нового сообщения StreamCorruptedException: invalid type code: AC)
    }

    public static void removeClient(int id) {// удаляет адрес клиента
        Server.clientSockets.remove(id);
        WriterThreadServer.removeClient(id);
    }

    private void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);//создаём сокет сервера
        WriterThreadServer writer = new WriterThreadServer();
        writer.start();//запускаем поток вывода
        System.out.println("Сервер запущен, ожидает клиентов");

        while (true) {
            Socket clientSocket = serverSocket.accept();// устанавливает соединение (воссоздавая клиентский сокет)
            System.out.println("Новый пользователь в чате! (Пока безымяный)");
            new Thread(new ReaderThreadServer(clientSocket)).start();//запускает поток чтения
        }
    }

    public static void main(String[] args) {

        Server server = new Server();
        try {
            server.startServer();// Стартуем!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


class ReaderThreadServer implements Runnable {
    private ObjectInputStream in;
    private Socket socket;
    private int id;
    private String name;

    public ReaderThreadServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Message inputMessage = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                inputMessage = (Message) in.readObject();
                if (inputMessage == null)// не пропускаем пустые сообщения
                    continue;
                if (!Server.getClientSockets().containsKey(inputMessage.getId())) {// регистрируем новых пользователей
                    Server.addClient(inputMessage.getId(), socket);
                    id = inputMessage.getId();
                    name = inputMessage.getClient();
                }
                Server.addMessage(inputMessage);
                System.out.println(inputMessage);
            }
        } catch (Exception e) {// ловим socketException
            System.out.println(name + " покинул чат");
            Server.removeClient(id);
            //Server.getClientSockets().entrySet().forEach(System.out::println);
        }
    }
}

class WriterThreadServer extends Thread {
    static private HashMap<Integer, ObjectOutputStream> objectOutputStreams = new HashMap<>();// хранит id + исходящий поток

    static public void addClient(int id, Socket socket) throws IOException {
        objectOutputStreams.put(id, new ObjectOutputStream(socket.getOutputStream()));// регистрируем нового получателя
    }

    static public void removeClient(int id) {// удаляем получателя
        objectOutputStreams.remove(id);
    }

    @Override
    public void run() {

        System.out.println("Writer запущен");
        while (true) {

            if (Server.getMessages().size() > 0) {
                try {
                    final Message message = Server.getMessages().takeFirst();
                    System.out.println("message::  " + message + "  " + message.getId());//// TODO: 14.12.2019
                    System.out.println("Deque " + Server.getMessages());//// TODO: 14.12.2019
                    objectOutputStreams.entrySet().stream().peek(s ->
                            System.out.println("Do filtra "+s+" "+s.getKey())
                    )
                            .filter(s -> !s.getKey().equals(message.getId()))// убираем отправителя из получателей
                            .peek(s ->
                                    System.out.println("Posle filtra "+s+" "+s.getKey())
                            ) .forEach(s -> {
                                try {
                                    s.getValue().writeObject(message);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                    objectOutputStreams.entrySet().stream()
                            .filter(s -> s.getKey() != message.getId())// убираем отправителя из получателей
                            .forEach(System.out::println);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}