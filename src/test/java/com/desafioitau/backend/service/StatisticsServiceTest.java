package com.desafioitau.backend.service;

import com.desafioitau.backend.model.StatisticsResponseDTO;
import com.desafioitau.backend.model.TransactionRequestDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {


    @InjectMocks
    TransactionService transacaoService;

    TransactionRequestDTO transacao;

    StatisticsResponseDTO estatisticas;

    @BeforeEach
    void setUp(){
        transacao = new TransactionRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new StatisticsResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void mustAddSuccess(){
        transacaoService.addTransaction(transacao);

        List<TransactionRequestDTO> transactions = transacaoService.searchTransactions(100);

        assertTrue(transactions.contains(transacao));
    }

   @Test
    void mustCleanTransaction(){
        transacaoService.deleteAll();

        List<TransactionRequestDTO> transactions = transacaoService.searchTransactions(100);

        assertTrue(transactions.isEmpty());
   }

   @Test
    void mustSearchTransactionInTime(){
       TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

       transacaoService.addTransaction(transacao);
       transacaoService.addTransaction(transactionRequestDTO);

       List<TransactionRequestDTO> transactions = transacaoService.searchTransactions(60);

       assertTrue(transactions.contains(transacao));
       assertFalse(transactions.contains(transactionRequestDTO));
   }
}
