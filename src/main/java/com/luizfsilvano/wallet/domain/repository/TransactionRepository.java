package com.luizfsilvano.wallet.domain.repository;

import com.luizfsilvano.wallet.domain.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByWalletIdAndDateTimeBetween(
            Long walletId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );
}
