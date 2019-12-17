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

    private ClientTest1(String name, Socket socket) {
        this.socket = socket;
        this.name = name;
        id=-1;
    }

    private void sentMessage(ObjectOutputStream out, String name, Socket socket) throws IOException {
        out.writeObject(new Message(name, scanner.nextLine(), id));//  Считывает сообщения с консоли и отправляет на сервер
        out.flush();
    }

    public static void main(String[] args) {

        try {

            Socket socket= new Socket(HOST, PORT);//Создаём соединение
            ClientTest1 client = new ClientTest1(" User1", socket);// Создаём клиента
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            Thread readerThread1 = new Thread(new ReaderThread1(client));
            readerThread1.start();

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

class ReaderThread1 implements Runnable {
    private ObjectInputStream input = null;
    private Socket socket;

    ReaderThread1(ClientTest1 client) {
        this.socket = client.getSocket();
        try {
            input = new ObjectInputStream(socket.getInputStream());
            client.setId((requestId(client, input)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int requestId(ClientTest1 client, ObjectInputStream input) throws IOException {
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




