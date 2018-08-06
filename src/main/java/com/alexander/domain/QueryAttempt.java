package com.alexander.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * Класс, представляющий собой сущность-попытку запроса на доступность URL
 */
public class QueryAttempt {
    /**
     * Время запроса
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss", timezone = "GMT+3")
    private Date time;

    /**
     * Результат
     */

    private String result;
    /**
     * Код статуса HTTP
     */

    private int responseCode;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "QueryAttempt{" +
                "time=" + time +
                ", result='" + result + '\'' +
                ", responseCode=" + responseCode +
                '}';
    }

}
