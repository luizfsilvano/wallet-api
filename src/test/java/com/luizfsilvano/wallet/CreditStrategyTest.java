package com.luizfsilvano.wallet;

import com.luizfsilvano.wallet.domain.model.Transaction;
import com.luizfsilvano.wallet.domain.model.Wallet;
import com.luizfsilvano.wallet.domain.model.enums.TransactionType;
import com.luizfsilvano.wallet.domain.service.CreditStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreditStrategyTest {

    private CreditStrategy strategy;
    private Wallet wallet;
    private Transaction transaction;

    // Before each class for arrange
    @BeforeEach
    void setUp() {
        strategy = new CreditStrategy();

        wallet = new Wallet();
        wallet.setBalance(new BigDecimal("100.00"));

        transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
    }

    @Test
    void apply_shouldAddAmountToWallet_whenCreditIsPositive() {
        // Arrange
        transaction.setAmount(new BigDecimal("50.00"));

        // Act
        BigDecimal newBalance = strategy.apply(transaction, wallet);

        // Assert
        BigDecimal expectedBalance = new BigDecimal("150.00");
        assertEquals(expectedBalance, newBalance);
    }

    @Test
    void apply_shouldThrowException_whenAmountIsNegative() {
        // Act
        transaction.setAmount(new BigDecimal("-100"));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.apply(transaction, wallet);
        });
    }

    @Test
    void apply_shouldThrowException_whenAmountIsZero() {
        // Act
        transaction.setAmount(new BigDecimal("0"));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.apply(transaction, wallet);
        });
    }

    @Test
    void apply_shouldThrowException_whenTransactionTypeIsDebit() {
        // Arrange
        transaction.setType(TransactionType.DEBIT);

        // Act
        transaction.setAmount(new BigDecimal("100"));

        // Assert
        assertThrows(IllegalArgumentException.class, () ->{
            strategy.apply(transaction, wallet);
        });
    }
}
