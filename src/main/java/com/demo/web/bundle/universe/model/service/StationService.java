package com.demo.web.bundle.universe.model.service;

import com.demo.web.bundle.universe.entity.Station;

import java.util.List;

public interface StationService
{
    void save(Station station);

    List<Station> findAll();

}
