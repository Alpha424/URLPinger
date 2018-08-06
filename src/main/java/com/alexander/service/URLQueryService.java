package com.alexander.service;

import com.alexander.domain.HttpResource;

import java.util.List;

/**
 * Абстракция, представляющая собой класс-сервис для запроса информации о состоянии веб-ресурсов
 */

public interface URLQueryService {
    List<HttpResource> getResourceStatuses();
}
