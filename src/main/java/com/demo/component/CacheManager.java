package com.demo.component;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> fetchListByKeys(String[] keys)
    {
        List<String> objList = new ArrayList<>();
        for (String key : keys)
        {
            objList.add(fetchOneByKey(key).toString());
        }
        return objList;
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

    public void saveList(List<CacheItem> cacheItemList)
    {
        for (CacheItem cacheItem : cacheItemList)
        {
            saveOne(cacheItem);
        }
    }

}
