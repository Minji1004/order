package com.practice.store.config.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;
    private RedisServer redisServer;


    @PostConstruct
    public void redisServer() throws IOException {
        redisServer = RedisServer.builder()
                .port(redisPort)
                .setting("maxmemory 128M")
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer.isActive()) {
            redisServer.stop();
        }
    }
}
