package ru.zaikin.Dictionary.Application.scheduler;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsScheduler {

    @PersistenceContext
    private EntityManager em;

    // Раз в сутки (например, в полночь)
    @Scheduled(cron = "0 0 0 * * *")
    @SchedulerLock(name = "refreshDailyRequests", lockAtMostFor = "10m", lockAtLeastFor = "5m")
    public void refreshMaterializedView() {
        em.createNativeQuery("REFRESH MATERIALIZED VIEW daily_requests").executeUpdate();
    }
}

/*ADVISORY LOCK HOW TO DO [КЛЮЧЕВАЯ МЫСЛЬ - УНИКАЛЬНЫЙ ИДЕНТИФИКАТОР ЗАДАЧИ 12345]
* package ru.zaikin.Dictionary.Application.scheduler;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnalyticsScheduler {

    @PersistenceContext
    private EntityManager em;

    // Раз в сутки (например, в полночь)
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void refreshMaterializedView() {
        // Попытка получить advisory lock с уникальным числом
        Boolean gotLock = (Boolean) em.createNativeQuery(
                "SELECT pg_try_advisory_lock(12345)")
                .getSingleResult();

        if (Boolean.TRUE.equals(gotLock)) {
            try {
                // Выполняем обновление материализованной вьюхи
                em.createNativeQuery("REFRESH MATERIALIZED VIEW daily_requests").executeUpdate();
            } finally {
                // Снимаем lock
                em.createNativeQuery("SELECT pg_advisory_unlock(12345)").executeUpdate();
            }
        }
        // Если lock не получен, значит другой инстанс уже выполняет задачу → пропускаем
    }
}

*
*
* */
