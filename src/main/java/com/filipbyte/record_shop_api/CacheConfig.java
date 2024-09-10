package com.filipbyte.record_shop_api;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .evictionListener((key, value, cause) -> {
                    LOGGER.info("Cache eviction occurred:");
                    LOGGER.info("Key: {}", key);
                    LOGGER.info("Value: {}", value);
                    LOGGER.info("Cause: {}", cause);
                    LOGGER.info("--------------------");
                });
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("albums");
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
