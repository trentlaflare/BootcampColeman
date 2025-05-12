package org.example;

import java.util.ArrayList;
import java.util.List;

public class DealerShip {
    private String dealership;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public DealerShip(String dealership, String address, String phone) {
        this.dealership = dealership;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Getters
    public String getDealership() {
        return dealership;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    // Add a vehicle
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }

    // Get vehicles by price range
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    // Get vehicles by make
    public List<Vehicle> getVehiclesByMake(String make) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make)) {
                results.add(v);
            }
        }
        return results;
    }

    // Get vehicles by model
    public List<Vehicle> getVehiclesByModel(String model) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getModel().equalsIgnoreCase(model)) {
                results.add(v);
            }
        }
        return results;
    }

    // Get vehicles by year range
    public List<Vehicle> getVehiclesByYear(int startYear, int endYear) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= startYear && v.getYear() <= endYear) {
                results.add(v);
            }
        }
        return results;
    }

    // Get vehicles by color
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                results.add(v);
            }
        }
        return results;
    }

    // Get vehicles by mileage range
    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= minMileage && v.getOdometer() <= maxMileage) {
                results.add(v);
            }
        }
        return results;
    }
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                result.add(vehicle);
            }
        }
        //if true , return null , otherwise return result
        return result.isEmpty() ? null : result;
    }
}
