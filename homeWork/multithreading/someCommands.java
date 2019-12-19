package multithreading;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;

public class someCommands {
    public static void main(String[] args) {
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду. ");
        while (true) {
            String s = scanner.nextLine();
            if ("help".equals(s))
                fixedPool.execute(new Help());
            if ("time".equals(s))
                fixedPool.execute(new TimeThread());
            if ("getFromFile".equals(s))
                fixedPool.execute(new FromFileThread());
            if ("exit".equals(s)) {
                fixedPool.shutdown();
                break;
            }
        }
    }
}

class TimeThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalTime.now());
    }
}

class FromFileThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Read");

    }
}

class Help implements Runnable {

    @Override
    public void run() {
        System.out.println("Доступные комады : \nhelp\ntime\ngetFromFile");
    }
}