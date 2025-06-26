package com.luizfsilvano.wallet.domain.model;

import com.luizfsilvano.wallet.domain.model.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id @GeneratedValue
    private Long id;
    private BigDecimal amount;
    private String type;
    @ManyToOne
    private Wallet wallet;
    @Column(name="created_at")
    private LocalDateTime dateTime;
    private TransactionStatus status; // poderia ser uma enum? Se sim, onde colocar?
}
