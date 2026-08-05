package com.biancasoares.transactionapi.controller;

import com.biancasoares.transactionapi.entity.Transaction;
import com.biancasoares.transactionapi.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;


    public TransactionController(TransactionService service) {
        this.service = service;
    }


    @GetMapping
    public List<Transaction> getAll() {
        return service.findAll();
    }


    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return service.save(transaction);
    }
}