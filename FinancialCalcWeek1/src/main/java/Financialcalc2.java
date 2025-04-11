import java.util.Scanner;

public class Financialcalc2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your initial deposit?");
        double deposit = scanner.nextDouble();

        System.out.println("What is the annual interest rate?");
        double interestRate = scanner.nextDouble();
        double interest = interestRate/ 100;

        System.out.println("What is the number of years?");
        double years = scanner.nextDouble();

        double fV = deposit * Math.pow(1 + interest/ 365, 365 * years);

        double dailyInterest = interestRate / 365;

        double totalInterest = fV - deposit;

        System.out.printf("Your Future Value is $%.2f "
                + "and your total interest earned is $%.2f", fV, totalInterest);






    }
}
