package org.example;

public class SalesContract extends Contract {
    private boolean isFinanced;
    private double salesTaxAmount;
    private final double recordingFee = 100.0;
    private double processingFee;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicle);
        this.isFinanced = isFinanced;
        this.salesTaxAmount = vehicle.getPrice() * 0.05;
        this.processingFee = vehicle.getPrice() < 10000 ? 295.0 : 495.0;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    @Override
    public double getTotalPrice() {
        return vehicle.getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0.0;
        }

        double loanAmount = getTotalPrice();
        double monthlyPayment;
        if (vehicle.getPrice() >= 10000) {
            // 48 months @ 4.25%
            double rate = 0.0425 / 12;
            int months = 48;
            monthlyPayment = loanAmount * rate / (1 - Math.pow(1 + rate, -months));
        } else {
            // 24 months @ 5.25%
            double rate = 0.0525 / 12;
            int months = 24;
            monthlyPayment = loanAmount * rate / (1 - Math.pow(1 + rate, -months));
        }
        return monthlyPayment;
    }
}
