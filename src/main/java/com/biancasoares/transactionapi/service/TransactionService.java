package com.biancasoares.transactionapi.service;

import com.biancasoares.transactionapi.entity.Transaction;
import com.biancasoares.transactionapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;


    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }


    public List<Transaction> findAll() {
        return repository.findAll();
    }


    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }
}