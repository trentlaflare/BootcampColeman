package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Book[] inventory = new Book[20];
        Book book1 = new Book("Shelly", 101, "701", true, "Dumb ways to die");
        inventory[0] = book1;
        Book book2 = new Book(102, "707", false, "Witch Hollow");
        inventory[1] = book2;
        Book book3 = new Book("Mark Twain", 103, "702", true, "Adventures of Tom Sawyer");
        inventory[2] = book3;
        Book book4 = new Book("Jane Austen", 104, "703", true, "Pride and Prejudice");
        inventory[3] = book4;
        Book book5 = new Book(105, "704", false, "The Lost Kingdom");
        inventory[4] = book5;
        Book book6 = new Book("Isaac Asimov", 106, "705", true, "Foundation");
        inventory[5] = book6;
        Book book7 = new Book(107, "706", false, "The Clockmaker's Code");
        inventory[6] = book7;
        Book book8 = new Book("J.K. Rowling", 108, "708", true, "The Tales of Beedle the Bard");
        inventory[7] = book8;
        Book book9 = new Book("George Orwell", 109, "709", true, "1984");
        inventory[8] = book9;
        Book book10 = new Book(110, "710", false, "Echoes of the Void");
        inventory[9] = book10;
        Book book11 = new Book("Mary Shelley", 111, "711", true, "Frankenstein");
        inventory[10] = book11;
        Book book12 = new Book(112, "712", false, "Binary Skies");
        inventory[11] = book12;
        Book book13 = new Book("Arthur Conan Doyle", 113, "713", true, "The Hound of the Baskervilles");
        inventory[12] = book13;
        Book book14 = new Book(114, "714", false, "Secrets of the Sea");
        inventory[13] = book14;
        Book book15 = new Book("Leo Tolstoy", 115, "715", true, "War and Peace");
        inventory[14] = book15;
        Book book16 = new Book(116, "716", false, "Digital Realms");
        inventory[15] = book16;
        Book book17 = new Book("Agatha Christie", 117, "717", true, "Murder on the Orient Express");
        inventory[16] = book17;
        Book book18 = new Book(118, "718", false, "Dreams of Tomorrow");
        inventory[17] = book18;
        Book book19 = new Book("H.G. Wells", 119, "719", true, "The Time Machine");
        inventory[18] = book19;
        Book book20 = new Book(120, "720", false, "Silent Stars");
        inventory[19] = book20;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What would you like to do?");
            Thread.sleep(1000);
            System.out.println("1) Show available books");
            Thread.sleep(1000);
            System.out.println("2) Check in a book");
            Thread.sleep(1000);
            System.out.println("3) Show checked out books");
            Thread.sleep(1000);
            System.out.println("4) Exit");
            Thread.sleep(1000);

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    showBooksOut(inventory);
                    break;
                case 2:
                    System.out.println("Here's the checked out books!");
                    showBooksOut(inventory);
                    checkInBook(inventory, scanner);
                    break;


                case 3:
                    showBooksIn(inventory);
                    break;


                case 4:
                    System.exit(0);
                    break;
            }
        }


    }

    public static void showBooksOut(Book[] inventory) {
        for (int i = 0; i < inventory.length; i++) {
            Book currentBook = inventory[i];
            if (currentBook != null && currentBook.isCheckedOut()) {
                System.out.println(currentBook.getTitle());
            }

        }
    }

    public static void showBooksIn(Book[] inventory) {
        for (int i = 0; i < inventory.length; i++) {
            Book theCurrentBook = inventory[i];
            if (theCurrentBook != null && !theCurrentBook.isCheckedOut()) {
                System.out.println(theCurrentBook.getTitle());
            }




        }
    }
    public static void checkInBook(Book[] inventory, Scanner scanner) {
        System.out.println("What book are you returning?");
        String returnedBook = scanner.nextLine();

        boolean isCheckedIn = false;

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getTitle().equalsIgnoreCase(returnedBook)) {
                if (!inventory[i].isCheckedOut()) {
                    System.out.println("That book is already checked in.");
                }
                else {
                    inventory[i].setCheckedOut(false);
                    System.out.println("Book checked in successfully.");
                }
                isCheckedIn = true;
                break;
            }
        }

        if (!isCheckedIn) {
            System.out.println("Book not found in inventory.");
        }
    }

}

