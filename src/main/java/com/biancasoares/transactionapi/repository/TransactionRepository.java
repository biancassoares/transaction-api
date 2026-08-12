package com.biancasoares.transactionapi.repository;

import com.biancasoares.transactionapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository; //jpa repository ja possui varios metodos prontos

public interface TransactionRepository extends JpaRepository<Transaction, Long> //crie um Repository que trabalha com Transaction, cujo ID é do tipo Long.
{
    //Está vazio porque neste momento você não precisa criar consultas personalizadas.
    //O Spring já fornece o CRUD básico.
}