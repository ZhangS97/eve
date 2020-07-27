package com.demo.web.bundle.market.model.service.impl;

import com.demo.web.bundle.market.entity.MarketGroup;
import com.demo.web.bundle.market.model.dao.MarketGroupDao;
import com.demo.web.bundle.market.model.service.MarketGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (MarketGroup)表服务实现类
 *
 * @author makejava
 * @since 2020-05-28 12:00:09
 */

@Service("marketGroupsService")
public class MarketGroupServiceImpl implements MarketGroupService {
    @Autowired
    MarketGroupDao dao;

    @Override
    public void save(MarketGroup marketGroup) {
        dao.save(marketGroup);
    }

    @Override
    public List<MarketGroup> findAll() {
        return dao.findAll();
    }

    @Override
    public String getTypeIdsByGIdAndRId(String marketGroupId) {
        return dao.getTypeIdsByGIdAndRId(marketGroupId);
    }

}