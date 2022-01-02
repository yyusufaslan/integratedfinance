package com.integratedfinance.exchange.rest;



import com.integratedfinance.exchange.model.DataResponse;
import com.integratedfinance.exchange.model.ErrorResponse;
import com.integratedfinance.exchange.model.Response;
import com.integratedfinance.exchange.model.ResponseBuilder;

import java.util.List;

public abstract class BaseController {

    public <T> Response<DataResponse<T>> respond(List<T> items) {
        return ResponseBuilder.build(items);
    }

    protected <T> Response<T> respond(T item) {
        return ResponseBuilder.build(item);
    }

    protected Response<ErrorResponse> respond(ErrorResponse errorResponse) {
        return ResponseBuilder.build(errorResponse);
    }
}
