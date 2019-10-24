package homeProject.homeWork;
/*Дан массив целых чисел.
        Массив не отсортирован, числа могут повторяться.
        Необходимо найти в данном массиве такие два числа n и m,
        чтобы их сумма была равна 7.
        Например, 2 + 5 = 7, 6 + 1 = 7, -2 + 9 = 7
        * */


import java.util.Arrays;

public class FindDelta7 {
    int[] array;
    void CreateArray(){
        double random;
         array = new int[(int) (Math.random() * 1000) + 20];// Создание массива случайного размера
        for (int i = 0; i < array.length; i++) {
            random =  Math.random();
            if (random >= 0.5)  array[i] = (int) (Math.random() * 1000);// генерация случайных положительных и отрицательных значений
            else array[i] = -(int) (Math.random() * 1000);
        }
        Arrays.sort(array);
    }
    void printArray(){
        System.out.println(Arrays.toString(array));
    }
    void findDelta(){
        int[] array2=array.clone();
        int a;
        int c;
        for (int i = 0; i <array2.length ; i++) {
            a = array2[i];
            c = Arrays.binarySearch(array2, 7-a);
            if (c > 0) {
                    System.out.println(array2[c] + " + " + a+ " = 7");
                    array2[c]=0;//для избежания повторений
            }
        }
    }
}
class  FindDelta7TestDrive {
    public static void main(String[] args) {
        FindDelta7 delta7 = new FindDelta7();
        delta7.CreateArray();
        System.out.println("Созданный массив");
        delta7.printArray();
        System.out.println("Найденные пары чисел, сумма которых равняется 7");
        delta7.findDelta();

    }
}