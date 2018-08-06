package com.alexander.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс-исключение, представляющий собой ошибку, возникающую при попытке передать неверные параметры в качестве настроек мониторинга
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQuerySettingsException extends RuntimeException {
    public InvalidQuerySettingsException(String message) {
        super(message);
    }
}
