package com.demo.web.bundle.market.model.service.impl;

import com.demo.web.bundle.market.entity.MarketOrder;
import com.demo.web.bundle.market.model.dao.MarketOrderDao;
import com.demo.web.bundle.market.model.service.MarketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (MarketOrder)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 20:24:18
 */
@Service("marketOrdersService")
public class MarketOrderServiceImpl implements MarketOrderService {
    @Autowired
    MarketOrderDao dao;

    @Override
    public void save(MarketOrder marketOrder) {
        dao.save(marketOrder);
    }

    @Override
    public List<MarketOrder> getOrders(String typeId, String regionId) {
        return dao.findByTypeIdAndRegionId(typeId, regionId);
    }

}