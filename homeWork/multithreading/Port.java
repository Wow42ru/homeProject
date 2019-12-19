package multithreading;


import java.util.Random;
import java.util.concurrent.CountDownLatch;

/*Корабли - потоки:
Корабли заходят в порт для загрузки/разгрузки.
Работает несколько причалов(4)
У одного причала может стоять один корабль*/
public class Port {


    public static void main(String[] args) {
        CountDownLatch[] port = new CountDownLatch[4];
        for (int i = 0; i < 4; i++) {
            port[i] = new CountDownLatch(2);
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new ShipThread(port)).start();
        }
    }


}

class ShipThread extends Thread {
    CountDownLatch[] port;
    Random random = new Random();

    public ShipThread(CountDownLatch[] port) {
        this.port = port;
    }

    private void getPlace(CountDownLatch[] port, int a) {
        port[a].countDown();
        System.out.println("В порту " + a + " " + currentThread().getName());
        try {
            sleep(random.nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ушли из порта " + a);
        port[a].countDown();
        port[a] = new CountDownLatch(2);

    }

    @Override
    public void run() {
        loop:
        while (true) {
            int a = random.nextInt(4);
            if (port[a].getCount() != 2) {
                for (int i = 0; i < 3; i++) {
                    if (port[i].getCount() == 2) {
                        getPlace(port, i);
                        continue loop;
                    }
                }
                try {
                   port[a].await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            else {
                getPlace(port, a);
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}