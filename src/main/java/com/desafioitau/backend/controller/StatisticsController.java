package com.desafioitau.backend.controller;

import com.desafioitau.backend.model.StatisticsResponseDTO;
import com.desafioitau.backend.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsável por buscar estatísticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas de transações"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<StatisticsResponseDTO> statistics(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer interval){
        StatisticsResponseDTO response=  statisticsService.statistics(interval);
        return ResponseEntity.ok(response);
    }
}
