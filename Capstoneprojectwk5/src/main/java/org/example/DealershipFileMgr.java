package org.example;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileMgr {
    private static final String FILE_NAME = "inventory.csv";

    public DealerShip getDealership() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine(); // First line: dealership info
            if (line == null) return null;

            String[] dealershipData = line.split("\\|");
            DealerShip dealership = new DealerShip(dealershipData[0], dealershipData[1], dealershipData[2]);

            String vehicleLine;
            while ((vehicleLine = reader.readLine()) != null) {
                String[] v = vehicleLine.split("\\|");
                int vin = Integer.parseInt(v[0]);
                int year = Integer.parseInt(v[1]);
                String make = v[2];
                String model = v[3];
                String type = v[4];
                String color = v[5];
                int odometer = Integer.parseInt(v[6]);
                double price = Double.parseDouble(v[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

            return dealership;

        } catch (IOException e) {
            System.out.println("Error reading dealership file: " + e.getMessage());
            return null;
        }
    }
}
