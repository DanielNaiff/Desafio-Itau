package com.desafioitau.backend.model;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.OffsetDateTime;

public record TransactionRequestDTO(
        @NotNull
        @PositiveOrZero
        Double valor,

        @NotNull
        @PastOrPresent
        OffsetDateTime dataHora

) {}
