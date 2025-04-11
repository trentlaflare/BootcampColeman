package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("How much is your Principle?");
        Scanner scanner = new Scanner(System.in);
        double principle = scanner.nextDouble();

        scanner.nextLine();
        System.out.println("What is your interest rate?");
        double interest = scanner.nextDouble();
        double r = ((interest / 12) /100);


        scanner.nextLine();
        System.out.println("What is your loan length(years)?");
        double y = scanner.nextDouble();
        int n = (int) (y*12);
        double c = 1 + r;
        double b = Math.pow(c,n);
        double l = b * r;
        double left = principle * l;
        double right = b - 1;
        double m = left / right;


        double totalInterest = (m * n) - principle;
        System.out.printf("Your interest is $%.2f and your monthly payment is $%.2f", totalInterest, m);
}
}
//


