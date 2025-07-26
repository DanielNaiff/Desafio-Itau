package com.desafioitau.backend.controller;

import com.desafioitau.backend.model.TransactionRequestDTO;
import com.desafioitau.backend.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody @Valid TransactionRequestDTO requestDTO){
        transactionService.addTransaction(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){

        transactionService.deleteAll();

        return ResponseEntity.ok().build();
    }
 }
