package com.alexander;

import com.alexander.domain.dto.QuerySettings;

import java.util.ArrayList;

/**
 * Класс для хранения константных значений
 */
public class ConstantValues {
    public static final int HTTP_CONNECTION_TIMEOUT = 10000; // Время таймаута HTTP соединения (в миллисекундах)
    /**
     * Настройки мониторинга по умолчанию
     */
    public static final QuerySettings DEFAULT_QUERY_SETTINGS = new QuerySettings();
    static {
        DEFAULT_QUERY_SETTINGS.setQueryInterval(10); // интервал запроса по умолчанию
        DEFAULT_QUERY_SETTINGS.setUrls(new ArrayList<>()); // изначально пустой список URL
    }

    /**
     * Тексты сообщений
     */
    public class Messages {
        public static final String HTTP_RESOURCE_AVAILABLE_MESSAGE = "Ресурс доступен по протоколу HTTP";
        public static final String URL_MALFORMED_MESSAGE = "Недействительный URL";
        public static final String INVALID_QUERY_INTERVAL_MESSAGE = "Значение интервала должно быть больше нуля";
        public static final String RESOURCE_QUERY_PENDING = "Запрос на данный ресурс отправлен. Ожидается ответ.";
        public static final String RESOURCE_QUERY_TIMEOUT = "Время ожидания ответа от ресурса истекло";
    }
}
