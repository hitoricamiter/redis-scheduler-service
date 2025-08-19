package ru.zaikin.Dictionary.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DictionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictionaryApplication.class, args);
	}

}


/*
*
* “В продакшене обычный @Scheduled не безопасен на нескольких инстансах.
* Я обычно решаю это через распределённую блокировку, например с ShedLock, Redisson или PostgreSQL Advisory Lock.
* Ещё один вариант — вынести выполнение в отдельный сервис или CronJob, чтобы была централизованная точка запуска.”
* */