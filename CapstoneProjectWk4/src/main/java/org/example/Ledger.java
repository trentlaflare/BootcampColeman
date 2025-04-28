package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class Ledger {
    private List<Transaction> transactions;

    public Ledger() {
        transactions = TransactionFileMgr.loadTransactions();
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getDeposits() {
        return transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .collect(Collectors.toList());
    }

    public List<Transaction> getPayments() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.toList());
    }
}

