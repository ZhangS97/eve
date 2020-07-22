package com.demo.component;

import lombok.Data;

@Data
public class CacheItem
{
    private static final long serialVersionUID = -7876629741785837545L;

    protected String cacheKey;

    protected Object cacheValue;

    protected String cacheName;

    protected int cacheLifetime = -1;

    protected boolean cacheExpired = false;

    public CacheItem(String cacheKey, String cacheValue)
    {
        setCacheKey(cacheKey);
        setCacheValue(cacheValue);
    }

    public CacheItem(String cacheKey, String cacheValue, int cacheLifetime)
    {
        setCacheKey(cacheKey);
        setCacheValue(cacheValue);
        setCacheLifetime(cacheLifetime);
    }
}
