package com.luizfsilvano.wallet.domain.model;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Wallet {
    @Id @GeneratedValue
    private Long id;
    private BigDecimal balance;
}
