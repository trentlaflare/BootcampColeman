package org.example;

public class Product {
    private String sKU;
    private String productName;
    private double price;
    private String department;

    public Product(String department, double price, String productName, String sKU) {
        this.sKU = sKU;
        this.productName = productName;
        this.price = price;
        this.department = department;
    }
    public String getsKU() {
        return sKU;
    }

    public void setsKU(String sKU) {
        this.sKU = sKU;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }




    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
