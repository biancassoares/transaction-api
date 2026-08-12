package com.biancasoares.transactionapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
//TransactionRequest representa o que o cliente precisa mandar no JSON para criar uma transação.

public class TransactionRequest {

   @NotBlank (message = "Description is required")
   private String description;
   @NotNull (message = "Amount is required")
   @Positive (message = "Amount must be greater than zero")
   private BigDecimal amount;
   @NotBlank (message = "Category is required")
   private String category;
   @NotNull (message = "Date is required")
   private LocalDate date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
