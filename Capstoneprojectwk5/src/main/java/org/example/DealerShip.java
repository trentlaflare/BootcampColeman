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

    public String getDealership() {
        return dealership;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByMake(String make) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make.trim())) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByModel(String model) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getModel().equalsIgnoreCase(model.trim())) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByYear(int startYear, int endYear) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= startYear && v.getYear() <= endYear) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color.trim())) {
                results.add(v);
            }
        }
        return results;
    }

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
            if (vehicle.getVehicleType().equalsIgnoreCase(type.trim())) {
                result.add(vehicle);
            }
        }
        return result; // Safe to return empty list
    }

    public List<Vehicle> getVehiclesByVin(int vin) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVin() == vin) {
                results.add(v);
            }
        }
        return results;
    }


    public boolean removeVehicle(Vehicle vehicle) {
        return inventory.remove(vehicle);
    }
}
