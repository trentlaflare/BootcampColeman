package org.example;

import java.util.Scanner;

public class FinancialCalc2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your monthly payment amount?");
        double pmt = scanner.nextDouble();

        System.out.println("What is your expected interest rate?");
        double r = scanner.nextDouble();

        System.out.println("How many years to pay out?");
        double t = scanner.nextDouble();

        double monthlyInterest = (r / 12)/ 100;
        double monthlyPayment = t * 12;

        double a = 1 - Math.pow((1 + monthlyInterest), (-monthlyPayment));
        double b = a / monthlyInterest;
        double pV = pmt * b;
        System.out.printf("Your present value is $%.2f", pV);

    }
}
