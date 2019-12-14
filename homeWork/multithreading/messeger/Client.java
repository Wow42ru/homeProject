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
    static private int counter;
private int id;//плохое рещение, лучше пока не придумал
    private Client(String name, MySocket socket) {
        this.socket = socket;
        this.name = name;
        id= counter;
        counter++;
    }

    private void sentMessage(ObjectOutputStream out, String name,Socket socket) throws IOException {
        out.writeObject(new Message(name, scanner.nextLine(),id));//  Считывает сообщения с консоли и отправляет на сервер
        out.flush();
    }

    public static void main(String[] args) {

        Client client = null;
        MySocket socket = null;

        try {

            socket = new MySocket(HOST, PORT);//Создаём соединение
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            new Thread(new ReaderThread(socket)).start();
            client = new Client(" User1", socket);// Создаём клиента
            try {
                System.out.println("Соединение запушенно, для Старта нажмите Enter");
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

class ReaderThread implements Runnable {
    private ObjectInputStream input = null;
    private Socket socket;


    ReaderThread(Socket socket) {
        this.socket = socket;
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
                System.out.println(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}




