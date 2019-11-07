package homeProject.Lesson4;


public class Main {
    public static void main(String[] args) {
        Book java = new Book();// вызов конструктора
        Book cleanCode = new Book();
        java.setTitle("Философия Java");
        cleanCode.setTitle("Чистый код");
        java.setAuthor("Брюс Эккель");
        cleanCode.setAuthor("Роберт Мартин");
        java.setAvaiable(true);
        java.setForHome(true);
        cleanCode.setAvaiable(true);
        Book forBeginners = new Book("Руководство для начинающих", "Герберт Шилдт");
        Book headFirstJava = new Book("Head First Java", "Кэти Сиера");
        Book notforBeginners = new Book("Java 8. Полное руководство", "Герберт Шилдт");
        Book javaLiblatyOfProf = new Book("Java. Библиотека профессионала", "Кей С. Хорстманн");
        Book javaRussian = new Book("Java. Методы программирования", "Блинов");
        Book effectiveJava = new Book("Effective Java", "Джошуа Блох");
        Book forBeginners7 = new Book("книга1", "автор1");
        Book forBeginners8 = new Book("книга2", "автор2");
        Book forBeginners9 = new Book("книга3", "автор3");
        Library library = new Library();
        library.addBook(java);
        library.addBook(cleanCode, forBeginners,headFirstJava,notforBeginners,javaLiblatyOfProf,javaRussian,effectiveJava);
        library.addBook(forBeginners7,forBeginners8,forBeginners9);
        System.out.println(library);
        System.out.println( library.getInfo(java));
        System.out.println(library.takeHome("Философия Java"));
        System.out.println(library.takeHome("Руководство для начинающих"));

    }
}
