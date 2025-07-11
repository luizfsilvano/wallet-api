package com.luizfsilvano.wallet.domain.repository;

import com.luizfsilvano.wallet.domain.model.User;
import com.luizfsilvano.wallet.domain.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

public interface WalletRepository extends    JpaRepository<Wallet, Long> {
    List<Wallet> findByUserId(Long userId);
}
