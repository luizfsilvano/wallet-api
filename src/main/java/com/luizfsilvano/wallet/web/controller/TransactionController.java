package com.luizfsilvano.wallet.web.controller;

import com.luizfsilvano.wallet.domain.model.Transaction;
import com.luizfsilvano.wallet.domain.service.TransactionService;
import com.luizfsilvano.wallet.web.dto.CreateTransactionDTO;
import com.luizfsilvano.wallet.web.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    // Injecting the TransactionService to handle transaction-related operations
    private final TransactionService service;

    // Constructor injection for TransactionService
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // Defining endpoints for creating, retrieving, updating, and deleting transactions
    @PostMapping
    public ResponseEntity<TransactionDTO> create(@Valid @RequestBody CreateTransactionDTO dto) {
        Transaction tx = service.createTransaction(dto);

        // Mapping the Transaction entity to TransactionDTO
        TransactionDTO response = new TransactionDTO(tx.getId(), tx.getAmount(), tx.getType(), tx.getStatus(), tx.getDateTime(), tx.getWallet().getBalance());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/wallet/{id}")
    public Page<TransactionDTO> listByWallet(@PathVariable Long id, @RequestParam(defaultValue = "1970-01-01T00:00:00") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam(defaultValue = "9999-12-31T23:59:59") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, Pageable pageable) {
        Page<Transaction> txsPage = service.getByWallet(id, startDate, endDate, pageable);
        return txsPage.map(tx -> new TransactionDTO(tx.getId(), tx.getAmount(), tx.getType(), tx.getStatus(), tx.getDateTime(), tx.getWallet().getBalance()));
    }
}
