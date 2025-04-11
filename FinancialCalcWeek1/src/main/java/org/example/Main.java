package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("How much is your initial deposit?");
        Scanner scanner = new Scanner(System.in);
        int initialDeposit = scanner.nextInt();

        scanner.nextLine();
        System.out.println("What is your interest rate?");
        int interest = scanner.nextInt();

        scanner.nextLine();
        System.out.println("What is your loan length");

    }
}