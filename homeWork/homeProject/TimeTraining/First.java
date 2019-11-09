import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Set;

public class First  {
//DataTimeApi
   LocalTime time = LocalTime.of(14,20);
   public void KakayaSmena(LocalTime time){
       LocalTime b= this.time.minusHours(time.ge;
       System.out.println(b.toString());
   }

}
class LocalTest{
    public static void main(String[] args) {
        First first= new First() ;
        LocalTime time = LocalTime.of(12,4);
        first.KakayaSmena(time);

    }
}
//### Домашнее задание к LESSON9 (даты и время):
//        1. Есть три рабочие смены:
//        1. с 7:00 до 15:00
//        2. с 15:00 до 23:00
//        3. с 23:00 до 7:00
//
//        Определить какая сейчас смена (относительно текущего времени)
//        2. Наши занятия закончатся 20 января 2020 года. Сколько занятий осталось,
//        если они проходят 3 раза в неделю (пн, ср, пт). Праздничные дни не учитывать.