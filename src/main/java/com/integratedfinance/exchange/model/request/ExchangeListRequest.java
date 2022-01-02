package com.integratedfinance.exchange.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeListRequest {
    @NotEmpty(message = "Transaction Id can not be empty.")
    private long transactionId;
    @NotEmpty(message = "Conversion Date Id can not be empty.")
    private long conversionDate;
}
