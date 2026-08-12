package com.biancasoares.transactionapi.entity;

import jakarta.persistence.*; //Importa as ferramentas do JPA que permitem transformar essa classe Java em uma tabela do banco.

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity //anotacao q diz essa classe representa uma entidade que será salva no banco.
@Table(name = "transactions") //a tabela no banco vai se chamar transactions.
public class Transaction { //declaracao da classe

    @Id //diz esse atributo eh chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //o banco vai gerar automaticamente o ID.
    //cria os atributos
    private Long id;

    private String description;

    private BigDecimal amount;

    private String category;

    private LocalDate date;

    public Transaction() { //construtor vazio pois o  JPA precisa conseguir criar uma Transaction sem fornecer os valores imediatamente.
    }

    public Transaction( //construtor com os parametros fornecidos
            String description,
            BigDecimal amount,
            String category,
            LocalDate date
    ) {
        //pegando o q ta no parametro do construtor e coloque como atributo do objeto
        //atributo = parametro
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}