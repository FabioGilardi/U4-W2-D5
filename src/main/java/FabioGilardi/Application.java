package FabioGilardi;

import FabioGilardi.entities.Book;
import FabioGilardi.entities.Magazine;
import FabioGilardi.entities.Readable;
import FabioGilardi.enums.Periodicity;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

//        INTERFACCIA UTENTE
        Scanner scanner = new Scanner(System.in);
        int chosenValue = -1;
        while (chosenValue != 0) {
            System.out.println("Choose 1 to add an element");
            System.out.println("Choose 2 to remove an element");
            System.out.println("Choose 3 to search an element with ISBN");
            System.out.println("Choose 4 to search an element by publication date");
            System.out.println("Choose 5 to search a book by author");
            System.out.println("Choose 6 to save the archive on disk");
            System.out.println("Choose 0 to close the programme");
            chosenValue = Integer.parseInt(scanner.nextLine());
            switch (chosenValue) {
                case 1: {
                    addAnElement(storeList);
                    break;
                }
                case 2: {
                    Readable.removeAnElement(storeList);
                    break;
                }

                case 3: {
                    Readable.searchByIsbn(storeList);
                    break;
                }

                case 4: {
                    Readable.searchByYear(storeList);
                    break;
                }

                case 5: {
                    Book.searchByAuthor(storeList);
                    break;
                }

                case 6: {
                    saveOnDisk(storeList);
                    break;
                }

                case 0: {
                    System.out.println("Programme is shutting down");
                    break;
                }

                default: {
                    logger.error("You must insert a value between 0 and 8");
                }
            }
        }
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

    public static void saveOnDisk(List<Readable> list) {
        File file = new File("src/archive.txt");
        List<Readable> bookList = list.stream().filter(readable -> readable instanceof Book).toList();
        List<Readable> magazineList = list.stream().filter(readable -> readable instanceof Magazine).toList();
        String bookListString = bookList.stream().map(book -> book.getIsbn() + "@" + book.getTitle() + "@" + book.getPublicationDate() + "@" + book.getPagesNumber() + "@" + ((Book) book).getAuthor() + "@" + ((Book) book).getGenre()).collect(Collectors.joining("#"));
        String magazineListString = magazineList.stream().map(magazine -> magazine.getIsbn() + "@" + magazine.getTitle() + "@" + magazine.getPublicationDate() + "@" + magazine.getPagesNumber() + "@" + ((Magazine) magazine).getPeriodicity()).collect(Collectors.joining("#"));
        String finalString = bookListString + "//" + magazineListString;
        try {
            FileUtils.writeStringToFile(file, finalString, "UTF-8");
            System.out.println("File saved correctly");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
