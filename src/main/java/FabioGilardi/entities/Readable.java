package FabioGilardi.entities;

import FabioGilardi.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public abstract class Readable {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    //    ATTRIBUTES
    protected long isbn;
    protected String title;

    protected int pagesNumber, publicationDate;

    //    CONSTRUCTOR
    public Readable(long isbn, String title, int publicationDate, int pagesNumber) {
        this.isbn = isbn;
        this.title = title;
        this.publicationDate = publicationDate;
        this.pagesNumber = pagesNumber;
    }

    //    METHODS

    public static void removeAnElement(List<Readable> list) {
        Scanner myScanner = new Scanner(System.in);
        int chosen = -1;
        while (chosen != 0) {
            System.out.println("Type 1 if you want to remove an element");
            System.out.println("Type 0 if you want to retrieve");
            chosen = Integer.parseInt(myScanner.nextLine());
            switch (chosen) {
                case 1: {
                    System.out.println("Insert the isbn: ");
                    long isbn = Long.parseLong(myScanner.nextLine());
                    if (isbn < 0) {
                        continue;
                    }
                    try {
                        Readable elementToRemove = list.stream().filter(readable -> readable.getIsbn() == isbn).toList().get(0);
                        list.remove(elementToRemove);
                        System.out.println("Element removed correctly");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("No element has been found");
                        continue;
                    }
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

    public static void searchByIsbn(List<Readable> list) {
        Scanner myScanner = new Scanner(System.in);
        int chosen = -1;
        while (chosen != 0) {
            System.out.println("Type 1 if you want to find an element");
            System.out.println("Type 0 if you want to retrieve");
            chosen = Integer.parseInt(myScanner.nextLine());
            switch (chosen) {
                case 1: {
                    System.out.println("Insert the isbn: ");
                    long isbn = Long.parseLong(myScanner.nextLine());
                    if (isbn < 0) {
                        continue;
                    }
                    try {
                        Readable elementFound = list.stream().filter(readable -> readable.getIsbn() == isbn).toList().get(0);
                        System.out.println("This is the element you are looking for:");
                        System.out.println(elementFound);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("No element has been found");
                        continue;
                    }
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

    public static void searchByYear(List<Readable> list) {
        Scanner myScanner = new Scanner(System.in);
        int chosen = -1;
        while (chosen != 0) {
            System.out.println("Type 1 if you want to find an element");
            System.out.println("Type 0 if you want to retrieve");
            chosen = Integer.parseInt(myScanner.nextLine());
            switch (chosen) {
                case 1: {
                    System.out.println("Insert the year: ");
                    int year = Integer.parseInt(myScanner.nextLine());
                    if (year < 0 || year > 2024) {
                        logger.error("The year must be between 0 and 2024");
                        continue;
                    }
                    Map<Integer, List<Readable>> elementsByYear = list.stream().filter(readable -> readable.getPublicationDate() == year).collect(Collectors.groupingBy(Readable::getPublicationDate));
                    System.out.println("These are the elements published in year " + year + ":");
                    elementsByYear.forEach((integer, list1) -> System.out.println(list1));
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

    public long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

}
