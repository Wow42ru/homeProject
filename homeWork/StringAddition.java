

/*Найдите самое длинное слово в предложении, при условии, что в предложении все слова разной длины*/
class Include {
    /*Имеются две строки. Найти количество вхождений одной строки в другую.*/

    // indexOf("символ") - для поиска первого вхождения указанного символа
    // либо подстроки (вернет индекс)
    // lastIndexOf - для поиска последнего вхождения указанного символа
    // либо подстроки (вернет индекс)
    // -1 -если символ или подстрока не найдены

}

public class StringAddition {
    /*Даны 2 слова, состоящие из четного числа букв.
    Получить слово состоящее из первой половины первого слова и второй половины второго слова.*/
    String str1 = "Первое";
    String str2 = "собака";
    String newStr1 = str1.substring(0, str1.length() / 2);
    String newStr2 = str2.substring(str2.length() / 2, str2.length());
    String newStr = newStr1 + newStr2;

    void print() {
        System.out.println(newStr);
    }

}

class test {
    public static void main(String[] args) {
        StringAddition stringAddition = new StringAddition();
        stringAddition.print();
    }
}