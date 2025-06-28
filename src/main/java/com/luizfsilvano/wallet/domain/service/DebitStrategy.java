package com.luizfsilvano.wallet.domain.service;

import com.luizfsilvano.wallet.domain.model.Transaction;
import com.luizfsilvano.wallet.domain.model.Wallet;
import com.luizfsilvano.wallet.domain.model.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("DEBIT")
public class DebitStrategy implements TransactionStrategy {
    // Checks if the transaction type is DEBIT, validates the amount, and checks for sufficient funds in the wallet.
    @Override
    public BigDecimal apply(Transaction tx, Wallet w) {
        if (tx.getType() != TransactionType.DEBIT) {
            throw new IllegalArgumentException("Transaction type must be DEBIT");
        }

        if (tx.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive for DEBIT transactions");
        }

        BigDecimal newBalance = w.getBalance().subtract(tx.getAmount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient funds for this transaction");
        }

        return newBalance;
    }
}
