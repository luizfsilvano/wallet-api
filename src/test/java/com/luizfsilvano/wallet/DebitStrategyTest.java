package com.luizfsilvano.wallet;

import com.luizfsilvano.wallet.domain.model.Transaction;
import com.luizfsilvano.wallet.domain.model.Wallet;
import com.luizfsilvano.wallet.domain.model.enums.TransactionType;
import com.luizfsilvano.wallet.domain.service.DebitStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DebitStrategyTest {
    private DebitStrategy strategy;
    private Wallet wallet;
    private Transaction transaction;

    // Setup for arranges
    @BeforeEach
    void SetUp() {
        strategy = new DebitStrategy();

        wallet = new Wallet();
        wallet.setBalance(new BigDecimal("100.00"));

        transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
    }

    @Test
    void apply_shouldDebitAmountFromWallet_whenDebitIsSuccessful() {
        // act
        transaction.setAmount(new BigDecimal("50.00"));

        BigDecimal newBalance = strategy.apply(transaction, wallet);

        // Assert
        BigDecimal expectedBalance = new BigDecimal("50.00");
        assertEquals(expectedBalance, newBalance);
    }

    @Test
    void apply_shouldThrowException_whenInsufficientFunds() {
        // Act
        transaction.setAmount(new BigDecimal("101.00"));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.apply(transaction, wallet);
        });
    }

    @Test
    void apply_shouldThrowException_whenDebitIsNegative() {
        // Act
        transaction.setAmount(new BigDecimal("-50.00"));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.apply(transaction, wallet);
        });
    }

    @Test
    void apply_shouldThrowException_whenDebitValueIsZero() {
        // Act
        transaction.setAmount(new BigDecimal("0.00"));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.apply(transaction, wallet);
        });
    }

    @Test
    void apply_shouldThrowException_whenTransactionTypeIsCredit() {
        // Arrange
        transaction.setType(TransactionType.CREDIT);

        // Act
        transaction.setAmount(new BigDecimal("50.00"));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.apply(transaction, wallet);
        });
    }
}
