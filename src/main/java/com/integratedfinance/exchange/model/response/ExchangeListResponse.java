package com.integratedfinance.exchange.model.response;

import com.integratedfinance.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeListResponse {
    private double sourceAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private long conversionDate;
    private long transactionId;

    public static ExchangeListResponse fromModel(Exchange exchange) {
        return ExchangeListResponse.builder()
                .sourceAmount(exchange.getSourceAmount())
                .conversionDate(exchange.getConversionDate())
                .sourceCurrency(exchange.getSourceCurrency())
                .targetCurrency(exchange.getTargetCurrency())
                .transactionId(exchange.getTransactionId())
                .build();
    }
}
