package org.example;

// basic imports for date and input
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    // scanner in main so every scanner works, create one instance of scanner to be used through main
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // method to run 'homeScreen' / cleaner look later
        homeScreen();
    }

    private static void homeScreen() {
        while (true) {
            System.out.println(" ");
            System.out.println("=== Home Screen ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("V) View Total");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();
            // switch case for user input
            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "V":
                    List<Transaction> transactions = TransactionFileMgr.loadTransactions();
                    double total = viewTotal(transactions);
                    System.out.printf("Current Total Balance: $%.2f%n", total);
                    break;
                case "L":
                    ledgerScreen();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addDeposit() {
        System.out.print("Enter amount: ");
        double amount = readDouble();
        // force deposit to be positive using math.abs
        if (amount < 0) {
            amount = Math.abs(amount);
        }

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        // use local date and time as of now
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // create deposit transaction
        Transaction deposit = new Transaction(date, time, amount, vendor, "Deposit", description);
        TransactionFileMgr.saveTransaction(deposit);
        System.out.println("Deposit saved!");
    }

    private static void makePayment() {
        System.out.print("Enter amount: ");
        double amount = readDouble();
        // force payment to negative
        if (amount > 0) {
            amount = -amount;
        }

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        // use local date and time now
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // create payment transaction
        Transaction payment = new Transaction(date, time, amount, vendor, "Payment", description);
        TransactionFileMgr.saveTransaction(payment);
        System.out.println("Payment saved!");
    }

    private static void ledgerScreen() {
        //while loop to continue until exit/ since it is a new screen
        while (true) {
            //load transactions from file
            List<Transaction> transactions = TransactionFileMgr.loadTransactions();
            // manually sort by date (newest first)
            List<Transaction> sortedTransactions = new ArrayList<>();
            for (Transaction t : transactions) {
                boolean inserted = false;
                for (int i = 0; i < sortedTransactions.size(); i++) {
                    if (t.getDate().isAfter(sortedTransactions.get(i).getDate())) {
                        sortedTransactions.add(i, t);
                        inserted = true;
                        //sets the newest transactions first by checking if one date is after the other placing it before or later
                        //inserted = true ot know it was added
                        break;
                    }
                }
                if (!inserted) {
                    sortedTransactions.add(t);
                    //if it wasn't inserted bc it's the oldest in comparison, we add it to the end of the list
                }
            }
            System.out.println(" ");
            System.out.println("=== Ledger Screen ===");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            //make sure user input is one character(no extra space) and matching what we ask for
            String userInput = scanner.nextLine().trim().toUpperCase();
            switch (userInput) {
                case "A":
                    displayTransactions(sortedTransactions);
                    break;
                case "D":
                    List<Transaction> deposits = new ArrayList<>();
                    //loop
                    for (Transaction t : sortedTransactions) {
                        //if transaction is positive, it's a deposit
                        if (t.getAmount() > 0) {
                            // manually add to list
                            deposits.add(t);
                        }
                    }
                    displayTransactions(deposits);
                    break;
                case "P":
                    List<Transaction> payments = new ArrayList<>();
                    //if the transaction is negative it's a payment
                    for (Transaction t : sortedTransactions) {
                        if (t.getAmount() < 0) {
                            payments.add(t); // manually add to list
                        }
                    }
                    displayTransactions(payments);
                    break;
                case "R":
                    reportsScreen();
                    break;
                case "H":
                    // exit / home screen
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void reportsScreen() {
        while (true) {
            List<Transaction> transactions = TransactionFileMgr.loadTransactions();
            // manually sort by date (newest first)
            List<Transaction> sortedTransactions = new ArrayList<>();
            for (Transaction t : transactions) {
                boolean inserted = false;
                for (int i = 0; i < sortedTransactions.size(); i++) {
                    if (t.getDate().isAfter(sortedTransactions.get(i).getDate())) {
                        sortedTransactions.add(i, t);
                        inserted = true;
                        break;
                    }
                }
                if (!inserted) {
                    sortedTransactions.add(t);
                }
            }

            System.out.println("=== Reports Screen ===");
            System.out.println("1) Month-To-Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year-To-Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) View Total");
            System.out.println("0) Back");
            System.out.print("Choose a report: ");

            String choice = scanner.nextLine().trim();
            LocalDate today = LocalDate.now(); // only call once at top
            switch (choice) {
                case "1":
                    List<Transaction> mtd = new ArrayList<>();
                    for (Transaction t : sortedTransactions) {
                        if (t.getDate().getMonth() == today.getMonth() && t.getDate().getYear() == today.getYear()) {
                            // manually add to the list
                            mtd.add(t);
                        }
                    }
                    displayTransactions(mtd);
                    break;
                case "2":
                    LocalDate firstOfThisMonth = today.withDayOfMonth(1);
                    LocalDate lastMonth = firstOfThisMonth.minusDays(1);
                    List<Transaction> prevMonth = new ArrayList<>();
                    for (Transaction t : sortedTransactions) {
                        if (t.getDate().getMonth() == lastMonth.getMonth() && t.getDate().getYear() == lastMonth.getYear()) {
                            prevMonth.add(t); // manually add to the list
                        }
                    }
                    displayTransactions(prevMonth);
                    break;
                case "3":
                    List<Transaction> ytd = new ArrayList<>();
                    for (Transaction t : sortedTransactions) {
                        if (t.getDate().getYear() == today.getYear()) {
                            ytd.add(t); // manually add to the list
                        }
                    }
                    displayTransactions(ytd);
                    break;
                case "4":
                    int prevYear = today.getYear() - 1;
                    List<Transaction> lastYear = new ArrayList<>();
                    for (Transaction t : sortedTransactions) {
                        if (t.getDate().getYear() == prevYear) {
                            // manually add to the list
                            lastYear.add(t);
                        }
                    }
                    displayTransactions(lastYear);
                    break;
                case "5":
                    System.out.print("Enter vendor name to search: ");
                    String vendorSearch = scanner.nextLine().trim().toLowerCase();
                    List<Transaction> vendorResults = new ArrayList<>();
                    for (Transaction t : sortedTransactions) {
                        if (t.getVendor().toLowerCase().contains(vendorSearch)) {
                            // manually add to the list
                            vendorResults.add(t);
                        }
                    }
                    displayTransactions(vendorResults);
                    break;
                case "0":
                    return; // back to Ledger screen
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    // method to ensure input is a double and catch exception
    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    //method to get total balance
    private static double viewTotal(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No Total to view");
            return 0.0; // <-- return a total of 0 if empty
        } else {
            double total = 0.0;
            for (Transaction i : transactions) {
                total += i.getAmount();
            }
            return total;
        }
    }
}
