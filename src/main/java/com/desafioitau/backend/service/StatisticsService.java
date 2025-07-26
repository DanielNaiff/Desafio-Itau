package com.desafioitau.backend.service;

import com.desafioitau.backend.model.StatisticsResponseDTO;
import com.desafioitau.backend.model.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final TransactionService transactionService;

    public StatisticsResponseDTO statistics(Integer interval){
        long start = System.currentTimeMillis();

        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(interval);

        if (transactions.isEmpty()) {
            return new StatisticsResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics doubleSummaryStatistics = transactions.stream().mapToDouble(TransactionRequestDTO::valor).summaryStatistics();

        return new StatisticsResponseDTO(
                doubleSummaryStatistics.getCount(),
                doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getAverage(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getMax()
                );
    }
}
