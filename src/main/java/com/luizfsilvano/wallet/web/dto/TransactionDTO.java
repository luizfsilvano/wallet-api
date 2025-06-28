package com.luizfsilvano.wallet.web.dto;

import com.luizfsilvano.wallet.domain.model.enums.TransactionStatus;
import com.luizfsilvano.wallet.domain.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TransactionDTO {
    // This is the response DTO for transactions, the data will be sent to the client
    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private LocalDateTime dateTime;
    private BigDecimal walletBalance;
}
