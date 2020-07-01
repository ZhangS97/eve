package com.demo.service;

import com.demo.entity.Regions;

import java.util.List;

/**
 * (Regions)表服务接口
 *
 * @author makejava
 * @since 2020-06-03 14:17:53
 */
public interface RegionsService {
    void save(Regions regions);

    List<Integer> showHighSecureRegionsID();
}