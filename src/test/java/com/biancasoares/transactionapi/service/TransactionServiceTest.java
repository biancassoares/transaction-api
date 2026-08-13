package com.biancasoares.transactionapi.service;

import com.biancasoares.transactionapi.dto.TransactionRequest;
import com.biancasoares.transactionapi.dto.TransactionResponse;
import com.biancasoares.transactionapi.entity.Transaction;
import com.biancasoares.transactionapi.exception.TransactionNotFoundException;
import com.biancasoares.transactionapi.repository.TransactionRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;


    // 1 - Busca uma transaction existente
    @Test
    void shouldFindTransactionById() {

        // ARRANGE
        Transaction transaction = new Transaction();
        transaction.setDescription("Mercado");
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setCategory("Alimentação");
        transaction.setDate(LocalDate.of(2026, 8, 12));

        when(repository.findById(1L))
                .thenReturn(Optional.of(transaction));

        // ACT
        TransactionResponse response = service.findById(1L);

        // ASSERT
        assertEquals("Mercado", response.getDescription());
        assertEquals(new BigDecimal("100.00"), response.getAmount());
        assertEquals("Alimentação", response.getCategory());
        assertEquals(LocalDate.of(2026, 8, 12), response.getDate());
    }


    // 2 - Busca uma transaction que não existe
    @Test
    void shouldThrowExceptionWhenTransactionNotFound() {

        // ARRANGE
        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(
                TransactionNotFoundException.class,
                () -> service.findById(999L)
        );
    }


    // 3 - Cria uma transaction
    @Test
    void shouldSaveTransaction() {

        // ARRANGE
        TransactionRequest request = new TransactionRequest();
        request.setDescription("Academia");
        request.setAmount(new BigDecimal("150.00"));
        request.setCategory("Saúde");
        request.setDate(LocalDate.of(2026, 8, 12));

        Transaction savedTransaction = new Transaction();
        savedTransaction.setDescription("Academia");
        savedTransaction.setAmount(new BigDecimal("150.00"));
        savedTransaction.setCategory("Saúde");
        savedTransaction.setDate(LocalDate.of(2026, 8, 12));

        when(repository.save(any(Transaction.class)))
                .thenReturn(savedTransaction);

        // ACT
        TransactionResponse response = service.save(request);

        // ASSERT
        assertEquals("Academia", response.getDescription());
        assertEquals(new BigDecimal("150.00"), response.getAmount());
        assertEquals("Saúde", response.getCategory());
        assertEquals(LocalDate.of(2026, 8, 12), response.getDate());

        verify(repository).save(any(Transaction.class));
    }


    // 4 - Busca todas as transactions
    @Test
    void shouldFindAllTransactions() {

        // ARRANGE
        Transaction transaction1 = new Transaction();
        transaction1.setDescription("Mercado");
        transaction1.setAmount(new BigDecimal("100.00"));
        transaction1.setCategory("Alimentação");
        transaction1.setDate(LocalDate.of(2026, 8, 12));

        Transaction transaction2 = new Transaction();
        transaction2.setDescription("Academia");
        transaction2.setAmount(new BigDecimal("150.00"));
        transaction2.setCategory("Saúde");
        transaction2.setDate(LocalDate.of(2026, 8, 13));

        when(repository.findAll())
                .thenReturn(List.of(transaction1, transaction2));

        // ACT
        List<TransactionResponse> responses = service.findAll();

        // ASSERT
        assertEquals(2, responses.size());

        assertEquals("Mercado", responses.get(0).getDescription());
        assertEquals("Academia", responses.get(1).getDescription());
    }


    // 5 - Atualiza uma transaction
    @Test
    void shouldUpdateTransaction() {

        // ARRANGE
        Transaction existingTransaction = new Transaction();
        existingTransaction.setDescription("Mercado");
        existingTransaction.setAmount(new BigDecimal("100.00"));
        existingTransaction.setCategory("Alimentação");
        existingTransaction.setDate(LocalDate.of(2026, 8, 12));

        TransactionRequest request = new TransactionRequest();
        request.setDescription("Supermercado");
        request.setAmount(new BigDecimal("200.00"));
        request.setCategory("Compras");
        request.setDate(LocalDate.of(2026, 8, 13));

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingTransaction));

        when(repository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        TransactionResponse response = service.update(1L, request);

        // ASSERT
        assertEquals("Supermercado", response.getDescription());
        assertEquals(new BigDecimal("200.00"), response.getAmount());
        assertEquals("Compras", response.getCategory());
        assertEquals(LocalDate.of(2026, 8, 13), response.getDate());

        verify(repository).save(existingTransaction);
    }


    // 6 - Deleta uma transaction
    @Test
    void shouldDeleteTransaction() {

        // ARRANGE
        Transaction transaction = new Transaction();

        when(repository.findById(1L))
                .thenReturn(Optional.of(transaction));

        // ACT
        service.delete(1L);

        // ASSERT
        verify(repository).delete(transaction);
    }
}