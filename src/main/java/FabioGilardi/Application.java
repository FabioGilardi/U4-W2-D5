package FabioGilardi;

import FabioGilardi.entities.Book;
import FabioGilardi.entities.Magazine;
import FabioGilardi.entities.Readable;
import FabioGilardi.enums.Periodicity;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

import static FabioGilardi.entities.Readable.searchByYear;

public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
//        CREAZIONE DELLA LISTA DI LIBRI E RIVISTE
        List<Readable> storeList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            if (random.nextInt(0, 2) == 0) {
                storeList.add(bookSupplier().get());
            } else storeList.add(magazineSupplier().get());
        }

        storeList.forEach(System.out::println);

//        AGGIUNTA DI UN ELEMENTO
//        addAnElement(storeList);
//        storeList.forEach(System.out::println);


//        ELIMINAZIONE DI UN ELEMENTO
//        removeAnElement(storeList);
//        storeList.forEach(System.out::println);

//        RICERCA PER ISBN
//        searchByIsbn(storeList);

//        RICERCA PER ANNO
        searchByYear(storeList);
        storeList.forEach(System.out::println);

    }

    public static Supplier<Book> bookSupplier() {
        Supplier<Book> bookSupplier = () -> {
            Random random = new Random();
            Faker faker = new Faker();
            long randomIsbn = random.nextInt(1000000, 10000000);
            String randomTitle = faker.book().title();
            int randomDate = random.nextInt(2000, 2025);
            int randomPages = random.nextInt(100, 1001);
            String randomAuthor = faker.book().author();
            String randomGenre = faker.book().genre();
            return new Book(randomIsbn, randomTitle, randomDate, randomPages, randomAuthor, randomGenre);
        };

        return bookSupplier;
    }

    public static Supplier<Magazine> magazineSupplier() {
        Supplier<Magazine> magazineSupplier = () -> {
            Random random = new Random();
            Faker faker = new Faker();
            long randomIsbn = random.nextInt(1000000, 10000000);
            String randomTitle = faker.book().title();
            int randomDate = random.nextInt(2000, 2025);
            int randomPages = random.nextInt(40, 101);
            int randomPeriodicity = random.nextInt(0, 3);
            List<Periodicity> periodicityList = new ArrayList<>();
            periodicityList.add(Periodicity.SEMESTRAL);
            periodicityList.add(Periodicity.MONTHLY);
            periodicityList.add(Periodicity.SEMESTRAL);
            return new Magazine(randomIsbn, randomTitle, randomDate, randomPages, periodicityList.get(randomPeriodicity));
        };

        return magazineSupplier;

    }

    public static void addAnElement(List<Readable> list) {
        Scanner myScanner = new Scanner(System.in);
        int chosenNumber = -1;
        while (chosenNumber != 0) {
            System.out.println("Type 1 if u want to add a book");
            System.out.println("Type 2 if u want to add a magazine");
            System.out.println("Type 0 if u want to retrieve");

            chosenNumber = Integer.parseInt(myScanner.nextLine());
            switch (chosenNumber) {
                case 1: {
                    System.out.println("You choose to add a book");
                    System.out.println("Insert the ISBN");
                    long isbn = Long.parseLong(myScanner.nextLine());
                    System.out.println("Insert the title");
                    String title = myScanner.nextLine();
                    System.out.println("Insert the publication date");
                    int publicationDate = Integer.parseInt(myScanner.nextLine());
                    System.out.println("Insert the pages");
                    int pages = Integer.parseInt(myScanner.nextLine());
                    if (pages <= 0) {
                        continue;
                    }
                    System.out.println("Insert the author");
                    String author = myScanner.nextLine();
                    System.out.println("Insert the genre");
                    String genre = myScanner.nextLine();
                    list.add(0, new Book(isbn, title, publicationDate, pages, author, genre));
                    System.out.println("Book has been added correctly");
                    break;
                }
                case 2: {
                    Random random = new Random();
                    System.out.println("You choose to add a magazine");
                    System.out.println("Insert the ISBN");
                    long isbn = Long.parseLong(myScanner.nextLine());
                    System.out.println("Insert the title");
                    String title = myScanner.nextLine();
                    System.out.println("Insert the publication date");
                    int publicationDate = Integer.parseInt(myScanner.nextLine());
                    System.out.println("Insert the pages");
                    int pages = Integer.parseInt(myScanner.nextLine());
                    if (pages <= 0) {
                        continue;
                    }
                    int randomPeriodicity = random.nextInt(0, 3);
                    List<Periodicity> periodicityList = new ArrayList<>();
                    periodicityList.add(Periodicity.SEMESTRAL);
                    periodicityList.add(Periodicity.MONTHLY);
                    periodicityList.add(Periodicity.SEMESTRAL);
                    list.add(0, new Magazine(isbn, title, publicationDate, pages, periodicityList.get(randomPeriodicity)));
                    System.out.println("Magazine has been added correctly");
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


}
