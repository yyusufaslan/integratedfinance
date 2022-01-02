package com.integratedfinance.exchange.service;

import com.integratedfinance.exchange.entity.Exchange;
import com.integratedfinance.exchange.exception.BusinessException;
import com.integratedfinance.exchange.model.request.ExchangeListRequest;
import com.integratedfinance.exchange.model.request.ExchangeRateRequest;
import com.integratedfinance.exchange.model.request.ExchangeRequest;
import com.integratedfinance.exchange.model.response.ExchangeListResponse;
import com.integratedfinance.exchange.model.response.ExchangeRateResponse;
import com.integratedfinance.exchange.model.response.ExchangeResponse;
import com.integratedfinance.exchange.repository.ExchangeRepository;
import com.integratedfinance.exchange.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    public ExchangeResponse createExchange(ExchangeRequest exchangeRequest) {
        log.info("ExchangeCreateService -> createExchange started! {}", exchangeRequest.getClass().getName());
        JSONObject jsonObject = CurrencyGetService.sendLiveRateRequest(exchangeRequest.getSourceCurrency());
        if (jsonObject.has("error")) {
            throw new BusinessException(jsonObject.getJSONObject("error").toMap().get("info").toString());
        }
        double value = (double) getTargetCurrency(jsonObject, exchangeRequest.getTargetCurrency());

        Exchange exchange = copyProperties(exchangeRequest);
        Exchange savedEntity = exchangeRepository.save(exchange);
        return ExchangeResponse.fromModel(savedEntity,exchangeRequest.getSourceAmount() / value);
    }

    public List<ExchangeListResponse> listExchanges(ExchangeListRequest exchangeListRequest) {
        log.info("ExchangeService -> listExchanges started!");
        if (exchangeListRequest.getConversionDate() > 0) {
            return exchangeRepository.findAllByConversionDate(exchangeListRequest.getConversionDate()).stream().map(ExchangeListResponse::fromModel).collect(Collectors.toList());
        } else {
            return Collections.singletonList(exchangeRepository.findByTransactionId(exchangeListRequest.getTransactionId())).stream().map(ExchangeListResponse::fromModel).collect(Collectors.toList());
        }
    }

    public ExchangeRateResponse listExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        log.info("ExchangeService -> listExchangeRate started!");
        ExchangeRateResponse exchangeRateResponse = null;
        //List<Exchange> exchanges = (List<Exchange>) exchangeRepository.findAll();
        JSONObject currenciesRequest = CurrencyGetService.getCurrenciesRequest();
        if (currenciesRequest.has("error")) {
            throw new BusinessException(currenciesRequest.getJSONObject("error").toMap().get("info").toString());
        }
        Map<String, Object> currencyList = (Map<String, Object>) currenciesRequest.getJSONObject("currencies").toMap();
        if (currencyList.containsKey(exchangeRateRequest.getTargetCurrency()) && currencyList.containsKey(exchangeRateRequest.getSourceCurrency())) {
            JSONObject jsonObject = CurrencyGetService.sendLiveRateRequest(exchangeRateRequest.getSourceCurrency());
            if (jsonObject.has("error")) {
                throw new BusinessException(jsonObject.getJSONObject("error").toMap().get("info").toString());
            }
            Object value = getTargetCurrency(jsonObject, exchangeRateRequest.getTargetCurrency());
            exchangeRateResponse = ExchangeRateResponse.fromModel(jsonObject,value);
        } else {
            throw new BusinessException("Currency is not valid.");
        }
        return exchangeRateResponse;
    }

    private Object getTargetCurrency(JSONObject jsonObject, String targetCurrency) {
        Map<String, Object> quotes = jsonObject.getJSONObject("quotes").toMap();
        for (Map.Entry<String, Object> m: quotes.entrySet()) {
            if (m.getKey().contains(targetCurrency)) {
                return m.getValue();
            }
        }
        return null;
    }

    private Exchange copyProperties(ExchangeRequest exchangeRequest) {
        Exchange exchange = new Exchange();
        try {
            CommonUtils.copyFromTo(exchangeRequest, exchange);
            exchange.setUserId(Math.abs(SecureRandom.getInstanceStrong().nextLong()));
            exchange.setTransactionId(Math.abs(SecureRandom.getInstanceStrong().nextLong()));
            exchange.setConversionDate(new Date().getTime());
            exchange.setSourceAmount(exchangeRequest.getSourceAmount());
            exchange.setCreatedDate(new Date());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return exchange;
    }

}
