package com.example.cdnaskydrivejava.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void add(String key, String value) {
        redisTemplate.opsForValue().set(key, value,30, TimeUnit.SECONDS);
    }

    public void removeSet(String key) {
        redisTemplate.delete(key);
    }

    public String get(String key) {
        return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
    }
}