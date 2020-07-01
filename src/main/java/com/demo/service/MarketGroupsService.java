package com.demo.service;

import com.demo.entity.MarketGroups;

import java.util.List;

/**
 * (MarketGroups)表服务接口
 *
 * @author makejava
 * @since 2020-05-28 12:00:09
 */
public interface MarketGroupsService {

    void save(MarketGroups marketGroups);

    List<MarketGroups> findAll();

    String getTypeIdsByGIdAndRId(int groupId);
}
