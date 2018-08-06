package com.alexander.service;

import com.alexander.domain.dto.QuerySettings;

/**
 * Расширение абстракции URLQueryService, предполагающее, что реализующий класс будет осуществлять опрос состояния заданных ресурсов раз в несколько секунд
 */

public interface ScheduledURLQueryService extends URLQueryService {
    void setQuerySettings(QuerySettings settings);
}
