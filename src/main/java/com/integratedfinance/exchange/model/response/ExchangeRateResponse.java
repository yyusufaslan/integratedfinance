package com.integratedfinance.exchange.model.response;

import com.integratedfinance.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponse {
    private Long queryDate;
    private String source;
    private Object value;
    public static ExchangeRateResponse fromModel(JSONObject jsonObject, Object value) {
        return ExchangeRateResponse.builder()
                .queryDate(new Date().getTime())
                .value(value)
                .source(jsonObject.get("source").toString())
                .build();
    }
}
