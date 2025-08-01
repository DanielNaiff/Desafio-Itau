package com.desafioitau.backend.service;

import com.desafioitau.backend.model.TransactionRequestDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class TransactionService {

    private List<TransactionRequestDTO> transactions = new ArrayList<>();

    public void addTransaction(TransactionRequestDTO request){
        transactions.add(request);
        log.info("Transação adicionada: valor={}, dataHora={}", request.valor(), request.dataHora());
    }

    public void deleteAll(){
        transactions.clear();
        log.warn("Todas as transações foram removidas");
    }

    public List<TransactionRequestDTO> searchTransactions(Integer interval) {
        OffsetDateTime offsetDateTime = OffsetDateTime.now().minusSeconds(interval);
        List<TransactionRequestDTO> result = transactions.stream()
                .filter(t -> t.dataHora().isAfter(offsetDateTime))
                .toList();

        log.debug("Buscando transações nos últimos {} segundos. Encontradas: {}", interval, result.size());

        return result;
    }
}
