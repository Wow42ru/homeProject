package multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Top100 {
    Map<String, Integer> countingMap = new HashMap<>();

    public void showTop100Words() {
        System.out.println("Самые часто встречающиеся слова в тексте:");
        countingMap.entrySet()
                .stream()
                .sorted((s1, s2) -> s2.getValue().compareTo(s1.getValue()))//сортируем в обратном порядке
                .limit(100)
                .filter(p -> p.getKey().matches("[a-zA-Z]+"))//ловлю пустые строки, в потоке не смог
                .forEach(System.out::println);
    }
}


class Top100Test {
    public static void main(String[] args) {
        ClassLoader loader = Top100Test.class.getClassLoader();
        List<String> stringsArray = null;
        File file = new File(loader.getResource("wp.txt").getFile());
        try {
            stringsArray = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Top100 top100 = new Top100();
        ArrayList<Thread> threads = new ArrayList<>();

        // делим массив на части (кол-во частей зависит от кол-ва ядер процессора
        int a = 0;//


        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threads.add(new Thread(new CountingThread(top100,
                    stringsArray.subList(a,
                            Math.min(stringsArray.size(), (a = a + 1 + (stringsArray.size() / Runtime.getRuntime().availableProcessors())))))));


        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        top100.showTop100Words();
    }
}


class CountingThread implements Runnable {
    private Top100 top100;
    private List<String> partStringList;

    public CountingThread(Top100 top100, List<String> partStringList) {
        this.top100 = top100;
        this.partStringList = partStringList;
    }

    @Override
    public void run() {
        Map<String, Integer> TempCountingMap = new HashMap<>();
        for (String strings : partStringList) {
            for (String s : strings.split("[\\W\\s&&[^']]")) {
                TempCountingMap.merge(s.toLowerCase(), 1, Integer::sum);
            }
        }
        synchronized (top100.countingMap) {
            TempCountingMap.forEach((key, value) -> top100.countingMap.merge(key, value, Integer::sum));

        }
    }
}