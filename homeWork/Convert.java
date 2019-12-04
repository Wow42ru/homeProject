
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Convert {
    private String  binNum="";
    private String  hexNum="";
         void hello() throws IOException {
             System.out.println("Введите число");
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             int a = Integer.parseInt(reader.readLine());
             System.out.println("Для перевода в двоичную систему счисления введите цифру 2, для перевода в шестнадцатиричную введите 16");
             switch (Integer.parseInt(reader.readLine())) {
                 case 2:
                     toBin(a);
                     break;
                 case 16:to16(a);
                     break;

                 default:
                     System.out.println("введите корректное значение");
                     hello();
             }
         }

        void toBin(int a2){
            int c; // остаток
            while (a2!=0){
                c= a2%2;
                a2/=2;
                binNum= c+binNum;
            }
    a2=Integer.parseInt(binNum);
            System.out.println(a2);
        }
    void to16 (int a2){
        String c = ""; // остаток
        while (a2!=0){
            c=Integer.toString(a2%16);
            if (Integer.parseInt(c)>9){
            switch (a2%16) {
                case 10: c="A";
                break;
                case 11:c="B";
                    break;
                case 12:c="C";
                    break;
                case 13:c="D";
                    break;
                case 14:c="E";
                    break;
                case 15:c="F";
            }
            }
            a2/=16;
            hexNum= c+hexNum;
            }
        System.out.println(hexNum);
        }

    }
        // 1. Написать программу перевода числа из 10 системы счисления
        // в 2 систему счисления

        // 2. Написать программу перевода числа из 10 системы счисления
        // в 16 систему счисления

class TestDriveConvert {
    public static void main(String[] args) throws IOException {
        Convert convert = new Convert();
        convert.hello();
    }
}
