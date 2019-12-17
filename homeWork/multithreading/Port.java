package multithreading;


import java.util.Random;
import java.util.concurrent.CountDownLatch;

/*Корабли - потоки:
Корабли заходят в порт для загрузки/разгрузки.
Работает несколько причалов(4)
У одного причала может стоять один корабль*/
public class Port {

    public static CountDownLatch clear = new CountDownLatch(1);

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

    @Override
    public void run() {
       loop: while (true) {
            int a = random.nextInt(4);
            ifLoop: if (port[a].getCount() < 2) {
                for (int i = 0; i < 4; i++) {
                    if (port[i].getCount() == 2) {
                        a = i;//пытаюсь решить проблемму с пустующими пристанями.
                        break ifLoop;// осталась проблема с
                    }
                }
                try {
                    Port.clear.await();// блокирую по общей переменной (для всех)
                    continue loop;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            port[a].countDown();//заблокировали порт (условие в ifLoop)
            System.out.println("В порту " + a);
            try {
                sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            port[a].countDown();
            Port.clear.countDown();
            //пытаюсь обновить всё
            System.out.println("Ушли из порта " + a);
            port[a] = new CountDownLatch(2);
           Port.clear = new CountDownLatch(1);//Обновляю общий "стоппер"
        }
    }
}