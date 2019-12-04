import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WarandPieceHomeWork {
    private String[] alfabet = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private String allTextInside;//массив одной строкой

    private HashMap<String, Integer> hashMap = new HashMap<>();// для сортировке по популярности <Слово, количесво встреч слова в тексте>

    private HashMap<Integer, ArrayList<String>> integerArrayListHashMap = new HashMap<>();//для сортировки по количеству букв в слове

    public void arrayToString(List<String> list) {
        // TODO: 15.11.2019 сделать проверку на полноту массива финал?
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s.replaceAll("\\s+", " "));
        }
        allTextInside = stringBuilder.toString();
    }

    public Set keys() {//для проверки
        return (integerArrayListHashMap.keySet());
    }

    public int howOftenContains(String string, List<String> strings) {//1 задание
        if (string == null) {// проверка на пустой запрос
            return 0;
        }
        String regex = string.trim();//иначе не смог вставить переменную в паттерн
        arrayToString(strings);//перевожу всё в 1 строку, чтобы искать словосочетания в предложениях, которые занимают больше 1 строки
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(allTextInside);
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        return counter;
    }

    public void print(ArrayList<String> arrayList) {
        if (arrayList == null)
            return;
        for (String s : arrayList) {
            System.out.println(s);
        }
    }

    public HashMap<Integer, ArrayList<String>> getIntegerArrayListHashMap() {
        return integerArrayListHashMap;
    }

    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    void addStringToGroup(String s) {// тут добавляю строки м хэшмапу по длине строк (2 задание)
        for (String a : s.split("\\s*(\\s|\\W)\\s*")) {//делю по словам
            getIntegerArrayListHashMap().putIfAbsent(a.length(), new ArrayList<String>());//создание арайлиста в ячейке мапы
            getIntegerArrayListHashMap().get(a.length()).add(a);//добавление слова
        }
    }

    void addStringToHowOftenWithout(List<String> strings) {//4 задание
        arrayToString(strings);//Опять массив->строка
        for (String a : allTextInside.split("\\s*(\\s|,|!|\\.|-)\\s*")) {
            if (a.equals("the") || a.equals("to") || a.equals("of") || a.equals("a") || a.equals("\"") || a.equals("not") || a.equals(""))
                continue;
            getHashMap().putIfAbsent(a, 0);
            getHashMap().put(a, getHashMap().get(a) + 1);//наполняю мапу <Слово, количество встреч >
        }
        addStringToHowOften();
    }

    void addStringToHowOftenFull(List<String> strings) {// 3 задание
        arrayToString(strings);//Опять массив->строка
        for (String a : allTextInside.split("\\s*(\\s|,|!|\\.|-)\\s*")) {
            getHashMap().putIfAbsent(a, 0);
            getHashMap().put(a, getHashMap().get(a) + 1);//наполняю мапу <Слово, количество встреч >
        }
        addStringToHowOften();
    }

    private void addStringToHowOften() {
        TreeMap<Integer, String> top10TreeMap = new TreeMap<>();
        for (int i = 0; i < 10; i++) {//Ищем 10 самых популярных слов
            String keyWithHighestVal = "";
            int maxValueInMap = (Collections.max(getHashMap().values()));
            for (Map.Entry<String, Integer> entry : getHashMap().entrySet()) {  // Iterate through hashmap
                if (entry.getValue() == maxValueInMap) {
                    keyWithHighestVal = entry.getKey();     // this is the key which has the max value
                }
            }
            top10TreeMap.put(maxValueInMap, keyWithHighestVal);
            getHashMap().put(keyWithHighestVal, 0);
        }
        System.out.println(top10TreeMap.entrySet());
        ArrayList<String> arrayList = new ArrayList<>(top10TreeMap.values());
        System.out.println(arrayList);

    }

    public void persentOfLetters(List<String> strings) {
        arrayToString(strings);//Опять массив->строка
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        for (String c : alfabet) {
            stringIntegerHashMap.put(c, 0);//Создаём ячейку с ключём-буквой
            Matcher matcher = Pattern.compile(c, Pattern.CASE_INSENSITIVE).matcher(allTextInside);// почему-то не работало с отдельным (до цикла) обьяевлением паттерна
            while (matcher.find()) {//искал только "а"
                stringIntegerHashMap.put(c, stringIntegerHashMap.get(c) + 1);//фиксируем находку
            }
        }
        System.out.println(stringIntegerHashMap);
        long summ = 0L;// инт слишком маленький
        for (int i : (stringIntegerHashMap.values())) {
            summ = summ + (long) i;
        }
        Double[] doubles = new Double[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            doubles[i] = ((double) stringIntegerHashMap.get(alfabet[i]) * 100 / summ);
        }
        for (int i = 0; i < alfabet.length; i++) {
            System.out.println(alfabet[i] + " " + doubles[i]);

        }
    }
}

class TaskTest {
    public static void main(String[] args) throws IOException {
       /* WarandPieceHomeWork task2Strings = new WarandPieceHomeWork();

        ClassLoader loader = TaskTest.class.getClassLoader();
        File file = new File(loader.getResource("wp.txt").getFile());
        List<String> strings = Files.readAllLines(file.toPath());

        //2 задание
        for (String s : strings) {
            task2Strings.addStringToGroup(s);
        }
        System.out.println(task2Strings.getIntegerArrayListHashMap().size());
        System.out.println(task2Strings.keys());
        task2Strings.print(task2Strings.getIntegerArrayListHashMap().get(17));
        //1 задание
        System.out.println(task2Strings.howOftenContains(null, strings));
        System.out.println(task2Strings.howOftenContains("Well  ", strings));
        // 3 задание
        task2Strings.addStringToHowOftenFull(strings);// для поиска фраз достаточно проверить все комбинации из топ 10, но что-то не пошло
        // 4 задание
        task2Strings.addStringToHowOftenWithout(strings);// для поиска фраз достаточно проверить все комбинации из топ 10, но что-то не пошло
        // 5 задание
        task2Strings.persentOfLetters(strings);*/
        /*File file1 = new File("C:\\Users\\wow42\\IdeaProjects\\homeProject\\Resource\\wp.txt")
        Map<String, Long> map = Files.lines(file1.toPath())
                .parallel()
                .map(line -> line.replaceAll("\\p{Punct}", " ").toLowerCase().trim())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .parallelStream()
                .sorted((Comparator.comparing((entry1,entry2)->entry2,
*/


    }
}