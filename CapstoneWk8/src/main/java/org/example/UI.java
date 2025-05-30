package org.example;

import java.util.Scanner;
import java.util.List;

public class UI {
    private Scanner scanner;
    private Order currentOrder;
    private SandwichBuilder sandwichBuilder;
    private boolean running;

    public UI() {
        this.scanner = new Scanner(System.in);
        this.currentOrder = new Order();
        this.sandwichBuilder = new SandwichBuilder();
        this.running = true;
    }

    public void start() {
        System.out.println("=== Sandwich Order System ===");

        while (running) {
            displayMainMenu();
            int choice = getIntInput(1, 5); //ranges from 1-5, outside of that would reject

            switch (choice) {
                case 1:
                    newSandwichOrder();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    reviewOrder();
                    break;
                case 5:
                    completeOrder();
                    break;
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. New Sandwich");
        System.out.println("2. Add Drink");
        System.out.println("3. Add Chips");
        System.out.println("4. Review Order");
        System.out.println("5. Complete Order");
        System.out.print("Select an option: ");
    }

    private void newSandwichOrder() {
        System.out.println("\n=== New Sandwich ===");

        // Select bread type
        System.out.println("\nBread Types:");
        System.out.println("1. White");
        System.out.println("2. Wheat");
        System.out.println("3. Sourdough");
        System.out.println("4. Wrap");
        System.out.print("Select bread type (0 for none): ");
        int breadChoice = getIntInput(0, 4);
        BreadType breadType = null;
        if (breadChoice == 1) breadType = BreadType.WHITE;
        else if (breadChoice == 2) breadType = BreadType.WHEAT;
        else if (breadChoice == 3) breadType = BreadType.RYE;
        else if (breadChoice == 4) breadType = BreadType.WRAP;

        // Select size
        System.out.println("\nSandwich Sizes:");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");
        System.out.print("Select size: ");
        int sizeChoice = getIntInput(1, 3);
        Size size = null;
        if (sizeChoice == 1) size = Size.FOUR_INCH;
        else if (sizeChoice == 2) size = Size.TWELVE_INCH;
        else if (sizeChoice == 3) size = Size.EIGHT_INCH;

        // Toasted?
        System.out.print("Toast sandwich? (y/n): ");
        boolean toasted = getYesNoInput();

        sandwichBuilder.startNewSandwich(breadType, size, toasted);
        addToppings();

        currentOrder.addSandwich(sandwichBuilder.getCurrentSandwich());
        System.out.println("\nSandwich added to order!");
    }

    private void addToppings() {
        boolean addingToppings = true;

        while (addingToppings) {
            System.out.println("\nAdd Toppings:");
            System.out.println("1. Meats");
            System.out.println("2. Cheeses");
            System.out.println("3. Regular Toppings");
            System.out.println("4. Done with Sandwich");
            System.out.print("Select option: ");

            int choice = getIntInput(1, 4);

            switch (choice) {
                case 1:
                    addPremiumTopping(ToppingType.MEAT);
                    break;
                case 2:
                    addPremiumTopping(ToppingType.CHEESE);
                    break;
                case 3:
                    addRegularTopping();
                    break;
                case 4:
                    addingToppings = false;
                    break;
            }
        }
    }

    private void addPremiumTopping(ToppingType type) {
        List<PremiumTopping> toppings;

        if (type == ToppingType.MEAT) {
            toppings = sandwichBuilder.getAvailableMeats();
        } else {
            toppings = sandwichBuilder.getAvailableCheeses();
        }

        System.out.println("\nAvailable " + type.toString().toLowerCase() + "s:");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println((i + 1) + ". " + toppings.get(i).getName());
        }

        System.out.print("Select topping (0 to cancel): ");
        int choice = getIntInput(0, toppings.size());

        if (choice > 0) {
            System.out.print("Add extra portion? (y/n): ");
            boolean extra = getYesNoInput();

            PremiumTopping selected = toppings.get(choice - 1);
            sandwichBuilder.addTopping(new PremiumTopping(selected.getName(), extra, type));
            String message = selected.getName();
            if (extra) {
                message += " (extra)";
            }
            message += " added!";
            System.out.println(message);
        }
    }


    private void addRegularTopping() {
        List<RegularTopping> toppings = sandwichBuilder.getAvailableRegularToppings();

        System.out.println("\nAvailable Regular Toppings:");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println((i + 1) + ". " + toppings.get(i).getName());
        }
        System.out.print("Select topping (0 to cancel): ");
        int choice = getIntInput(0, toppings.size());

        if (choice > 0) {
            System.out.print("Add extra portion? (y/n): ");
            boolean extra = getYesNoInput();

            RegularTopping selected = toppings.get(choice - 1);
            sandwichBuilder.addTopping(new RegularTopping(selected.getName(), extra));
            String name = selected.getName();
            if (extra) {
                name += " (extra)";
            }
            System.out.println(name + " added!");
        }
    }

    private void addDrink() {
        System.out.println("\n=== Add Drink ===");
        System.out.println("Drink Sizes:");
        for (int i = 0; i < DrinkSize.values().length; i++) {
            DrinkSize size = DrinkSize.values()[i];
            Drink drink = new Drink(size);
            double price = drink.calculatePrice();
            String formattedPrice = String.format("%.2f", price);

            System.out.println((i + 1) + ". " + size + " - $" + formattedPrice);
        }
        System.out.print("Select size (0 to cancel): ");
        int choice = getIntInput(0, DrinkSize.values().length);

        if (choice > 0) {
            DrinkSize size = DrinkSize.values()[choice - 1];
            currentOrder.addDrink(new Drink(size));
            System.out.println(size + " drink added!");
        }
    }

    private void addChips() {
        System.out.println("\n=== Add Chips ===");
        System.out.print("Enter number of chips ($1.50 each, 0 to cancel): ");
        int count = getIntInput(0, 10); // Max 10 chips

        if (count > 0) {
            currentOrder.setChipsCount(currentOrder.getChipsCount() + count);
            System.out.println(count + " chips added!");
        }
    }

    private void reviewOrder() {
        System.out.println("\n=== Current Order ===");
        System.out.println(currentOrder.generateReceipt());
        System.out.print("Press enter to continue...");
        scanner.nextLine();

    }

    private void completeOrder() {

        System.out.println("\n=== ORDER SUMMARY ===");
        System.out.println(currentOrder.generateReceipt());

        // Ask for confirmation
        System.out.print("\nConfirm and save this order? (y/n): ");
        String input = scanner.nextLine().toLowerCase();

        if (input.equals("y") || input.equals("yes")) {
            // Save to CSV
            FileManager.saveOrderToCSV(currentOrder);

            System.out.println("Order saved successfully!");
            System.out.println("Thank you for your order!");
            currentOrder = new Order(); // Reset for next order
        } else {
            System.out.println("Order cancelled.");
        }
    }


    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print("Please enter a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private boolean getYesNoInput() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.print("Please enter 'y' or 'n': ");
        }

    }
}

