package com.desafioitau.backend.service;

import com.desafioitau.backend.model.StatisticsResponseDTO;
import com.desafioitau.backend.model.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    private final TransactionService transactionService;

    public StatisticsResponseDTO statistics(Integer interval){
        long start = System.currentTimeMillis();
        log.info("Iniciando cálculo de estatísticas para intervalo: {} segundos", interval);

        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(interval);

        if (transactions.isEmpty()) {
            log.warn("Nenhuma transação encontrada no intervalo de {} segundos", interval);
            return new StatisticsResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics statistics = transactions.stream().mapToDouble(TransactionRequestDTO::valor).summaryStatistics();
        log.info("Estatísticas calculadas com sucesso: count={}, sum={}, avg={}, min={}, max={}",
                statistics.getCount(), statistics.getSum(), statistics.getAverage(), statistics.getMin(), statistics.getMax());

        long duration = System.currentTimeMillis() - start;
        log.debug("Tempo de execução do método statistics: {} ms", duration);
        return new StatisticsResponseDTO(
                statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                statistics.getMin(),
                statistics.getMax()
                );
    }
}
