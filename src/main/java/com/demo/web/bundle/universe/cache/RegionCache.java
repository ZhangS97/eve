package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.Cacheable;
import com.demo.web.bundle.universe.entity.Region;
import com.demo.web.bundle.universe.model.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegionCache extends Cacheable
{
    //根据regionId存储对应region
    public static final String KEY_ID = "region:id:";

    //根据regionId存储对应的多个constellationId
    public static final String KEY_CONSTELLATIONS_ID = "region:constellations:id";

    @Autowired
    RegionService regionService;

    public void doLoad()
    {
        List<Region> allRegions = regionService.findAll();

        for (Region region : allRegions)
        {
            //存储本身
            saveOne(KEY_ID, JSON.toJSONString(region));
            //存储constellationIds
            saveOne(KEY_CONSTELLATIONS_ID, region.getConstellations());
        }
    }
}
