package com.alexander.service.impl;

import com.alexander.domain.QueryAttempt;
import com.alexander.domain.Resource;
import com.alexander.service.QueryMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.util.Date;

import static com.alexander.ConstantValues.*;
import static com.alexander.ConstantValues.Messages.*;

/**
 * Реализация интерфейса QueryMethod
 */

@Service
public class QueryMethodImpl implements QueryMethod {

    /**
     * Реализация метода, производящего запрос по переданному ресурсу. Метод сохраняет результат запроса в виде объекта класса QueryAttempt и помещает его внутрь переданного ресурса
     * @param resource Ресурс, для которого необходимо сделать запрос
     */

    @Override
    public void queryResource(Resource resource) {
        QueryAttempt queryAttempt = new QueryAttempt();
        queryAttempt.setTime(new Date());
        queryAttempt.setResult(RESOURCE_QUERY_PENDING);
        try {
            URL url = new URL(resource.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
            connection.connect();
            queryAttempt.setResponseCode(connection.getResponseCode());
            queryAttempt.setResult(HTTP_RESOURCE_AVAILABLE_MESSAGE); // Соединение было успешно установлено
            connection.disconnect();
        } catch (MalformedURLException e) {
            queryAttempt.setResult(URL_MALFORMED_MESSAGE);
        } catch (SocketTimeoutException e) {
            queryAttempt.setResult(RESOURCE_QUERY_TIMEOUT);
        } catch (IOException e) {
            queryAttempt.setResult(String.format("Не удалось установить соединение по протоколу HTTP. Ошибка: %s", e.getMessage()));
        }
        resource.setLastQueryStatus(queryAttempt);
    }
}
