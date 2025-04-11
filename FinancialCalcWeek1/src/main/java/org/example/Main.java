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
        double months = 12;

        scanner.nextLine();
        System.out.println("What is your loan length(years)?");
        double loanLength = scanner.nextDouble();
        double monthlyInterestRate = ((interest/ months)/ 100);
        int numberOfMonthlyPayments = Math.multiplyExact((int) loanLength, (int) months);
        double l=1+ monthlyInterestRate;
        double v = principle * l;
        double c = loanLength * 12;

        double n = Math.pow(l,c);
        double right = n - 1;
        double left = n * principle;
        double m = left / right;
        double totalInterest = (m * numberOfMonthlyPayments) - principle;
        System.out.printf("Your interest is %f and your monthly payment is %f", totalInterest, m);
}
}



