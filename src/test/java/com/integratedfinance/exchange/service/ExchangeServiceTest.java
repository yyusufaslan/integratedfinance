package com.integratedfinance.exchange.service;

import com.integratedfinance.exchange.IntegratedfinanceApplication;
import com.integratedfinance.exchange.model.request.ExchangeListRequest;
import com.integratedfinance.exchange.model.request.ExchangeRateRequest;
import com.integratedfinance.exchange.model.request.ExchangeRequest;
import com.integratedfinance.exchange.model.response.ExchangeListResponse;
import com.integratedfinance.exchange.model.response.ExchangeRateResponse;
import com.integratedfinance.exchange.model.response.ExchangeResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangeServiceTest {

    @Autowired
    ExchangeService exchangeService;

    @Test
    public void testWriteToDb_Exchange() {
        ExchangeRequest exchangeRequest = new ExchangeRequest();
        exchangeRequest.setSourceAmount(20);
        exchangeRequest.setSourceCurrency("USD");
        exchangeRequest.setTargetCurrency("TRY");
        ExchangeResponse exchange = exchangeService.createExchange(exchangeRequest);
        Assert.assertTrue(exchange.getTransactionId() > 0);
    }

    @Test
    public void testListExchange() {
        ExchangeListRequest exchangeListRequest = new ExchangeListRequest();
        exchangeListRequest.setConversionDate(5080898142669902040L);
        exchangeListRequest.setTransactionId(5080898142669902040L);
        List<ExchangeListResponse> exchange = exchangeService.listExchanges(exchangeListRequest);
        Assert.assertTrue(exchange.isEmpty());
    }

    @Test
    public void testListExchangeRate() {
        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest();
        exchangeRateRequest.setTargetCurrency("TRY");
        exchangeRateRequest.setSourceCurrency("USD");
        ExchangeRateResponse exchange = exchangeService.listExchangeRate(exchangeRateRequest);
        Assert.assertTrue((double)exchange.getValue() > 0);
    }
}
