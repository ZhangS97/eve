package com.demo.bundle.market.model.service;

import com.demo.bundle.market.entity.MarketOrders;
import com.demo.bundle.universe.entity.Types;

import java.util.List;

public interface MarketService
{
    void updateMarketGroups();

    void updateMarketOrders();

    List<List<MarketOrders>> getOrders(int regionId, List<Integer> typeIds);

    List<Types> getTypeDetails(int marketGroupId);

    List<Integer> getAllTypeIds(int marketGroupId);
}
