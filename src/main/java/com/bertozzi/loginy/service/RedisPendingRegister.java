package com.bertozzi.loginy.service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisPendingRegister {

    private static final String PREFIX = "pending:registration:";
    private static final Duration RETENTION = Duration.ofMinutes(15);

    private final RedisTemplate<String, Object> redisTemplate;

    public PendingRegistration save(PendingRegistration pending) {
        redisTemplate.opsForValue().set(key(pending.email()), pending, RETENTION);
        return pending;
    }

    public Optional<PendingRegistration> findByEmail(String email) {
        return Optional.ofNullable((PendingRegistration) redisTemplate.opsForValue().get(key(email)));
    }

    public void deleteByEmail(String email) {
        redisTemplate.opsForValue().getAndDelete(key(email));
    }

    private String key(String email) {
        return PREFIX + email;
    }
}
