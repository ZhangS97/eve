package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.cache.Cacheable;
import com.demo.utils.ListUtils;
import com.demo.utils.BeanUtils;
import com.demo.web.bundle.universe.entity.Constellation;
import com.demo.web.bundle.universe.model.service.ConstellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
        List<List<Constellation>> allConstellations = ListUtils.groupList(
                constellationService.findAll());

        ConstellationCache constellationCacheProxy = BeanUtils.getBean(
                ConstellationCache.class);
        for (List<Constellation> constellations : allConstellations)
        {
            constellationCacheProxy.doLoadTask(constellations);
        }
    }

    @Async
    void doLoadTask(List<Constellation> constellations)
    {
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
