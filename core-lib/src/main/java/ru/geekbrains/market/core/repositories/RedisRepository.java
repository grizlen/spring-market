package ru.geekbrains.market.core.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisRepository {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void setKey(String key) {
        if (key.startsWith("Bearer ")) {
            key = key.replace("Bearer ", "");
        }
        redisTemplate.opsForValue().set(key, "abc", Duration.ofHours(1));
    }

    public boolean hasKey(String key) {
        if (key.startsWith("Bearer ")) {
            key = key.replace("Bearer ", "");
        }
        return redisTemplate.hasKey(key);
    }
}
