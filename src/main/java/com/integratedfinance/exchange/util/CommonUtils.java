package com.integratedfinance.exchange.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;

@Slf4j
public class CommonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getStringFromExceptionMessage(Throwable throwable, int stringSize) {
        return StringUtils.substring(ExceptionUtils.getMessage(throwable), 0, stringSize);
    }

    public static void copyFromTo(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            log.error("Object copy operation has failed!", e);
        }
    }

}
