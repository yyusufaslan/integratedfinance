package com.integratedfinance.exchange.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRequest {
    private double sourceAmount;
    private String sourceCurrency;
    private String targetCurrency;
}
