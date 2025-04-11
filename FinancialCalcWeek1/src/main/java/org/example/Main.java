package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("How much is your initial deposit?");
        Scanner scanner = new Scanner(System.in);
        int initialDeposit = scanner.nextInt();

        scanner.nextLine();
        System.out.println("What is your interest rate?");
        float interest = scanner.nextFloat();
        double months = 12;

        scanner.nextLine();
        System.out.println("What is your loan length(years)?");
        int loanLength = scanner.nextInt();
        float monthlyInterestRate = (float) ((interest/ months)/ 100);
        int monthlyPayments = Math.addExact(loanLength, (int) months);






    }
}