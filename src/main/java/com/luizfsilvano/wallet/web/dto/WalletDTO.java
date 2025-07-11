package com.luizfsilvano.wallet.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class WalletDTO {
    private Long id;
    private BigDecimal balance;
}
