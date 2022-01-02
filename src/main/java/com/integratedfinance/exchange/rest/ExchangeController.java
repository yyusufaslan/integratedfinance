package com.integratedfinance.exchange.rest;

import com.integratedfinance.exchange.exception.BusinessException;
import com.integratedfinance.exchange.model.DataResponse;
import com.integratedfinance.exchange.model.Response;
import com.integratedfinance.exchange.model.request.ExchangeListRequest;
import com.integratedfinance.exchange.model.request.ExchangeRateRequest;
import com.integratedfinance.exchange.model.request.ExchangeRequest;
import com.integratedfinance.exchange.model.response.ExchangeListResponse;
import com.integratedfinance.exchange.model.response.ExchangeRateResponse;
import com.integratedfinance.exchange.model.response.ExchangeResponse;
import com.integratedfinance.exchange.rest.api.ExchangeApi;
import com.integratedfinance.exchange.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
public class ExchangeController extends BaseController implements ExchangeApi {
    @Autowired
    private ExchangeService exchangeService;

    @Override
    public ResponseEntity<Response<ExchangeResponse>> createExchange(ExchangeRequest exchange) {
        return new ResponseEntity<>(respond(exchangeService.createExchange(exchange)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response<DataResponse<ExchangeListResponse>>> listExchange(ExchangeListRequest exchangeListRequest) {
        if (exchangeListRequest.getTransactionId() <= 0 && exchangeListRequest.getConversionDate() <= 0) {
                throw new BusinessException("Conversation Date or Transaction Id can not be empty.");
        }
        if (exchangeListRequest.getConversionDate() > 0 && exchangeListRequest.getTransactionId() > 0) {
            throw new BusinessException("Conversation Date or Transaction Id can not be provided same time.");
        }
        return new ResponseEntity<>(respond(exchangeService.listExchanges(exchangeListRequest)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<ExchangeRateResponse>> listExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        if (Objects.isNull(exchangeRateRequest.getSourceCurrency())) {
            exchangeRateRequest.setSourceCurrency("USD");
        }
        return new ResponseEntity<>(respond(exchangeService.listExchangeRate(exchangeRateRequest)), HttpStatus.OK);
    }
}
