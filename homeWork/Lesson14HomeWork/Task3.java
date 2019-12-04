package Lesson14HomeWork;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

///Написать свои реализации InputStream и OutputStream, которые будут расширять FilterinputStream и FilterOutputStream,
//в переопределяемых методах (write и read) необходимо шифровать и дешифровать данные (использовать xor).
public class Task3 {
    public static void main(String[] args) {
        int c = 123454;
        String s = String.valueOf(c);
        byte[] bytes = s.getBytes();
        File file = new File("C:\\Test\\2.txt");

        try {
            MyOutputStream myOutputStream = new MyOutputStream(file);
            myOutputStream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            MyInputStream inputStream = new MyInputStream(new FileInputStream(file));
            System.out.println(inputStream.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyInputStream extends FilterInputStream {

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    public MyInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read(byte[] b) throws IOException {
      //  return super.read(b);
        ArrayList<Integer> d= new ArrayList<> (super.read(b));
        System.out.println(d);
        return 0;
    }

    @Override
    public int read() throws IOException {
        ArrayList<Integer> d= new ArrayList<> (super.read());
        System.out.println(d);
        return 0;
    }
}

class MyOutputStream extends FileOutputStream {
    public MyOutputStream(String name) throws FileNotFoundException {
        super(name);
    }

    public MyOutputStream(String name, boolean append) throws FileNotFoundException {
        super(name, append);
    }

    public MyOutputStream(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public void write(byte[] b) throws IOException {
        String key = "Проверка";
        byte[] keyBytes=key.getBytes();
        for (int i = 0; i < b.length; i++) {
            b[i]= (byte)( b[i]^keyBytes[i]);
        }
        System.out.println(b +"     22222");
        super.write(b);

    }



    public MyOutputStream(File file, boolean append) throws FileNotFoundException {
        super(file, append);
    }
}
