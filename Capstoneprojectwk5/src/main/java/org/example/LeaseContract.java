package org.example;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;
    private double monthlyPayment;

    private static final int LEASE_MONTHS = 36;
    private static final double LEASE_INTEREST_RATE = 0.04; // 4.0%

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        double originalPrice = vehicle.getPrice();
        this.expectedEndingValue = originalPrice * 0.5;
        this.leaseFee = originalPrice * 0.07;
        this.monthlyPayment = calculateMonthlyPayment(originalPrice);
    }

    private double calculateMonthlyPayment(double originalPrice) {
        double amountToFinance = (originalPrice - expectedEndingValue) + leaseFee;

        double monthlyInterestRate = LEASE_INTEREST_RATE / 12.0;

        double numerator = amountToFinance * monthlyInterestRate;
        double denominator = 1 - Math.pow(1 + monthlyInterestRate, -LEASE_MONTHS);
        return numerator / denominator;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return (monthlyPayment * LEASE_MONTHS) + leaseFee;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "\nLease Contract: Expected Ending Value: $%.2f, Lease Fee: $%.2f, Monthly Payment: $%.2f",
                expectedEndingValue, leaseFee, monthlyPayment);
    }
}
