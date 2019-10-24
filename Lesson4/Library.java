package homeProject.Lesson4;

import java.util.Arrays;
import java.util.Scanner;

public class Library {
    private Book[] books = new Book[10];
    private int FreePlace = 0;//-счетчик добавления книги в библеотеку
    // В библеотеку можно добавить одну книгу
    public void addBook(Book newBook) {
        for (int i = 0; FreePlace < this.books.length-1; i++) {
            if (this.books[i] == null) {
                this.books[i] = newBook;
                FreePlace++;
                break;//==return
            }
        }

    }

    public void addBook(Book... newBooks) {// Это перегруз метода, одинаковые названия, но разные аргументы
     for (;FreePlace < newBooks.length;FreePlace++) {
            for (int i = 0; i < this.books.length; i++) {
                if (this.books[i] == null) {
                    this.books[i] = newBooks[FreePlace];
                    break;
                }
            }
            if (FreePlace == this.books.length) {
                System.out.println("Библеотека перегружена");
            }
        }

    }

    public String getInfo(String title) {
        Scanner scanner = new Scanner(System.in);
        String nameOfBook = scanner.nextLine();
        for (int i = 0; i < this.books.length; i++) {

        }
        String info = "";
        return info;
    }


    @Override
    public String toString() {
        return "Library{" +
                "books=" + Arrays.toString(books) +
                '}';
    }
}
