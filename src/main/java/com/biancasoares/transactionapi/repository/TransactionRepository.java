package com.biancasoares.transactionapi.repository;

import com.biancasoares.transactionapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}