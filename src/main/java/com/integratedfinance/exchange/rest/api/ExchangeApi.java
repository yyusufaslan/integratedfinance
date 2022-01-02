package com.integratedfinance.exchange.rest.api;


import com.integratedfinance.exchange.entity.Exchange;
import com.integratedfinance.exchange.model.DataResponse;
import com.integratedfinance.exchange.model.Response;
import com.integratedfinance.exchange.model.request.ExchangeListRequest;
import com.integratedfinance.exchange.model.request.ExchangeRateRequest;
import com.integratedfinance.exchange.model.request.ExchangeRequest;
import com.integratedfinance.exchange.model.response.ExchangeListResponse;
import com.integratedfinance.exchange.model.response.ExchangeRateResponse;
import com.integratedfinance.exchange.model.response.ExchangeResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-28T16:44:54.163+03:00")
@Api(value = "exchange", description = "The Document Management Exchange API")
public interface ExchangeApi {
    @ApiOperation(value = "Creates a Exchange", nickname = "createExchange", notes = "This operation creates a Exchange entity.", response = ExchangeRateResponse.class, tags = {"exchange",})
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created", response = ExchangeRateResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/createExchange", produces = {"application/json;charset=utf-8"}, consumes = {"application/json;charset=utf-8"}, method = RequestMethod.POST)
    ResponseEntity<Response<ExchangeResponse>> createExchange(@ApiParam(value = "The Exchange to be created", required = true) @RequestBody ExchangeRequest Exchange);

    @ApiOperation(value = "List or find exchange objects", nickname = "listExchange", notes = "This operation list or find exchange entities", response = Exchange.class, responseContainer = "List", tags = {"exchange",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ExchangeRateResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/listExchange", produces = {"application/json;charset=utf-8"}, method = RequestMethod.POST)
    ResponseEntity<Response<DataResponse<ExchangeListResponse>>> listExchange(@ApiParam(value = "The Exchange list", required = true) @RequestBody ExchangeListRequest exchangeListRequest);

    @ApiOperation(value = "List or find exchange objects", nickname = "listExchange", notes = "This operation list or find exchange entities", response = Exchange.class, responseContainer = "List", tags = {"exchange",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ExchangeRateResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/listExchangeRate", produces = {"application/json;charset=utf-8"}, method = RequestMethod.POST)
    ResponseEntity<Response<ExchangeRateResponse>> listExchangeRate(@ApiParam(value = "The Exchange list", required = true) @RequestBody ExchangeRateRequest exchangeRateRequest);


}
