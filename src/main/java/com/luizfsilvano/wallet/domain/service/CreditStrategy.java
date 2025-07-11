package com.luizfsilvano.wallet.domain.service;

import com.luizfsilvano.wallet.domain.model.Transaction;
import com.luizfsilvano.wallet.domain.model.Wallet;
import com.luizfsilvano.wallet.domain.model.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("CREDIT")
public class CreditStrategy implements TransactionStrategy {

    @Override
    public BigDecimal apply(Transaction tx, Wallet w) {

        // Validate that the transaction type is CREDIT
        if (tx.getType() != TransactionType.CREDIT) {
            throw new IllegalArgumentException("Transaction type must be CREDIT");
        }

        // Validate that the amount is positive
        if (tx.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive for CREDIT transactions");
        }

        // No need to check for insufficient funds since CREDIT transactions always increase the balance
        // For CREDIT transactions, we simply add the amount to the wallet's balance
        return w.getBalance().add(tx.getAmount());
    }
}
