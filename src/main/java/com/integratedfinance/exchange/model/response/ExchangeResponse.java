package com.integratedfinance.exchange.model.response;

import com.integratedfinance.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponse {
    private Long transactionId;
    private Double amount;

    public static ExchangeResponse fromModel(Exchange exchange, Double amount) {
        return ExchangeResponse.builder()
                .transactionId(exchange.getTransactionId())
                .amount(amount)
                .build();
    }
}
