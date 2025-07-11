package com.luizfsilvano.wallet.domain.service;

import com.luizfsilvano.wallet.domain.model.Transaction;
import com.luizfsilvano.wallet.domain.model.Wallet;
import com.luizfsilvano.wallet.domain.model.enums.TransactionStatus;
import com.luizfsilvano.wallet.domain.repository.TransactionRepository;
import com.luizfsilvano.wallet.domain.repository.WalletRepository;
import com.luizfsilvano.wallet.web.dto.CreateTransactionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final WalletRepository walletRepo;
    private final TransactionRepository transactionRepo;
    private final Map<String, TransactionStrategy> strategies;

    public TransactionService(WalletRepository walletRepo,
                              TransactionRepository transactionRepo,
                              Map<String, TransactionStrategy> strategies) {
        this.walletRepo = walletRepo;
        this.transactionRepo = transactionRepo;
        this.strategies = strategies;
    }

    @Transactional
    public Transaction createTransaction(CreateTransactionDTO dto) {
        // Search for the wallet by ID
        Wallet wallet = walletRepo.findById(dto.getWalletId())
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found"));

        // Convert DTO to Transaction entity
        Transaction tx = new Transaction();
        tx.setAmount(dto.getAmount());
        tx.setType(dto.getType());
        tx.setWallet(wallet);
        tx.setDateTime(LocalDateTime.now());
        // Status set by the strategy

        // Apply the strategy and save the transaction
        BigDecimal newBalance = strategies
                .get(dto.getType().name())
                .apply(tx, wallet);
        wallet.setBalance(newBalance);

        walletRepo.save(wallet);
        tx.setStatus(TransactionStatus.COMPLETED); // Assuming success for now
        transactionRepo.save(tx);

        return tx;
    }

    public Page<Transaction> getByWallet(Long walletId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        // Retrieve transactions by wallet ID and date range
        return transactionRepo.findByWalletIdAndDateTimeBetween(walletId, startDate, endDate, pageable);
    }

}
