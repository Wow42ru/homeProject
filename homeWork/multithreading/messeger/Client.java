package multithreading.messeger;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private static final int PORT = 80;// порт сервера
    private static final String HOST = "localhost";
    private Scanner scanner = new Scanner(System.in);
    private String name;
    private Socket socket;
    private int id; // выдаёт сервер


    public Socket getSocket() {
        return socket;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Client(String name, Socket socket) {
        this.socket = socket;
        this.name = name;
        id = -1; //заглушка, на всякий случай
    }

    private Message createMessage() {
        String s = scanner.nextLine();
        int pairId = -1;// заглушка ( id сервера)
        if (s.matches("/w\\d\\s.+")) {
            Matcher matcher = Pattern.compile("/w\\d+").matcher(s);
            matcher.find();
            int start = matcher.start() + 2;
            int end = matcher.end();
            pairId = Integer.parseInt(s.substring(start, end));
            s = s.replaceAll("/w\\d+", "");
        }
        return new Message(name, s, id, pairId);
    }

    private void sentMessage(ObjectOutputStream out, String name) throws IOException {
        out.writeObject(createMessage());//  Считывает сообщения с консоли и отправляет на сервер
        out.flush();
    }

    public static void main(String[] args) {

        try {

            Socket socket = new Socket(HOST, PORT);//Создаём соединение
            Client client = new Client(" User1", socket);// Создаём клиента
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            Thread readerThread = new Thread(new ReaderThread(client));
            readerThread.start();


            System.out.println("Чат запущен");
            System.out.println("Для отправки личного сообщения перед текстом сообшения введите /w и id собеседника, без пробела (/w1234) ");
            while (true)
                client.sentMessage(out, client.name);// вызываем метод отправки сообщений // TODO: 13.12.2019 доделать выход

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
            client.setId((requestId(input)));// запращивает и устанавливает id
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int requestId(ObjectInputStream input) throws IOException {
        return input.readInt();// При новом соединении ServerPrivateMessage отправляет id первым  сообщением
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




