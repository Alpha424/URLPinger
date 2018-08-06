package com.alexander.domain.dto;

import java.util.List;

/**
 * DTO для хранения и передачи параметров мониторинга
 */

public class QuerySettings {
    /**
     * Интервал для запроса (в секундах)
     */
    private int queryInterval;

    /**
     * Список URL
     */
    private List<String> urls;

    public int getQueryInterval() {
        return queryInterval;
    }

    public void setQueryInterval(int queryInterval) {
        this.queryInterval = queryInterval;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
