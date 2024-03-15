package FabioGilardi.entities;

import java.util.Objects;

public class Book extends Readable {

    //    ATTRIBUTES
    protected String author, genre;

    //    CONSTRUCTOR
    public Book(long isbn, String title, int publicationDate, int pagesNumber, String author, String genre) {
        super(isbn, title, publicationDate, pagesNumber);
        this.author = author;
        this.genre = genre;
    }

    //    METHODS
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
