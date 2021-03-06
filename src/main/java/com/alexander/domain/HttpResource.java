package com.alexander.domain;
/**
 * Класс, представляющий собой сущность HTTP интернет-ресурса. Агрегирует в себе объект класса QueryAttempt, содержащий информацию о последнем запросе к данному ресурсу
 */
public class HttpResource {
    /**
     * Адрес ресурса
     */
    private String url;

    /**
     * Информация о последней попытке запроса ресурса
     */
    private QueryAttempt lastQueryStatus;

    public HttpResource(String url) {
        setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        // Добавляем протокол в начало URL строки, если его нет
        if(!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://")) {
            this.url = "http://" + url;
        }
    }

    public QueryAttempt getLastQueryStatus() {
        return lastQueryStatus;
    }

    public void setLastQueryStatus(QueryAttempt lastQueryStatus) {
        this.lastQueryStatus = lastQueryStatus;
    }

    @Override
    public String toString() {
        return "HttpResource{" +
                "url='" + url + '\'' +
                ", lastQueryStatus=" + lastQueryStatus +
                '}';
    }
}
