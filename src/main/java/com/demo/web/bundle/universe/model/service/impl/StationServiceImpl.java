package com.demo.web.bundle.universe.model.service.impl;

import com.demo.web.bundle.universe.entity.Station;
import com.demo.web.bundle.universe.model.dao.StationsDao;
import com.demo.web.bundle.universe.model.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StationServiceImpl implements StationService
{
    @Autowired
    StationsDao dao;

    @Override
    public void save(Station station)
    {
        dao.save(station);
    }

    @Override
    public List<Station> findAll()
    {
        return dao.findAll();
    }

}
