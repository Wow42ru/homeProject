package multithreading.messeger;

import multithreading.MySocket;

import java.io.*;
import java.net.Socket;


import java.util.Scanner;

public class Client {
    private static final int PORT = 80;// порт сервера
    private static final String HOST = "localhost";
    private Scanner scanner = new Scanner(System.in);
    private String name;
    private Socket socket;
    private int id;

    public Socket getSocket() {
        return socket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Client(String name, Socket socket) {
        this.socket = socket;
        this.name = name;
        id=-1;
    }

    private void sentMessage(ObjectOutputStream out, String name, Socket socket) throws IOException {
        out.writeObject(new Message(name, scanner.nextLine(), id));//  Считывает сообщения с консоли и отправляет на сервер
        out.flush();
    }

    public static void main(String[] args) {

        Client client = null;
        Socket socket = null;

        try {

            socket = new Socket(HOST, PORT);//Создаём соединение
            client = new Client(" User1", socket);// Создаём клиента
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Thread readerThread = new Thread(new ReaderThread(client));

            readerThread.start();
            try {
                System.out.println("Соединение запушенно, для Старта нажмите Enter");
                while (true)
                    client.sentMessage(out, client.name, socket);// вызываем метод отправки сообщений // TODO: 13.12.2019 доделать выход
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReaderThread implements Runnable {
    private ObjectInputStream input = null;
    private Socket socket;


    ReaderThread(Client client) {
        this.socket = client.getSocket();
        try {
            input = new ObjectInputStream(socket.getInputStream());
            client.setId((requestId(client, input)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int requestId(Client client, ObjectInputStream input) throws IOException {
        return input.readInt();
    }

    @Override
    public void run() {
        Message message = null;
        try {
            while (true) {
                message = (Message) input.readObject();
                if (message == null)
                    continue;
                System.out.println(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}




