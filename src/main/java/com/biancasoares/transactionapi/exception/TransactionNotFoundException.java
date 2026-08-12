package com.biancasoares.transactionapi.exception;


public class TransactionNotFoundException extends  RuntimeException{

    public TransactionNotFoundException( String message) {
        super(message);
    }
}

