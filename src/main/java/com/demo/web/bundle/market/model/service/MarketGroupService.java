package com.demo.web.bundle.market.model.service;

import com.demo.web.bundle.market.entity.MarketGroup;

import java.util.List;

/**
 * (MarketGroup)表服务接口
 *
 * @author makejava
 * @since 2020-05-28 12:00:09
 */
public interface MarketGroupService {

    void save(MarketGroup marketGroup);

    List<MarketGroup> findAll();

    String getTypeIdsByGIdAndRId(String groupId);

}
