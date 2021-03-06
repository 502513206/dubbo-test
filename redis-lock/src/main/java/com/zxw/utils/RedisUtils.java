package com.zxw.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zxw
 * @date 2020/7/1 15:47
 */
public class RedisUtils {

    private static final String LOCKED_SUCCESS = "OK";
    private static final String NX = "NX";
    private static final String EXPIRE_TIME = "PX";

    public static boolean lock(RedisTemplate redisTemplate, String lockKey, String value, long expreTime) {
        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(lockKey, value, expreTime, TimeUnit.SECONDS);
        return ifAbsent;
    }

    public static boolean unlock(RedisTemplate redisTemplate, String lockKey) {
        Boolean expire = redisTemplate.opsForValue().getOperations().delete(lockKey);
        return expire;
    }

}
