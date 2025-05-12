package org.example;

import java.util.List;
import java.util.Scanner;

public class UI {
    private DealerShip dealership;
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
        inUI();
    }

    private void inUI() {
        DealershipFileMgr fileManager = new DealershipFileMgr();
        dealership = fileManager.getDealership();
    }

    public void display() {
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter a command: ");


            switch (choice) {
                case 1:
                    GetByPriceRequest();
                    break;
                case 2:
                    GetByMakeRequest();
                    break;
                case 3:
                    GetByModelRequest();
                    break;
                case 4:
                    GetByYearRequest();
                    break;
                case 5:
                    GetByColorRequest();
                    break;
                case 6:
                    GetByMileageRequest();
                    break;
                case 7:
                    GetByTypeRequest();
                    break;
                case 8:
                    AllVehiclesRequest();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("""
            -------------------------------
            Welcome to the Grays Dealership App
            1 - Find vehicles within a price range
            2 - Find vehicles by make
            3 - Find vehicles by model
            4 - Find vehicles by year range
            5 - Find vehicles by color
            6 - Find vehicles by mileage range
            7 - Find vehicles by type
            8 - List ALL vehicles
            0 - Quit
            -------------------------------
        """);
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid decimal: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    private int getYearInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid year: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void GetByPriceRequest() {
        double min = getDoubleInput("Enter minimum price: ");
        double max = getDoubleInput("Enter maximum price: ");
        List<Vehicle> matches = dealership.getVehiclesByPrice(min, max);
        displayVehicles(matches);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void GetByMakeRequest() {
        String make = getStringInput("Enter vehicle make: ");
        List<Vehicle> matches = dealership.getVehiclesByMake(make);
        displayVehicles(matches);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void GetByModelRequest() {
        String model = getStringInput("Enter vehicle model: ");
        List<Vehicle> matches = dealership.getVehiclesByModel(model);
        displayVehicles(matches);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void GetByYearRequest() {
        int startYear = getYearInput("Enter start year: ");
        int endYear = getYearInput("Enter end year: ");
        List<Vehicle> matches = dealership.getVehiclesByYear(startYear, endYear);
        displayVehicles(matches);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void GetByColorRequest() {
        String color = getStringInput("Enter vehicle color: ");
        List<Vehicle> matches = dealership.getVehiclesByColor(color);
        displayVehicles(matches);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void GetByMileageRequest() {
        int minMileage = getIntInput("Enter minimum mileage: ");
        int maxMileage = getIntInput("Enter maximum mileage: ");
        List<Vehicle> matches = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(matches);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void AllVehiclesRequest() {
        List<Vehicle> all = dealership.getAllVehicles();
        displayVehicles(all);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private void GetByTypeRequest() {
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String type = scanner.next();
        List<Vehicle> matches = dealership.getVehiclesByType(type);
        displayVehicles(matches);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
