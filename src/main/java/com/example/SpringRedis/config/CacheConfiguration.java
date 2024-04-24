package com.example.SpringRedis.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        var defaultConfig = RedisCacheConfiguration.defaultCacheConfig();
        Map<String, RedisCacheConfiguration> configs = new HashMap<>();

        configs.put("bookByTitleAndAuthor", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(5)));

        return RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(configs)
                .build();
    }
}
