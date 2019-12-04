package Lesson14HomeWork;

import sun.text.normalizer.UTF16;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//Разбить файл (информацию из одного файла записать в 2 разных файла).
// Склеить файл (информацию из нескольких файлов записать в один файл)
public class Task2 {
    public static ArrayList<String> readFile(File file) throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        String string = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((string = bufferedReader.readLine()) != null) {
                strings.add(string + "\n");
            }
            return strings;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception();
    }

    public static void readOneWriteTwo(File file1, File file2, File file3) throws Exception {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
             BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(file3))) {
            ArrayList<String> strings = readFile(file1);
            int a = strings.size() / 2;
            int b = strings.size() - a;
            for (int i = 0; i < a; i++) {
                bufferedWriter.write(strings.get(i));
            }
            for (int i = 0; i < b; i++) {
                bufferedWriter1.write(strings.get(i + a));
            }
        }
    }

    public static void readTwoWriteOne(File file1, File file2, File file3) throws Exception {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file3))) {
            ArrayList<String> strings = new ArrayList<>();
            strings.addAll(readFile(file1));
            strings.addAll((readFile(file2)));
            for (int i = 0; i < strings.size(); i++) {
                bufferedWriter.write(strings.get(i));
            }
        }
    }

    public static void writeSomeLines(File file, String lines, int number) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < number; i++) {
                bufferedWriter.write(lines + " " + i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file1 = new File("C:\\Test\\test3.txt");
        File file2 = new File("C:\\Test\\test4.txt");
        File file3 = new File("C:\\Test\\test5.txt");
        File file4 = new File("C:\\Test\\test6.txt");
        // Task2.writeSomeLines(file1, "Это строка номер:", 10);
        try {
            Task2.readOneWriteTwo(file1, file2, file3);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            readTwoWriteOne(file2, file3, file4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
