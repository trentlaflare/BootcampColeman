package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileManager {
    public static void saveOrderToCSV(Order order) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = String.format("%04d-%02d-%02d %02d:%02d:%02d",
                now.getYear(),
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getHour(),
                now.getMinute(),
                now.getSecond());

        String filename = "orders.csv";

        try (FileWriter writer = new FileWriter(filename, true)) {

            writer.write("Item,Description,Price\n");

            writer.write(timestamp);

            // Write w string format (same idea as printf)
            for (Sandwich sandwich : order.getSandwiches()) {
                writer.write(String.format(
                        "Sandwich,%s %s sandwich,%.2f\n",
                        sandwich.getSize(),
                        sandwich.getBreadType(),
                        sandwich.calculatePrice()
                ));
            }

            for (Drink drink : order.getDrinks()) {
                writer.write(String.format(
                        "Drink,%s drink,%.2f%n",
                        drink.getSize(),
                        drink.calculatePrice()
                ));
            }

            if (order.getChipsCount() > 0) {
                writer.write(String.format(
                        "Chips,%d bags,%.2f%n",
                        order.getChipsCount(),
                        order.getChipsCount() * 1.50
                ));
            }

            writer.write(String.format(
                    "Total,%.2f%n",
                    order.calculateTotalPrice()
            ));

            System.out.println("Order saved to: " + filename);

        } catch (IOException e) {
            System.out.println("Could not save order: " + e.getMessage());
        }
    }
}