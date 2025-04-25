package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("");
            System.out.println("=== Welcome to The Small Stop Shop ===");
            System.out.println("Choose an option:");
            System.out.println("1) Display Products");
            System.out.println("2) Display Cart");
            System.out.println("3) Add Product to Cart");
            System.out.println("4) Remove Product from Cart");
            System.out.println("5) View Cart Total");
            System.out.println("6) Search Products");
            System.out.println("7) Search Cart");
            System.out.println("8) Exit");

            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1:
                    List<Product> products = FileLoader.readFile();
                    for (int i = 0; i < products.size(); i++) {
                        Product product = products.get(i);
                        System.out.println((i + 1) + ") " + product.getProductName() + " - $" + product.getPrice());
                        System.out.println("   Department: " + product.getDepartment() + " | SKU: " + product.getsKU());
                    }
                    break;

                case 2:
                    cart.printCartContents();
                    break;

                case 3:
                    System.out.println("Enter the SKU of the product you want to add to the cart:");
                    String addSku = scanner.nextLine();
                    List<Product> availableProducts = FileLoader.readFile();
                    boolean foundProduct = false;

                    for (Product product : availableProducts) {
                        if (product.getsKU().equalsIgnoreCase(addSku)) {
                            cart.addProductToCart(product);
                            System.out.println(product.getProductName() + " has been added to your cart.");
                            foundProduct = true;
                            break;
                        }
                    }

                    if (!foundProduct) {
                        System.out.println("Product with SKU " + addSku + " not found.");
                    }
                    break;

                case 4:
                    System.out.println("Enter the SKU of the product you want to remove from the cart:");
                    String removeSku = scanner.nextLine();
                    cart.removeProduct(removeSku);
                    break;

                case 5:
                    System.out.printf("Your cart total is: $%.2f%n", cart.getCartTotal());
                    break;

                case 6:
                    System.out.println("Enter product name, department, or price to search:");
                    String searchItems = scanner.nextLine();
                    List<Product> searchProducts = FileLoader.readFile();
                    boolean searchMatch = false;

                    for (Product product : searchProducts) {
                        //or not and here
                        if (
                                product.getProductName().toLowerCase().contains(searchItems.toLowerCase()) ||
                                        product.getDepartment().toLowerCase().contains(searchItems.toLowerCase()) ||
                                        String.valueOf(product.getPrice()).contains(searchItems)
                        ) {
                            System.out.println(product.getProductName() + " $" + product.getPrice());
                            System.out.println("Department: " + product.getDepartment() + " | SKU: " + product.getsKU());
                            searchMatch = true;
                        }
                    }

                    if (!searchMatch) {
                        System.out.println("No products matched your search.");
                    }
                    break;

                case 7:
                    System.out.println("Enter a name, department, or price to search within your cart:");
                    String cartQuery = scanner.nextLine();
                    cart.searchCart(cartQuery);
                    break;

                case 8:
                    System.out.println("Thanks for shopping with us!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

//mainMenu
        //searchProduct


