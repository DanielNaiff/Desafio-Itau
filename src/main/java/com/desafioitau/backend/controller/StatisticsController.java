package com.desafioitau.backend.controller;

import com.desafioitau.backend.model.StatisticsResponseDTO;
import com.desafioitau.backend.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsResponseDTO> statistics(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer interval){

        StatisticsResponseDTO response=  statisticsService.statistics(interval);

        return ResponseEntity.ok(response);
    }
}
