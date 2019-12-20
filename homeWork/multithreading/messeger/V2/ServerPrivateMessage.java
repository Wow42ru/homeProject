package multithreading.messeger.V2;


import multithreading.messeger.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ServerPrivateMessage {
    private static LinkedBlockingDeque<Message> messages = new LinkedBlockingDeque();// хранит сообщения
    private static ConcurrentHashMap<Integer, Socket> clientSockets = new ConcurrentHashMap<>();// хранит id адреса клиентов
    private static ArrayList<Integer> privateTalk;// TODO: 19.12.2019 добавить семофор в метод // хранит приватные диалоги (клиентов)
    private static int counter = 0;// для назначения id

    public static int getPair(int id) {
        try {
            if (id % 2 == 0)
                return privateTalk.get(id + 1);
            else return privateTalk.get(id - 1);
        } catch (NullPointerException e) {
        }
        return -1;// на случай отсутствия пары
    }

    public static void addPair(int a, int b) {
        if (a < b) {// упорядочиваем по id
            privateTalk.add(a);
            privateTalk.add(b);
        } else {
            privateTalk.add(b);
            privateTalk.add(a);
        }
    }

    public static void deletePair(int id) {
        int a = -1;// для проверки
        for (int i = 0; i < privateTalk.size(); i++) {
            if (privateTalk.get(i).equals(id)) {
                a = i;
                break;
            }

        }
        if (a % 2 == 0) {
            privateTalk.remove(a + 1); // чётный всегда меньше
            privateTalk.remove(a);
        }
        if (a % 2 == 1) {
            privateTalk.remove(a);
            privateTalk.remove(a);
        } else System.out.println("Пары не существует");
    }


    public static int getId() {
        return counter++;// генерируем новый id
    }

    public static LinkedBlockingDeque<Message> getMessages() {
        return messages;
    }

    public static ConcurrentHashMap<Integer, Socket> getClientSockets() {
        return clientSockets;
    }

    public static void addMessage(Message message) {// добавление сообщения
        System.out.println("Сообщение добавленно :" + ServerPrivateMessage.messages.add(message));
    }

    public static void addClient(int id, Socket socket) {// добавляет адрес клиента
        ServerPrivateMessage.clientSockets.put(id, socket);
        //добавляем сокет для создания выход. потока
        // (была проблема с созданиями новых потоков при отправке нового сообщения StreamCorruptedException: invalid type code: AC)
    }

    public static void removeClient(int id) {// удаляет адрес клиента
        ServerPrivateMessage.clientSockets.remove(id);
        WriterThreadServer2.removeClient(id);
    }

    private void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);//создаём сокет сервера
        WriterThreadServer2 writer = new WriterThreadServer2();
        writer.start();//запускаем поток вывода
        System.out.println("Сервер запущен, ожидает клиентов");

        while (true) {
            Socket clientSocket = serverSocket.accept();// устанавливает соединение (воссоздавая клиентский сокет)
            WriterThreadServer2.addClient(clientSocket);// регистрируем нового клиента
            System.out.println("Новый пользователь в чате! (Пока безымяный)");
            new Thread(new ReaderThreadServer2(clientSocket)).start();//запускает поток чтения
        }
    }

    public static void main(String[] args) {

        ServerPrivateMessage server = new ServerPrivateMessage();
        try {
            server.startServer();// Стартуем!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


class ReaderThreadServer2 implements Runnable {
    private ObjectInputStream in;
    private Socket socket;
    private int id;
    private String name;

    public ReaderThreadServer2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        boolean newClient = true;
        boolean work = true;
        Message inputMessage;
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (work) {
                inputMessage = (Message) in.readObject();
                if (newClient) {
                    id = inputMessage.getId();
                    name = inputMessage.getClient();
                    newClient = false;
                }
                if ("".equals(inputMessage.getText()) || inputMessage.getText() == null)// не пропускаем пустые сообщения
                    continue;
                if (!ServerPrivateMessage.getClientSockets().containsKey(inputMessage.getId())) {// не пропускает незарегистрированных пользователей
                    continue;
                }
                ServerPrivateMessage.addMessage(inputMessage);//добавляем в очередь
                System.out.println(inputMessage);
            }
        } catch (Exception e) {// ловим socketException
            System.out.println(name + " покинул чат");
            ServerPrivateMessage.removeClient(id);
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            work = false;// при ошибке соединения удаляет пользователя и  поток прекращает работу. Не понимаю, почему work подчеркнут

        }
    }
}

class WriterThreadServer2 extends Thread {
    static private HashMap<Integer, ObjectOutputStream> objectOutputStreams = new HashMap<>();// хранит id + исходящий поток


    static public void addClient(Socket socket) throws IOException {
        ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
        int id = ServerPrivateMessage.getId();
        ous.writeInt(id);// отправляем id при подключении нового пользователя
        ous.flush();
        objectOutputStreams.put(id, ous);// регистрируем нового получателя
        ServerPrivateMessage.addClient(id, socket);
    }


    static public void removeClient(int id) {// удаляем получателя
        objectOutputStreams.remove(id);
    }

    private int getPair(int id) throws NullPointerException {
        int a = ServerPrivateMessage.getPair(id);
        if (a == -1) {
            System.out.println("Приватного диалога не существует");
            throw new NullPointerException();
        }
        return a;
    }

    private void sendMessage() throws InterruptedException, IOException {
        final Message message = ServerPrivateMessage.getMessages().takeFirst();
        if (message.getPrivatePair() != -1) {
            int pairId = getPair(message.getPrivatePair());
            objectOutputStreams.get(pairId).writeObject(message);
        } else
            objectOutputStreams.entrySet().stream()
                    .filter(s -> !s.getKey().equals(message.getId()))// убираем отправителя из получателей
                    .forEach(s -> {
                        try {
                            s.getValue().writeObject(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
    }

    @Override
    public void run() {

        System.out.println("Writer запущен");
        while (true) {
            if (ServerPrivateMessage.getMessages().size() > 0) {
                try {
                    sendMessage();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}