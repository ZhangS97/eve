package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.cache.Cacheable;
import com.demo.utils.ListUtils;
import com.demo.utils.BeanUtils;
import com.demo.web.bundle.universe.entity.System;
import com.demo.web.bundle.universe.model.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemCache extends Cacheable
{
    //根据systemId存储对应system
    public static final String KEY_ID = "system:id:";

    //根据systemId存储对应的多个stationId
    public static final String KEY_STATION_ID = "system:station:id:";

    @Autowired
    SystemService systemService;

    public void doLoad()
    {
        List<List<System>> allSystems = ListUtils.groupList(systemService.findAll());
        SystemCache systemCacheProxy = BeanUtils.getBean(SystemCache.class);

        for (List<System> systems : allSystems)
        {
            systemCacheProxy.doLoadTask(systems);
        }
    }

    @Async
    void doLoadTask(List<System> systems)
    {
        for (System system : systems)
        {
            //存储本身
            saveOne(KEY_ID + system.getSystemId(), JSON.toJSONString(system));
            //存储下属空间站
            saveOne(KEY_STATION_ID + system.getSystemId(),
                    system.getStations());
        }
    }
}