package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String FILE_PATH = "contracts.csv";

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) { // true = append mode
            Vehicle v = contract.getVehicle();
            String line = "";

            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;

                line = "SALE|" +
                        contract.getDate() + "|" +
                        contract.getCustomerName() + "|" +
                        contract.getCustomerEmail() + "|" +
                        v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        String.format("%.2f", v.getPrice()) + "|" +
                        String.format("%.2f", sc.getSalesTaxAmount()) + "|" +
                        String.format("%.2f", sc.getRecordingFee()) + "|" +
                        String.format("%.2f", sc.getProcessingFee()) + "|" +
                        String.format("%.2f", sc.getTotalPrice()) + "|" +
                        (sc.isFinanced() ? "YES" : "NO") + "|" +
                        String.format("%.2f", sc.getMonthlyPayment());

            } else if (contract instanceof LeaseContract) {
                LeaseContract lc = (LeaseContract) contract;

                line = "LEASE|" +
                        contract.getDate() + "|" +
                        contract.getCustomerName() + "|" +
                        contract.getCustomerEmail() + "|" +
                        v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        String.format("%.2f", v.getPrice()) + "|" +
                        String.format("%.2f", lc.getExpectedEndingValue()) + "|" +
                        String.format("%.2f", lc.getLeaseFee()) + "|" +
                        String.format("%.2f", lc.getTotalPrice()) + "|" +
                        String.format("%.2f", lc.getMonthlyPayment());
            }

            line += "\n";
            writer.write(line);

        } catch (IOException e) {
            System.out.println("Error writing contract to file: " + e.getMessage());
        }
    }
}
