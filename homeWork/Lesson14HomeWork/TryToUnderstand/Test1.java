package Lesson14HomeWork.TryToUnderstand;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) {
        String str =  "second test write";
        FileWriter fr=null;
        try {
             fr = new FileWriter("C:\\Test\\1.txt");
       //     FileOutputStream     fileOutputStream = new FileOutputStream("C:\\Test\\1.txt");
            fr.write(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
