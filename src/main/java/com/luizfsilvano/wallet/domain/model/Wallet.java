package com.luizfsilvano.wallet.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal balance;

    // Relation with User
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
