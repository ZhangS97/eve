package com.demo.component;

import lombok.Data;

@Data
public abstract class Cacheable
{
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

}
