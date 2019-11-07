package homeProject.Lesson4;

import java.util.Arrays;
import java.util.Scanner;

public class Library {
    private homeProject.Lesson4.Book[] books = new homeProject.Lesson4.Book[10];
    private int FreePlace = 0;//-счетчик добавления книги в библеотеку

    // В библеотеку можно добавить одну книгу
    public void addBook(Book newBook) {
        for (int i = 0; FreePlace < this.books.length - 1; i++) {
            if (this.books[i] == null) {
                this.books[i] = newBook;
                FreePlace++;
                break;//==return
            }
        }

    }

    public void addBook(Book... newBooks) {// Это перегруз метода, одинаковые названия, но разные аргументы
        for (; FreePlace < newBooks.length; FreePlace++) {
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

    public  String getInfo(Book bookTitle) {
        for (int i = 0; i < FreePlace; i++) {
        if (bookTitle.getTitle().equals(books[i].getTitle()))
        return books[i].toString();
        }
        return "Такой книги в библиотеке нет";
    }
    public Book takeHome(String title){
        for (int i = 0; i < FreePlace; i++) {
            if (title.equals(books[i].getTitle())&& books[i].getIsForHome()) {
                books[i].setForHome(false);
                books[i].setAvaiable(false);
                return books[i];
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "homeProject.Lesson4.Library{" +
                "books=" + Arrays.toString(books) +
                '}';
    }
}

