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

}
