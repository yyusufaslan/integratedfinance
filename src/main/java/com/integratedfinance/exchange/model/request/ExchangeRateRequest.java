package com.integratedfinance.exchange.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRequest {
    private String sourceCurrency;
    private String targetCurrency;
}
