package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order implements PriceInterface {
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private int chipsCount;
    private LocalDateTime timestamp;

    public Order() {
        this.timestamp = LocalDateTime.now();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void setChipsCount(int count) {
        this.chipsCount = count;
    }
    public int getChipsCount() {
        return chipsCount;
    }


    public String generateReceipt() {
        LocalDateTime now = LocalDateTime.now();
        String timestampStr = String.format("%d-%02d-%02d %02d:%02d:%02d",
                now.getYear(),
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getHour(),
                now.getMinute(),
                now.getSecond());
        String receipt = "=== ORDER RECEIPT ===\n" +
                "Date: " + timestampStr + "\n\n";

        // Sandwiches
        receipt += "Sandwiches:\n";
        for (Sandwich sandwich : sandwiches) {
            receipt += "- " + sandwich.getSize() + " " +
                    sandwich.getBreadType() + ": $" +
                    String.format("%.2f", sandwich.calculatePrice()) + "\n";

            for (Topping topping : sandwich.getToppings()) {
                receipt += "  * " + topping.getName() +
                        (topping.isExtra() ? " (extra)" : "") + "\n";
            }
        }

        // Drinks
        if (!drinks.isEmpty()) {
            receipt += "\nDrinks:\n";
            for (Drink drink : drinks) {
                receipt += "- " + drink.getSize() + ": $" +
                        String.format("%.2f", drink.calculatePrice()) + "\n";
            }
        }

        // Chips
        if (chipsCount > 0) {
            receipt += "\nChips: " + chipsCount + " x $1.50 = $" +
                    String.format("%.2f", chipsCount * 1.50) + "\n";
        }

        return receipt + "\nTOTAL: $" + String.format("%.2f", calculatePrice());
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }




    // Add this calculation method
    public double calculateTotalPrice() {
        double total = 0.0;

        // Sum sandwich prices
        for (Sandwich sandwich : sandwiches) {
            total += sandwich.calculatePrice();
        }

        // Sum drink prices
        for (Drink drink : drinks) {
            total += drink.calculatePrice();
        }

        // Add chips
        total += chipsCount * 1.50;

        return total;
    }
    @Override
    public double calculatePrice() {
        double total = 0;

        for (Sandwich sandwich : sandwiches) {
            total += sandwich.calculatePrice();
        }

        for (Drink drink : drinks) {
            total += drink.calculatePrice();
        }

        total += chipsCount * 1.50;

        return total;
    }
}
