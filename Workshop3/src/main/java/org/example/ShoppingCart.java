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

    public void printCartContents() {
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
}
