package homeWork;
// наибольший общий делитель НОД
public class Nod {
    int num;
   static public void findNod(int num1,int num2){
       num1=(int)Math.sqrt(num1*num1);// для обработки отрицательных чисел
       num2=(int)Math.sqrt(num2*num2);
        while (num1!=0&&num2!=0){
            if (num1>num2) {
                num1 = num1%num2;
            }
            else {
                num2 = num2%num1;
            }
            }
       int nod=num1+num2;
       System.out.println(nod);
    }
}
class NodTestDrive{
    public static void main(String[] args) {
        Nod nod1 =new Nod();
        Nod nod2 =new Nod();
        nod1.num = -5;
        nod2.num = 500;
        Nod.findNod(nod1.num,nod2.num);

    }
}
