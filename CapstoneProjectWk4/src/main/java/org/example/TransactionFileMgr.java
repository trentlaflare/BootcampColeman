package org.example;

// system input and output for data streams, read/write/handle files
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// save and load transactions here
public class TransactionFileMgr {
    // static final - belongs to this class and never changes
    private static final String FILE_NAME = "transactions.csv";

    // filewriter - needs exception / try-catch
    public static void saveTransaction(Transaction transaction) {
        // append with true to add transactions instead of overwriting the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            // write a single transaction, put into CSV format
            writer.println(transaction.getDate() + "," + transaction.getTime() + "," + transaction.getAmount() + "," + transaction.getVendor() + "," + transaction.getDescription());
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // method to load transactions
    public static List<Transaction> loadTransactions() {
        // read transactions to show back if asked
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return transactions; // return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            // read until file line is null
            while ((line = reader.readLine()) != null) {
                //comma delimited, -1 keeps all fields so code doesn't break if part of array is left empty
                String[] parts = line.split(",", -1);
                // if the length of the string is enough, break down parts into their categories
                if (parts.length >= 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    double amount = Double.parseDouble(parts[2]);
                    String vendor = parts[3];
                    String description = parts[4];
                    // add a new Transaction object to the list
                    transactions.add(new Transaction(date, time, amount, vendor, "Transaction", description));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }

    // method to clear all transactions
    public static void clearTransactions() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // overwrites the file with nothing (clears all data)
        } catch (IOException e) {
            System.out.println("Error clearing transactions: " + e.getMessage());
        }
    }
}
