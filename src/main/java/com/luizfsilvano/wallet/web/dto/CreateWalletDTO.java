package com.luizfsilvano.wallet.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateWalletDTO {
    @NotNull
    @Positive
    private BigDecimal balance;
}
