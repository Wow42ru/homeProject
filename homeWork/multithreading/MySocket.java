package multithreading;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocket extends Socket {
    private int id;
    private static int counterId;
    public MySocket(String host, int port) throws IOException {
        super(host, port);
        id = counterId;
        counterId++;
    }

    public void setId(int id){this.id=id;}
    public int getId(){return this.id;}

}
