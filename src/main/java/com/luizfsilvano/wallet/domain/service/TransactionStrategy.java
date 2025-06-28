package com.luizfsilvano.wallet.domain.service;

import com.luizfsilvano.wallet.domain.model.Transaction;
import com.luizfsilvano.wallet.domain.model.Wallet;

import java.math.BigDecimal;

public interface TransactionStrategy {
    BigDecimal apply(Transaction transaction, Wallet wallet);
}
