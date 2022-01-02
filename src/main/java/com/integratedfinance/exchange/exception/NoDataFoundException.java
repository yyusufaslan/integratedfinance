package com.integratedfinance.exchange.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoDataFoundException extends RuntimeException {
    private String key;
    private final String[] args;

    public NoDataFoundException(String key) {
        super(key);
        this.key = key;
        args = new String[0];
    }

    public NoDataFoundException(String key, String... args) {
        super(key);
        this.key = key;
        this.args = args;
    }
}
