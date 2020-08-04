package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.Cacheable;
import com.demo.utils.ListUtils;
import com.demo.utils.SpringUtils;
import com.demo.web.bundle.universe.entity.Station;
import com.demo.web.bundle.universe.model.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationCache extends Cacheable
{
    //根据stationId存储对应station
    public static final String KEY_ID = "station:id:";

    @Autowired
    StationService stationService;

    public void doLoad()
    {
        List<List<Station>> allStations = ListUtils.groupList(stationService.findAll());
        StationCache stationCacheProxy = SpringUtils.getBean(StationCache.class);

        for (List<Station> station : allStations)
        {
            stationCacheProxy.doLoadTask(station);
        }
    }

    @Async
    void doLoadTask(List<Station> stations)
    {
        for (Station station : stations)
        {
            //保存本身
            saveOne(KEY_ID + station.getStationId(),
                    JSON.toJSONString(station));

        }
    }
}
