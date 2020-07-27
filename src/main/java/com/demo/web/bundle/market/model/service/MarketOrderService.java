package com.demo.web.bundle.market.model.service;

import com.demo.web.bundle.market.entity.MarketOrder;

import java.util.List;

/**
 * (MarketOrder)表服务接口
 *
 * @author makejava
 * @since 2020-06-04 20:24:18
 */
public interface MarketOrderService {
    void save(MarketOrder marketOrder);

    List<MarketOrder> getOrders(String typeId, String regionId);
}