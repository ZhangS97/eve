package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.cache.Cacheable;
import com.demo.utils.ListUtils;
import com.demo.utils.BeanUtils;
import com.demo.web.bundle.universe.entity.Region;
import com.demo.web.bundle.universe.model.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionCache extends Cacheable
{
    //根据regionId存储对应region
    public static final String KEY_ID = "region:id:";

    //根据regionId存储对应的多个constellationId
    public static final String KEY_CONSTELLATION_ID = "region:constellation:id";

    @Autowired
    RegionService regionService;

    public void doLoad()
    {
        List<List<Region>> allRegions = ListUtils.groupList(regionService.findAll());
        RegionCache regionCacheProxy = BeanUtils.getBean(RegionCache.class);

        for (List<Region> regions : allRegions)
        {
            regionCacheProxy.doLoadTask(regions);
        }
    }

    @Async
    void doLoadTask(List<Region> regions)
    {
        for (Region region : regions)
        {
            //存储本身
            saveOne(KEY_ID + region.getRegionId(),
                    JSON.toJSONString(region));
            //存储constellationIds
            saveOne(KEY_CONSTELLATION_ID + region.getRegionId(),
                    region.getConstellations());
        }
    }
}
