package com.demo.web.bundle.market.model.service.impl;

import com.demo.web.bundle.market.entity.MarketGroups;
import com.demo.web.bundle.market.model.dao.MarketGroupsDao;
import com.demo.web.bundle.market.model.service.MarketGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (MarketGroups)表服务实现类
 *
 * @author makejava
 * @since 2020-05-28 12:00:09
 */

@Service("marketGroupsService")
public class MarketGroupsServiceImpl implements MarketGroupsService
{
    @Autowired
    MarketGroupsDao dao;

    @Override
    public void save(MarketGroups marketGroups)
    {
        dao.save(marketGroups);
    }

    @Override
    public List<MarketGroups> findAll()
    {
        return dao.findAll();
    }

    //    public List<String> get
    public void func(List<MarketGroups> marketGroupsList)
    {
        String[] marketGroupIdHeap;

        int index = 0;
        marketGroupsList.get(index);
    }

    @Override
    public String getTypeIdsByGIdAndRId(int marketGroupId)
    {
        return dao.getTypeIdsByGIdAndRId(marketGroupId);
    }

}