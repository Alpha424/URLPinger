package com.alexander.controller;

import com.alexander.domain.HttpResource;
import com.alexander.domain.dto.QuerySettings;
import com.alexander.service.ScheduledURLQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Основной класс-контроллер приложения. Содержит конечные точки доступа к REST сервису
 */

@RestController
public class URLResolverController {
    private ScheduledURLQueryService urlQueryService;

    public URLResolverController(ScheduledURLQueryService urlQueryService) {
        this.urlQueryService = urlQueryService;
    }

    /**
     * Метод-endpoint (HTTP GET) для получения текущего статуса всех добавленных URL
     * @return Список объектов типа HttpResource, содержащих информацию о ресурсе и последней попытке обращения к нему
     */
    @GetMapping("/query")
    public ResponseEntity<List<HttpResource>> getCurrentStatus() {
        return new ResponseEntity<>(this.urlQueryService.getResourceStatuses(), HttpStatus.OK);
    }

    /**
     * Метод-endpoint (HTTP POST) для задания текущих настроек мониторинга. Настройки передаются в формате JSON в теле POST запроса
     *
     * Пример:
     *
     * {
     *   "queryInterval": 10,
     *   "urls": [
     *     "http://google.com",
     *     "http://apple.com",
     *     "yandex.ru"
     *   ]
     * }
     *
     * @return В случае успеха возвращает JSON, ранее переданный в метод
     */
    @PostMapping("/query")
    public ResponseEntity<QuerySettings> setQuerySettings(@RequestBody QuerySettings settings) {
        urlQueryService.setQuerySettings(settings);
        return ResponseEntity.ok(settings);
    }

}
