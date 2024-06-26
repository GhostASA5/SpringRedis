package com.example.SpringRedis.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "app.cache")
public class AppCacheProperties {

    private final List<String> cacheNames = new ArrayList<>();

    private final Map<String, CacheProperties> caches = new HashMap<>();

    private CacheType cacheType;

    @Data
    public static class CacheProperties {
        private Duration expiry = Duration.ZERO;
    }

    public interface CacheNames {
        String BOOK_BY_TITLE_AND_AUTHOR = "bookByTitleAndAuthor";
        String BOOKS_BY_CATEGORY = "booksByCategory";
    }

    public enum CacheType {
        REDIS
    }
}
