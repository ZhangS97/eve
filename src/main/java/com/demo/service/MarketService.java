package com.demo.service;

import com.demo.web.bundle.market.entity.MarketOrder;
import com.demo.web.bundle.universe.entity.Type;

import java.util.List;

public interface MarketService
{
    void updateMarketGroups();

    void updateMarketOrders();

    List<List<MarketOrder>> getOrders(String regionId, List<String> typeIds);

    List<Type> getTypeDetails(String marketGroupId);

    List<String> getAllTypeIds(String marketGroupId);
}
