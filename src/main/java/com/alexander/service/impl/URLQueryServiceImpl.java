package com.alexander.service.impl;

import com.alexander.ConstantValues;
import com.alexander.domain.HttpResource;
import com.alexander.domain.dto.QuerySettings;
import com.alexander.exception.InvalidQuerySettingsException;
import com.alexander.service.QueryMethod;
import com.alexander.service.ScheduledURLQueryService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static com.alexander.ConstantValues.Messages.*;

/**
 * Реализация интерфейса URLQueryService
 */

@Service
public class URLQueryServiceImpl implements ScheduledURLQueryService {
    /**
     * Объект класса, содержащего реализацию метода получения информации о состоянии ресурса
     */
    private QueryMethod queryMethod;
    /**
     * Список объектов типа HttpResource, представляющих собой интернет ресурсы с информацией об их последнем статусе
     */
    private List<HttpResource> httpResources;
    /**
     * Объект, содержащий текущие настройки мониторинга
     */
    private QuerySettings querySettings;
    /**
     * Таймер для периодического опроса всех ресурсов
     */
    private Timer updateTimer;
    /**
     * Флаг, указывающий на то, запущен ли таймер опроса ресурсов в данный момент
     */
    private boolean isUpdateTimerRunning = false;

    public URLQueryServiceImpl(QueryMethod queryMethod) {
        this.queryMethod = queryMethod;
        this.httpResources = new ArrayList<>();
        this.querySettings = ConstantValues.DEFAULT_QUERY_SETTINGS;
    }

    @PostConstruct
    private void scheduleUpdateTask() {
        // Проверяем, запущен ли таймер
        if(isUpdateTimerRunning) {
            updateTimer.cancel(); // Отменяем ранее запущенный таймер (если есть)
            updateTimer.purge(); // Очищаем очередь задач таймера
            isUpdateTimerRunning = false;
        }
        this.updateTimer = new Timer(true);
        this.updateTimer.schedule(
                new RefreshTimerTask(),
                0, // Нулевая задержка перед началом выполнения
                querySettings.getQueryInterval() * 1000 // перевод в миллисекунды
        );
        isUpdateTimerRunning = true;
    }

    @Override
    public void setQuerySettings(QuerySettings settings) {
        // Необходимо убедиться, что интервал больше нуля
        if(settings.getQueryInterval() <= 0) {
            throw new InvalidQuerySettingsException(INVALID_QUERY_INTERVAL_MESSAGE);
        }
        this.httpResources.clear();
        this.httpResources.addAll(settings.getUrls().stream().map(HttpResource::new).collect(Collectors.toList()));
        this.querySettings = settings;
        scheduleUpdateTask(); // Заводим таймер заново с новым интервалом
    }

    @Override
    public List<HttpResource> getResourceStatuses() {
        return httpResources;
    }

    /**
     * Класс, описывающий задачу периодического опроса состояния всех ресурсов (URL)
     */
    private class RefreshTimerTask extends TimerTask {

        @Override
        public void run() {
            for (HttpResource httpResource : httpResources) {
                // Запускаем задачу опроса ресурса в отдельном потоке для каждого ресурса чтобы долгий ответ от одного URL не блокировал опрос остальных
                Thread resourceQueryThread = new Thread(new ResourceQueryTask(httpResource));
                resourceQueryThread.setDaemon(true); //чтобы не мешать преждевременному закрытию приложения
                resourceQueryThread.start();
            }
        }
    }

    /**
     * Класс, описывающий задачу опроса состояния одного ресурса (URL)
     */
    private class ResourceQueryTask implements Runnable {
        /**
         * Ресурс, доступность которого необходимо выяснить
         */
        private final HttpResource httpResourceToQuery;

        private ResourceQueryTask(HttpResource httpResourceToQuery) {
            this.httpResourceToQuery = httpResourceToQuery;
        }

        @Override
        public void run() {
            queryMethod.queryResource(httpResourceToQuery);
        }
    }

}
