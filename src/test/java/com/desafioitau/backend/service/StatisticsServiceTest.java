package com.desafioitau.backend.service;

import com.desafioitau.backend.model.StatisticsResponseDTO;
import com.desafioitau.backend.model.TransactionRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class StatisticsServiceTest {


    @InjectMocks
    StatisticsService estatisticasService;

    @Mock
    TransactionService transacaoService;

    TransactionRequestDTO transacao;

    StatisticsResponseDTO estatisticas;

    @BeforeEach
    void setUp(){
        transacao = new TransactionRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new StatisticsResponseDTO(1l, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calcularEstatisticasComSucesso(){
        when(transacaoService.searchTransactions(60))
                .thenReturn(Collections.singletonList(transacao));

        StatisticsResponseDTO resultado = estatisticasService.statistics(60);

        verify(transacaoService, times(1)).searchTransactions(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticas);
    }

    @Test
    void calcularEstatisticasQuandoListaVazia(){

        StatisticsResponseDTO estasticaEsperado =
                new StatisticsResponseDTO(0l, 0.0, 0.0, 0.0, 0.0);

        when(transacaoService.searchTransactions(60))
                .thenReturn(Collections.emptyList());

        StatisticsResponseDTO resultado = estatisticasService.statistics(60);

        verify(transacaoService, times(1)).searchTransactions(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estasticaEsperado);
    }

}
