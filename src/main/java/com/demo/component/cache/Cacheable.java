package com.demo.component.cache;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public abstract class Cacheable
{
    @Autowired
    protected CacheManager cacheManager;

    private static final long serialVersionUID = -7876629741785837545L;

    public void doLoad()
    {

    }

    //无限生命记录
    public void saveOne(String key, String value)
    {
        cacheManager.saveOne(new CacheItem(key, value));
    }

    //有限生命记录
    public void saveOne(String key, String value, int lifeTime)
    {
        cacheManager.saveOne(new CacheItem(key, value, lifeTime));
    }

    public String fetchOne(String key)
    {
        return (String) cacheManager.fetchOneByKey(key);
    }
}
