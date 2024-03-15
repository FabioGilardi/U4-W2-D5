package FabioGilardi.entities;

import FabioGilardi.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Book extends Readable {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    //    ATTRIBUTES
    protected String author, genre;

    //    CONSTRUCTOR
    public Book(long isbn, String title, int publicationDate, int pagesNumber, String author, String genre) {
        super(isbn, title, publicationDate, pagesNumber);
        this.author = author;
        this.genre = genre;
    }

    //    METHODS
    public static void searchByAuthor(List<Readable> list) {
        Scanner myScanner = new Scanner(System.in);
        int chosen = -1;
        while (chosen != 0) {
            System.out.println("Type 1 if you want to find an element");
            System.out.println("Type 0 if you want to retrieve");
            chosen = Integer.parseInt(myScanner.nextLine());
            switch (chosen) {
                case 1: {
                    System.out.println("Insert the author: ");
                    String author = myScanner.nextLine();
                    Map<String, List<Readable>> bookList = list.stream().filter(readable -> readable instanceof Book).filter(book -> ((Book) book).getAuthor().equals(author)).collect(Collectors.groupingBy(book -> ((Book) book).getAuthor()));
                    System.out.println("These are the elements published by this author " + author + ":");
                    bookList.forEach((s, list1) -> System.out.println(s + " " + list1));
                    break;
                }
                case 0: {
                    System.out.println("You went back");
                    break;
                }
                default: {
                    logger.error("You must choose a correct value to proceed!");
                }
            }

        }
    }

    public String getAuthor() {
        return author;
    }


    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", pagesNumber=" + pagesNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }
}
