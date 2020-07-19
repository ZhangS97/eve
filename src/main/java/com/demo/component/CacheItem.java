package com.demo.component;

import lombok.Data;

@Data
public class CacheItem
{
    private static final long serialVersionUID = -7876629741785837545L;

    protected String cacheKey;

    protected Object cacheValue;

    protected String cacheName;

    protected static int cacheLifetime = -1;

    protected boolean cacheExpired = false;
}
