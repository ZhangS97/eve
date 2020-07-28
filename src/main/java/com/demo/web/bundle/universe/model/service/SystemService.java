package com.demo.web.bundle.universe.model.service;

import com.demo.web.bundle.universe.entity.System;

import java.util.List;

/**
 * (System)表服务接口
 *
 * @author makejava
 * @since 2020-06-03 16:10:08
 */
public interface SystemService
{
    void save(System system);

    List<System> findAll();

    List<String> findAllStationIds();
}