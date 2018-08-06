package com.alexander.service;

import com.alexander.domain.Resource;

import java.util.List;

/**
 * Абстракция, представляющая собой класс-сервис для запроса информации о состоянии веб-ресурсов
 */

public interface URLQueryService {
    List<Resource> getResourceStatuses();
}
