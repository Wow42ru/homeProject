package Lesson14HomeWork.TryToUnderstand;

import java.io.*;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        File file = new File("C:\\Test\\test1.txt");    // Путь к файлу(обьект - ссылка(адрес) файла
        System.out.println(file.exists()); // проверка на существование
        System.out.println(file.isDirectory());// проверка на ссылку на папку
        System.out.println(file.isFile());// это файл?
        System.out.println(file.canRead());//проверка
        System.out.println(file.canWrite());//проверка
        try {
            System.out.println(file.createNewFile() + " Новый файл");// записали новый файл(boolean)
        } catch (IOException e) {
            e.printStackTrace();
        }
        File[] files = file.listFiles();// Массив файлов, ничего необычного
        System.out.println(file.lastModified());// время от 1970 до последнего изменения
        try {
            //  writeToFile(file, true);// append - отвечает за запись(тру) или перезапись (фолс)
            //     readByteArray(file);
            //  writeWithBuffer(file);
              writeReadData(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(File file, boolean append) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(file, append)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append("Line ").append(i).append("\n");
            }
            byte[] bytes = sb.toString().getBytes();// передаём строку в массив чаров(байтовый)
            fileOutput.write(bytes);// записываем файловый поток (существующие данные стираются при false )
        }
    }

    public static String readByteArray(File file) throws IOException {
        String string = null;

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ) {
            System.out.println(fileInputStream.available());// проверкка, сколько байт будет загруженно

            byte[] buf = new byte[10];
            int data;

            while ((data = fileInputStream.read(buf)) > 0) {// читаем по [] символов, в конце выдаёт-1
                System.out.println(data);
                System.out.println(Arrays.toString(buf));
                outputStream.write(buf, 0, data);// что, сдвиг, номера //куда?что / что это, зачем? Вместо буфера?
            }
            string = new String(outputStream.toByteArray());
        }

        return string;
    }

    public static void writeWithBuffer(File file) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);//поток выдачи байт
             BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream) //буффер(накопитель)
        ) {
            for (int i = 0; i < 1_000_000; i++) {// копит, и записывает
                outputStream.write((i + " ").getBytes());// нужен перевод стринга в байт
            }

        }

    }

    public static String readFromSeveralFiles(File... files) throws IOException {
        String string = null;

        try (
                FileInputStream input1 = new FileInputStream(files[0]);
                FileInputStream input2 = new FileInputStream(files[1]);
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ) {
//            InputStream stream1 = new FileInputStream("file1.txt");
//            InputStream stream2 = new FileInputStream("file2.txt");
//            InputStream stream3 = new FileInputStream("file3.txt");
//            InputStream stream4 = new FileInputStream("file4.txt");
//
//            Vector<InputStream> sequence = new Vector<>();
//            sequence.add(stream1);
//            sequence.add(stream2);
//            sequence.add(stream3);
//            sequence.add(stream4);
//            SequenceInputStream sequenceInputStream =
//                    new SequenceInputStream(sequence.elements());

            SequenceInputStream sequenceInputStream =
                    new SequenceInputStream(input1, input2);// можно передать массив (см. выше)

            byte[] buf = new byte[10];
            int data;
            while ((data = sequenceInputStream.read(buf)) > 0) {
                bout.write(buf, 0, data);
            }

            string = new String(bout.toByteArray());
        }
        return string;
    }


    public static void writeReadData(File file) throws IOException {// для записи примитивов
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             DataOutputStream dataOutput = new DataOutputStream(fileOutputStream);
        ) {
            dataOutput.writeDouble(4.6);
            dataOutput.writeFloat(5.9f);
            dataOutput.writeUTF("hello");
            dataOutput.writeDouble(4.1);
            dataOutput.writeDouble(2.6);
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             DataInputStream dataInput = new DataInputStream(fileInputStream);
        ) {
            System.out.println(dataInput.readDouble());
            System.out.println(dataInput.readFloat());
            System.out.println(dataInput.readUTF());
            System.out.println(dataInput.readDouble());
            System.out.println(dataInput.readDouble());


//            RandomAccessFile
//            XOR ^

            //
        }
    }
}










