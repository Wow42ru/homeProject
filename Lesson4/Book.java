package homeProject.Lesson4;

// Программы на java состоят из совокупности объектов, каждый объект - экземпляр определённого класса
//1.Выделяем объекты на уровне концепций и выделяем связи между  ними
//Каждый тип объекта имеет свою сферу ответствености
//2. Описание классов , там описываем свойства и методы будующих обьектов
//3. Создание объектов
//          Система учёта книг в библеотеке (для примера)
// Каждая книга должна иметь 1.Автор 2.Название 3. Доступ к книге (дом/читальный зал)
// 4. Доступна ли в данный момент(кто-то уже взял)
//          Книги хранятся в библеотеке  В библеотеку можно добавить 1 книгу или сразу несколько
//          Должна быть возможность получить инфу по книги
// Получаемая инфа (автор, название, можно ли забрать домой, есть ли в наличии)
// если книга не найдена, сообщаем, что такой  книги нет
// Длжна быть возможность взять книгу домой, указав название или прочесть в читальном зале, указав название
public class Book {
    // Свойста объекта (поля, атрибуты)
    private String author;// автор
    private String title;// название книги

    // Методы устанавливающей значения свойств title
    public void setTitle(String title) {
        if (title != null || !"".equals(title))
            this.title = title;
        else System.out.println("введите корректоное значение");
    }

    public void setAuthor(String author) {
        if (author != null || !"".equals(author))
            this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    // Создали класс - сразу можем создавать объект этого класса
    private boolean isForHome;
    private boolean isAvaiable;

    public boolean getIsForHome() {
        return isForHome;
    }

    public void setForHome(boolean forHome) {
        isForHome = forHome;
    }

    public boolean getIsAvaiable() {
        return isAvaiable;
    }

    public void setAvaiable(boolean avaiable) {
        isAvaiable = avaiable;
    }

    public Book(String title, String author) {
        setTitle(title);
        setAuthor(author);
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", isForHome=" + isForHome +
                ", isAvaiable=" + isAvaiable +
                '}';
    }
}
