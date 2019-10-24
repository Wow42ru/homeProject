package homeProject.Lesson4;

import java.util.Arrays;
import java.util.Scanner;

public class Library {
    private Book[] books = new Book[10];
    private int FreePlace = 0;//-счетчик добавления книги в библеотеку

    void Print() {
        for (int i = 0; i < books.length; i++) {
            System.out.println(i + ". " + books[i]);
        }
    }

    public void addBook(Book newBook) {   // В библеотеку можно добавить одну книгу

        for (int i = 0; FreePlace < this.books.length; i++) {// проверяет наличие свободного места
            if (FreePlace == this.books.length) {
                System.out.println("Библеотека перегружена");
                break;
            }
            if (this.books[i] == null) {// если "полка" под номером i свободна(нулл)
                this.books[i] = newBook;// присваивает значение
                FreePlace++;
                break;//==return
            }
        }
    }

    public void addBook(Book... newBooks) {// Это перегруз метода, одинаковые названия, но разные аргументы
        int numberOfNewBook = 0;//для последовательного назначения места newBooks в массиве books
        someBook:
        for (int i = 0; FreePlace < this.books.length; i++) {
            if (this.books[i] == null) {// проверка на свободное место
                this.books[i] = newBooks[numberOfNewBook];
                FreePlace++;// "закрытие" индекса
                numberOfNewBook++;
                if (numberOfNewBook == newBooks.length) {
                    numberOfNewBook = 0;// При вызове нового метода отсчёт ведётся с 0
                    break someBook;
                }
            }
        }
        if (FreePlace == this.books.length) {
            System.out.println("Библеотека перегружена");

        }
    }

    public String getInfo(String title) {
        Scanner scanner = new Scanner(System.in);
        String nameOfBook = scanner.nextLine();
        for (int i = 0; i < this.books.length; i++) {

        }
        String info = "";
        if (scanner.nextLine().equals(1))
            getInfo( title);
        return info;
    }


    @Override
    public String toString() {
        return "Library{" +
                "books=" + Arrays.toString((books)) + '}';
    }
}
