package FabioGilardi.entities;

import FabioGilardi.Application;
import FabioGilardi.enums.Periodicity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Magazine extends Readable {

    private static Logger logger = LoggerFactory.getLogger(Application.class);


    //    ATTRIBUTES
    protected Periodicity periodicity;

    //    CONSTRUCTOR
    public Magazine(long isbn, String title, int publicationDate, int pagesNumber, Periodicity periodicity) {
        super(isbn, title, publicationDate, pagesNumber);
        this.periodicity = periodicity;
    }

    //    METHODS
    
    public Periodicity getPeriodicity() {
        return periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
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
        Magazine magazine = (Magazine) o;
        return periodicity == magazine.periodicity;
    }


}
