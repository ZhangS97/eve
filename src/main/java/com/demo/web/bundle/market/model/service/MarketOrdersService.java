package com.demo.web.bundle.market.model.service;

import com.demo.web.bundle.market.entity.MarketOrders;

import java.util.List;

/**
 * (MarketOrders)表服务接口
 *
 * @author makejava
 * @since 2020-06-04 20:24:18
 */
public interface MarketOrdersService {
    void save(MarketOrders marketOrders);

    List<MarketOrders> getOrders(int typeId, int regionId);
}