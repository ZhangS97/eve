package com.demo.web.bundle.universe.model.service;

import com.demo.web.bundle.universe.entity.Region;

import java.util.List;

/**
 * (Region)表服务接口
 *
 * @author makejava
 * @since 2020-06-03 14:17:53
 */
public interface RegionService
{
    void save(Region region);

    List<String> showHighSecureRegionsID();

    List<Region> findAll();
}