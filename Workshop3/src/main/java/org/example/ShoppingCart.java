package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart(){
        this.products = new ArrayList<>();
    }

    public void addProductToCart(Product product){
        products.add(product);
    }

    public void removeProduct(String sku){
        Product needsRemoving = null;
        //loop
        for(Product product: products){
            if (product.getsKU().equalsIgnoreCase(sku)){
                needsRemoving = product;
            }
        }
        if(needsRemoving != null) {
            products.remove(needsRemoving);
            System.out.println("Confirmed Removal of SKU " + sku);
        } else {
            System.out.println("We couldn't find SKU " + sku + " in cart");
        }
    }

    public double getCartTotal(){
        double total = 0.0;
        for(Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void viewCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (Product product : products) {
                System.out.println(product.getProductName() + " $" + product.getPrice());
                System.out.println("Department: " + product.getDepartment() + " | SKU: " + product.getsKU());
            }
            System.out.printf("Total: $%.2f%n", getCartTotal());
        }
    }

    //Search the cart by name, price, or department
    public void searchCart(String barely) {
        boolean found = false;
        for (Product product : products) {
            if (
                    //or??
                    product.getProductName().toLowerCase().contains(barely.toLowerCase()) ||
                            product.getDepartment().toLowerCase().contains(barely.toLowerCase()) ||
                            String.valueOf(product.getPrice()).contains(barely)
            ) {
                System.out.println(product.getProductName() + " $" + product.getPrice());
                System.out.println("Department: " + product.getDepartment() + " | SKU: " + product.getsKU());
                found = true;
            }
        }
        //what if not found?
        if (!found) {
            System.out.println("No items in your cart matched: " + barely);
        }
    }
    public void printReceipt(double cashPaid) {
        System.out.println(" ");
        System.out.println("=== Receipt ===");
        for (Product product : products) {
            System.out.println(product.getProductName() + " | " + product.getDepartment() + " | $" + product.getPrice() + " | SKU: " + product.getsKU());
        }

        double total = getCartTotal();
        double change = cashPaid - total;

        System.out.printf("Total: $%.2f%n", total);
        System.out.printf("Cash Paid: $%.2f%n", cashPaid);
        System.out.printf("Change: $%.2f%n", change);
        System.out.println("Thank you for your purchase!");
        //clear cart???
        products.clear();
    }
}
