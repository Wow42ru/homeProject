package multithreading;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*Корабли - потоки:
Корабли заходят в порт для загрузки/разгрузки.
Работает несколько причалов(4)
У одного причала может стоять один корабль*/
// через семафор, в данном случае == boolean
public class PortVersion2 {
    private Semaphore[] pier;

    public static void main(String[] args) {
        PortVersion2 portVersion2 = new PortVersion2();
        portVersion2.pier = new Semaphore[4];
        for (int i = 0; i < portVersion2.pier.length; i++) {
            portVersion2.pier[i] = new Semaphore(1);
        }

        for (int i = 0; i < 100; i++) {
            new Thread(new ShipThread2(portVersion2.pier)).start();
        }
    }
}

class ShipThread2 implements Runnable {
    private Semaphore[] semaphores;
    private Random random;

    public ShipThread2(Semaphore[] semaphores) {
        this.semaphores = semaphores;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < semaphores.length; i++) {
                try {
                    if (semaphores[i].tryAcquire(50, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + " занял пристань " + i);
                        Thread.sleep(random.nextInt(1000));
                        System.out.println(Thread.currentThread().getName() + " освободил пристань " + i);
                        semaphores[i].release();
                        Thread.sleep(random.nextInt(5000));
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}