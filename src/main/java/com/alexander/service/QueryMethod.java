package com.alexander.service;

import com.alexander.domain.HttpResource;

/**
 * Абстракция, содержащая описание метода для запроса информации о состоянии ресурса
 */

public interface QueryMethod {
    void queryResource(HttpResource httpResource);
}
