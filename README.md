# Кжижный сервис с использованием Redis

## Описание проекта
Проект реализует Кжижный сервис. Вы можете просмотреть, создать, изменить или
удалить разных книги и категории книг.

Более подробную информацию по REST запросам вы можете найти по ссылке:
http://localhost:8080/swagger-ui/index.html

Get запросы по поиску книг по названию и автору или по 
категориям кешируются в Redis. В файле application.yaml 
в разделе app.cache.caches можно задавать время исчезновения кеша.

## Используемые технологии

- Spring Boot 3
- Gradle 8.7
- Lombok
- JDK 17

## Запуск проекта
Проект можно запустить двумя способами:
1. Запустить SpringRedisApplication.java по расположению src/main/java/com/example/SpringRedis/SpringRedisApplication.java

2. Запустить docker-compose.yaml. Для этого перейдите в папку docker и запустите
   команду:
```shell
docker-compose up
```