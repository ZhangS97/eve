package com.demo.service.impl;

import com.demo.dao.MarketOrdersDao;
import com.demo.entity.MarketOrders;
import com.demo.service.MarketOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (MarketOrders)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 20:24:18
 */
@Service("marketOrdersService")
public class MarketOrdersServiceImpl implements MarketOrdersService {
    @Autowired
    MarketOrdersDao dao;

    @Override
    public void save(MarketOrders marketOrders) {
        dao.save(marketOrders);
    }

    @Override
    public List<MarketOrders> getOrders(int typeId, int regionId) {
        return dao.findByTypeIdAndRegionId(typeId, regionId);
    }


}