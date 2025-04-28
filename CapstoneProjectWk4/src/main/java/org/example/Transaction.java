package org.example;

//import
import java.time.LocalDate;
import java.time.LocalTime;
//properties
public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private double amount;
    private String vendor;
    private String type; // Deposit or Payment
    private String description;

    //constructors
    public Transaction(LocalDate date, LocalTime time, double amount, String vendor, String type, String description) {
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.vendor = vendor;
        this.type = type;
        this.description = description; // set description
    }

    // getters/setters
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    // toString / override how transaction will print out
    @Override
    public String toString() {
        return date + " " + time + " | " + type + " | " + vendor + " | $" + String.format("%.2f", amount) + " | " + description;
    }
}
