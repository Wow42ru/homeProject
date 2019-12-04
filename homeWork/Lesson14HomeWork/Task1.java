package Lesson14HomeWork;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//Самостоятельно разобрать RandomAccessFile
public class Task1 {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Test\\1.txt","r");
            System.out.println(randomAccessFile.length());
            randomAccessFile.seek(2);
            System.out.println(randomAccessFile.getFilePointer());
            System.out.println(randomAccessFile.readLine());
            System.out.println(randomAccessFile.getFilePointer());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
