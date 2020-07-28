package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.Cacheable;
import com.demo.web.bundle.universe.entity.Constellation;
import com.demo.web.bundle.universe.model.service.ConstellationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConstellationCache extends Cacheable
{
    //根据ConstellationId存储对应Constellation
    public static final String KEY_ID = "constellation:id:";

    //根据constellationId存储对应的多个systemId
    public static final String KEY_SYSTEM_ID = "constellation:system:id:";

    @Autowired
    ConstellationService constellationService;

    public void doLoad()
    {
        List<Constellation> constellations = constellationService.findAll();

        for (Constellation constellation : constellations)
        {
            //存储本身
            saveOne(KEY_ID + constellation.getConstellationId(),
                    JSON.toJSONString(constellation));
            //存储下属的systemId
            saveOne(KEY_SYSTEM_ID + constellation.getConstellationId(),
                    constellation.getSystems());
        }
    }

}
