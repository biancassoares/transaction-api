package com.biancasoares.transactionapi.service;

import com.biancasoares.transactionapi.dto.TransactionRequest;
import com.biancasoares.transactionapi.dto.TransactionResponse;
import com.biancasoares.transactionapi.entity.Transaction; //pq manipula transactions
import com.biancasoares.transactionapi.exception.TransactionNotFoundException;
import com.biancasoares.transactionapi.repository.TransactionRepository; //pq conversa com banco atraves do repository
import org.springframework.http.HttpStatus; //pra retornar resposta http
import org.springframework.stereotype.Service; //permite usar anotacao @service
import org.springframework.transaction.annotation.Transactional; //e @trnasactional
import org.springframework.web.server.ResponseStatusException; //permite lancar um erro http

import java.util.ArrayList;
import java.util.List;// permite retornar varias transactions


@Service //anotacao de service, permite q o spring injete essa classe no controller
public class TransactionService { //declara o service

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) { //injecao de dependencia
        this.repository = repository;
    }

    public List<TransactionResponse> findAll() { //declara um metodo q retorna a lista de transaction
        List<Transaction> transactions = repository.findAll();
        List<TransactionResponse> responses = new ArrayList<>();

        for (Transaction transaction : transactions){

            TransactionResponse response = new TransactionResponse();

            response.setId(transaction.getId());
            response.setDescription(transaction.getDescription());
            response.setAmount(transaction.getAmount());
            response.setCategory(transaction.getCategory());
            response.setDate(transaction.getDate());

            responses.add(response);
        }

        return responses;
    }

    public TransactionResponse findById(Long id){
        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(()-> new TransactionNotFoundException(
                        "Transaction not found"
                ));
        TransactionResponse response = new TransactionResponse();

        response.setId(existingTransaction.getId());
        response.setDescription(existingTransaction.getDescription());
        response.setAmount(existingTransaction.getAmount());
        response.setCategory(existingTransaction.getCategory());
        response.setDate(existingTransaction.getDate());
        return response;
    }

    public TransactionResponse save(TransactionRequest request) {

        //setter da entidade recebendo getter do DTO
        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setCategory(request.getCategory());
        transaction.setDate(request.getDate());

        Transaction savedTransaction = repository.save(transaction);


        TransactionResponse response = new TransactionResponse();
        response.setDescription(savedTransaction.getDescription());
        response.setAmount(savedTransaction.getAmount());
        response.setCategory(savedTransaction.getCategory());
        response.setDate(savedTransaction.getDate());
        response.setId(savedTransaction.getId());

        return response;
    }

    @Transactional
    public TransactionResponse update(Long id, TransactionRequest request) {

        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Transaction not found"
                ));

        existingTransaction.setDescription(request.getDescription());
        existingTransaction.setAmount(request.getAmount());
        existingTransaction.setCategory(request.getCategory());
        existingTransaction.setDate(request.getDate());

        Transaction updatedTransaction = repository.save(existingTransaction);

        TransactionResponse response = new TransactionResponse();
        response.setId(updatedTransaction.getId());
        response.setDescription(updatedTransaction.getDescription());
        response.setAmount(updatedTransaction.getAmount());
        response.setCategory(updatedTransaction.getCategory());
        response.setDate(updatedTransaction.getDate());

        return response;
    }

    @Transactional
    public void delete(Long id) {//recebe o ID q sera apagado

        Transaction existingTransaction = repository.findById(id) //procura a id
                .orElseThrow(() -> new TransactionNotFoundException(
                        "Transaction not found"
                ));

        repository.delete(existingTransaction); //se existe deleta
    }
}