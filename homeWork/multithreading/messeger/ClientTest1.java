package multithreading.messeger;

import multithreading.MySocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest1 {
    private static final int PORT = 80;// порт сервера
    private static final String HOST = "localhost";
    private Scanner scanner = new Scanner(System.in);
    private String name;
    private Socket socket;
    static private int counter=1;
    private int id;
    private ClientTest1(String name, MySocket socket) {
        this.socket = socket;
        this.name = name;
        id= counter;
        counter++;
    }

    private void sentMessage(ObjectOutputStream out, String name, Socket socket) throws IOException {
        System.out.println("Можно писать сообщение");
        out.writeObject(new Message(name, scanner.nextLine(),id));//  Считывает сообщения с консоли и отправляет на сервер
        out.flush();
    }

    public static void main(String[] args) {

        ClientTest1 client = null;
        MySocket socket = null;

        try {

            socket = new MySocket(HOST, PORT);//Создаём соединение
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            new Thread(new ReaderThread1(socket)).start();
            client = new ClientTest1("Test User", socket);// Создаём клиента
            try {
                while (true)
                    client.sentMessage(out, client.name,socket);// вызываем метод отправки сообщений // TODO: 13.12.2019 доделать выход
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReaderThread1 implements Runnable {
    private ObjectInputStream input = null;
    private Socket socket;


    ReaderThread1(Socket socket) {
        this.socket = socket;

        //поймал дедлок, решал проблему (нельзя открывать читающие потоки с двух сторон одновременно)
    }

    @Override
    public void run() {
        try {
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message message = null;
        try {
            while (true) {
                message = (Message) input.readObject();
                if (message == null)
                    continue;
                System.out.println("Сообщение принято!" + message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}




