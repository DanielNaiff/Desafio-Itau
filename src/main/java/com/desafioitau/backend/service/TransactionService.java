package com.desafioitau.backend.service;

import com.desafioitau.backend.model.TransactionRequestDTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service

public class TransactionService {

    private List<TransactionRequestDTO> transactions = new CopyOnWriteArrayList<>();

    public void addTransaction(TransactionRequestDTO request){
        transactions.add(request);
    }

    public void deleteAll(){
        transactions.clear();
    }
}
