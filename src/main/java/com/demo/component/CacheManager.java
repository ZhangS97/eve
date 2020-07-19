package com.demo.component;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

@Data
public class CacheManager
{
    private static RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate()
    {
        return redisTemplate;
    }

    public Object fetchOneByKey(String key)
    {
        return redisTemplate.opsForValue().get(key);
    }

    public void saveOne(CacheItem cacheItem)
    {
        if (-1 == cacheItem.cacheLifetime)
        {
            redisTemplate.opsForValue()
                    .set(cacheItem.cacheKey, cacheItem.cacheValue);
        }
        else
        {
            redisTemplate.opsForValue()
                    .set(cacheItem.cacheKey,
                            cacheItem.cacheValue,
                            cacheItem.cacheLifetime);
        }

    }
}
