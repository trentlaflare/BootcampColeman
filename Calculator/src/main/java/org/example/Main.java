package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("What is the first number?");
        Scanner scanner = new Scanner(System.in);
        int number1 = scanner.nextInt();
        System.out.println("What is the second number?");
        Scanner scanner1 = new Scanner(System.in);
        int number2 = scanner1.nextInt();


        
        System.out.println("Would you like to Add, Sub, Multiply, or Divide");
        Scanner scanner2 = new Scanner(System.in);
        String math = scanner2.nextLine();
        if (math.equalsIgnoreCase("Add")) {
            System.out.println(Math.addExact(number1,number2));


        } else if (math.equalsIgnoreCase("Subtract")) {
            System.out.println(Math.subtractExact(number1, number2));


        } else if (math.equalsIgnoreCase("Multiply")) {
            System.out.println(Math.multiplyExact(number1, number2));


        } else{
            System.out.println(Math.floorDiv(number1, number2));

        }









    }
}