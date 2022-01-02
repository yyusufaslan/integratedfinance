package com.integratedfinance.exchange.config;

import com.integratedfinance.exchange.exception.BusinessException;
import com.integratedfinance.exchange.exception.NoDataFoundException;
import com.integratedfinance.exchange.model.ErrorResponse;
import com.integratedfinance.exchange.model.Response;
import com.integratedfinance.exchange.rest.BaseController;
import com.integratedfinance.exchange.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends BaseController {

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<ErrorResponse> handleException(Exception exception, Locale locale) {
        log.error("An error occurred! Details: ", exception);
        return createErrorResponseFromMessageSource(CommonUtils.getStringFromExceptionMessage(exception,512), locale);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<ErrorResponse> handleRequestPropertyBindingError(WebExchangeBindException webExchangeBindException, Locale locale) {
        log.debug("Bad request!", webExchangeBindException);
        return createFieldErrorResponse(webExchangeBindException.getBindingResult(), locale);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Response<ErrorResponse> handleBusinessException(BusinessException ex, Locale locale) {
        log.warn("Business exception occurred", ex);
        return createErrorResponseFromMessageSource(ex.getKey(), locale, ex.getArgs());
    }

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<ErrorResponse> handleNoDataFoundException(NoDataFoundException ex, Locale locale) {
        log.warn("Business exception occurred", ex);
        return createErrorResponseFromMessageSource(ex.getKey(), locale, ex.getArgs());
    }

    private Response<ErrorResponse> createFieldErrorResponse(BindingResult bindingResult, Locale locale) {
        List<String> requiredFieldErrorMessages = retrieveLocalizationMessage("general.client.requiredField", locale);
        String code = requiredFieldErrorMessages.get(0);

        String errorMessage = bindingResult
                .getFieldErrors().stream()
                .map(FieldError::getField)
                .map(error -> retrieveLocalizationMessage("general.client.requiredField", locale, error))
                .map(errorMessageList -> errorMessageList.get(1))
                .collect(Collectors.joining(" && "));

        log.debug("Exception occurred while request validation: {}", errorMessage);

        return respond(new ErrorResponse(code, errorMessage));
    }

    private Response<ErrorResponse> createErrorResponseFromMessageSource(String key, Locale locale, String... args) {
        List<String> messageList = retrieveLocalizationMessage(key, locale, args);
        return respond(new ErrorResponse(messageList.get(0), messageList.get(1)));
    }

    private List<String> retrieveLocalizationMessage(String key, Locale locale, String... args) {
        String message = messageSource.getMessage(key, args, locale);
        return Pattern.compile(";").splitAsStream(message).collect(Collectors.toList());
    }
}
