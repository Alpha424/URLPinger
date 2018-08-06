package com.alexander.service;

import com.alexander.domain.Resource;

/**
 * Абстракция, содержащая описание метода для запроса информации о состоянии ресурса
 */

public interface QueryMethod {
    void queryResource(Resource resource);
}
