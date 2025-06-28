package com.luizfsilvano.wallet.web.controller;

import com.luizfsilvano.wallet.domain.model.Transaction;
import com.luizfsilvano.wallet.domain.service.TransactionService;
import com.luizfsilvano.wallet.web.dto.CreateTransactionDTO;
import com.luizfsilvano.wallet.web.dto.TransactionDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    // Definition of endpoints for transaction management will go here

    // Injecting the TransactionService to handle transaction-related operations
    private final TransactionService service;

    // Constructor injection for TransactionService
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // Defining endpoints for creating, retrieving, updating, and deleting transactions
    @PostMapping
    public ResponseEntity<TransactionDTO> create(
            @Valid @RequestBody CreateTransactionDTO dto) {
        Transaction tx = service.createTransaction(dto);

        // Mapping the Transaction entity to TransactionDTO
        TransactionDTO response = new TransactionDTO(
                tx.getId(),
                tx.getAmount(),
                tx.getType(),
                tx.getStatus(),
                tx.getDateTime(),
                tx.getWallet().getBalance()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/wallet/{id}")
    public List<TransactionDTO> listByWallet(@PathVariable Long id) {
        List<Transaction> txs = service.getByWallet(id);
        return txs.stream()
                .map(tx -> new TransactionDTO(
                        tx.getId(),
                        tx.getAmount(),
                        tx.getType(),
                        tx.getStatus(),
                        tx.getDateTime(),
                        tx.getWallet().getBalance()))
                .toList();
    }
}
