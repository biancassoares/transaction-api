package com.biancasoares.transactionapi.service;

import com.biancasoares.transactionapi.entity.Transaction;
import com.biancasoares.transactionapi.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public Transaction update(Long id, Transaction updatedTransaction) {

        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Transaction not found"
                ));

        existingTransaction.setDescription(
                updatedTransaction.getDescription()
        );

        existingTransaction.setAmount(
                updatedTransaction.getAmount()
        );

        existingTransaction.setCategory(
                updatedTransaction.getCategory()
        );

        existingTransaction.setDate(
                updatedTransaction.getDate()
        );

        return repository.save(existingTransaction);
    }

    @Transactional
    public void delete(Long id) {

        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Transaction not found"
                ));

        repository.delete(existingTransaction);
    }
}