package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    //readFile()
    //writeFile()
    //method to read csv file
    public static List<Product> readFile() {
        //use file reader and buffered reader to load file

        //loop through file line by line
        //skip first line bc it's the header
        // take each line and split it on the |
        //we need to convert data as needed
        // the price will need conversion
        //create a product object to hold the data
        //put the product in a list
        try {
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //skip first line
            bufferedReader.readLine();
            // read the file
            String input;
            //keep reading lines until there are no more lines to read
            List<Product> productList = new ArrayList<>();
            while ((input = bufferedReader.readLine()) != null) {
               String[] row = input.split("\\|");
               //index 0 is the sku, index 1 is productName, index 2 is the price, index 3 is department
                String sKU = row[0];
                String productName = row[1];
                double price = Double.parseDouble(row[2]);
                String department = row[3];
                Product product = new Product(department, price, productName, sKU);
                productList.add(product);

            }
            return productList;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();

        }
    }
}