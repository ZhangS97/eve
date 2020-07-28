package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.Cacheable;
import com.demo.web.bundle.universe.entity.Station;
import com.demo.web.bundle.universe.model.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StationCache extends Cacheable
{
    //根据stationId存储对应station
    public static final String KEY_ID = "station:id:";

    @Autowired
    StationService stationService;

    public void doLoad()
    {
        List<Station> stations = stationService.findAll();
        for (Station station : stations)
        {
            saveOne(KEY_ID + station.getStationId(),
                    JSON.toJSONString(station));

        }

    }
}
