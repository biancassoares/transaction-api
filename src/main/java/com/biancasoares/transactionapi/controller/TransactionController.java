package com.biancasoares.transactionapi.controller;

import com.biancasoares.transactionapi.dto.TransactionRequest;
import com.biancasoares.transactionapi.dto.TransactionResponse;
import com.biancasoares.transactionapi.entity.Transaction; //pq recebe/ devolve transactions
import com.biancasoares.transactionapi.service.TransactionService;// pq vai chamar o service
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus; //usado pra retornar status do http
import org.springframework.web.bind.annotation.*; //importa varias anotacoes do spring

import java.util.ArrayList;
import java.util.List;

@RestController //anotacao q diz essa classe recebe requisições HTTP e devolve respostas HTTP, normalmente em JSON.
@RequestMapping("/transactions") //define o caminho base
public class TransactionController { //declara o controller

    private final TransactionService service; //controller precisa do service

    public TransactionController(TransactionService service) { //injecao de dependencia
        this.service = service;
    }

    @GetMapping //GET /transactions
    public List<TransactionResponse> getAll() { //endpoint retorna 1 lista
        return  service.findAll();
    }

    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable Long id){
        return  service.findById(id);
    }
    @PostMapping //POST /transactions
    public TransactionResponse create(@Valid @RequestBody TransactionRequest request) { // entra transactionrequest e retorno um transaction
        return service.save(request); //entrega p/ service salvar
    }

    @PutMapping("/{id}") //PUT /transaction {id}
    public TransactionResponse update( //metodo pra atualizar
            @PathVariable Long id, //anotacao q pega o id q veio da url
            @RequestBody TransactionRequest request //anotacao q transaforma o json enviado no body em Transaction
    ) {
        return service.update(id, request); //entrega os dois p/ o service
    }

    @DeleteMapping("/{id}") //DELETE /transactions/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT) //feito a exclusao retorna NO CONTENT
    public void delete(@PathVariable Long id) { //pega o id da url
        service.delete(id); //entrega ao service
    }
}