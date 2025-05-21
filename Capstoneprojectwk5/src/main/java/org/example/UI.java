package org.example;

import java.util.List;
import java.util.Scanner;

public class UI {
    private DealerShip dealership;
    private Scanner scanner;
    private ContractFileManager contractDataManager;

    public UI() {
        scanner = new Scanner(System.in);
        contractDataManager = new ContractFileManager();
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
                case 9:
                    createContractRequest();
                    break;
                case 10:
                    System.out.print("Enter VIN: ");
                    int vin = scanner.nextInt();
                    scanner.nextLine(); // consume leftover newline

                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();

                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();

                    System.out.print("Enter vehicle type: ");
                    String vehicleType = scanner.nextLine();

                    System.out.print("Enter color: ");
                    String color = scanner.nextLine();

                    System.out.print("Enter odometer reading: ");
                    int odometer = scanner.nextInt();

                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicle(newVehicle);
                    System.out.println("Vehicle added to inventory.");
                    break;
                case 11:
                    System.out.println("Enter vin: ");
                    int v = scanner.nextInt();
                    scanner.nextLine();
                    boolean removed = dealership.removeVehicle(v);
                    if (removed){
                        System.out.println("Vehicle Removed from Inventory");
                    }
                    else {
                        System.out.println("Vehicle with vin " + v + "not found.");
                    }
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
            9 - Create Contract (Sale or Lease)
            10 - Add A Vehicle
            11 - Remove Vehicle
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
        scanner.nextLine();
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
    }

    private void GetByMakeRequest() {
        String make = getStringInput("Enter vehicle make: ");
        List<Vehicle> matches = dealership.getVehiclesByMake(make);
        displayVehicles(matches);
    }

    private void GetByModelRequest() {
        String model = getStringInput("Enter vehicle model: ");
        List<Vehicle> matches = dealership.getVehiclesByModel(model);
        displayVehicles(matches);
    }

    private void GetByYearRequest() {
        int startYear = getYearInput("Enter start year: ");
        int endYear = getYearInput("Enter end year: ");
        List<Vehicle> matches = dealership.getVehiclesByYear(startYear, endYear);
        displayVehicles(matches);
    }

    private void GetByColorRequest() {
        String color = getStringInput("Enter vehicle color: ");
        List<Vehicle> matches = dealership.getVehiclesByColor(color);
        displayVehicles(matches);
    }

    private void GetByMileageRequest() {
        int minMileage = getIntInput("Enter minimum mileage: ");
        int maxMileage = getIntInput("Enter maximum mileage: ");
        List<Vehicle> matches = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(matches);
    }

    private void AllVehiclesRequest() {
        List<Vehicle> all = dealership.getAllVehicles();
        displayVehicles(all);
    }

    private void GetByTypeRequest() {
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String type = scanner.next();
        List<Vehicle> matches = dealership.getVehiclesByType(type);
        displayVehicles(matches);
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    private void createContractRequest() {
        String date = getStringInput("Enter contract date (YYYY-MM-DD): ");
        String customerName = getStringInput("Enter customer name: ");
        String customerEmail = getStringInput("Enter customer email: ");

        String vinStr = getStringInput("Enter VIN of vehicle to contract: ");
        int vin;
        try {
            vin = Integer.parseInt(vinStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid VIN format.");
            return;
        }

        List<Vehicle> vehicles = dealership.getVehiclesByVin(vin);
        if (vehicles.isEmpty()) {
            System.out.println("Vehicle with VIN " + vin + " not found.");
            return;
        }

        Vehicle vehicleToSell = vehicles.get(0);

        System.out.print("Enter contract type (sale/lease): ");
        String contractType = scanner.next();

        Contract contract = null;

        if (contractType.trim().equalsIgnoreCase("sale")) {
            System.out.print("Is the sale financed? (yes/no): ");
            String financedInput = scanner.next();
            boolean financed = financedInput.equalsIgnoreCase("yes");

            contract = new SalesContract(date, customerName, customerEmail, vehicleToSell, financed);

        } else if (contractType.equalsIgnoreCase("lease")) {
            // Use the existing constructor - no extra parameters
            contract = new LeaseContract(date, customerName, customerEmail, vehicleToSell);
        } else {
            System.out.println("Invalid contract type.");
            return;
        }

        contractDataManager.saveContract(contract);
        dealership.removeVehicleL(vehicleToSell);
        System.out.println("Contract saved and vehicle removed from inventory.");
        System.out.println(contract.customerName);
        System.out.println(contract.customerEmail);
        System.out.println(contract.date);
        System.out.println(contract.vehicle);

    }
}
