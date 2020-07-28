package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.Cacheable;
import com.demo.web.bundle.universe.entity.System;
import com.demo.web.bundle.universe.model.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        List<System> systems = systemService.findAll();
        for (System system : systems)
        {
            saveOne(KEY_ID + system.getSystemId(), JSON.toJSONString(system));

            saveOne(KEY_STATION_ID + system.getSystemId(),
                    system.getStations());
        }

    }
}