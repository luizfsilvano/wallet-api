package com.luizfsilvano.wallet.domain.model;

import com.luizfsilvano.wallet.domain.model.enums.TransactionStatus;
import com.luizfsilvano.wallet.domain.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    private Wallet wallet;

    @Column(name="created_at")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
