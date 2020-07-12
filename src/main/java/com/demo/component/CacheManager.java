package com.demo.component;

import org.springframework.data.redis.core.RedisTemplate;

public class CacheManager
{
    RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate()
    {
        return redisTemplate;
    }

    public Object fetchOneByKey(String key)
    {
        Object object = null;
        redisTemplate.getValueSerializer();
        return object;
    }
}
