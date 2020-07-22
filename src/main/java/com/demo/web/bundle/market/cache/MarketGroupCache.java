package com.demo.web.bundle.market.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.CacheableTree;
import com.demo.web.bundle.market.entity.MarketGroups;
import com.demo.web.bundle.market.model.service.MarketGroupsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MarketGroupCache extends CacheableTree
{

    public static final String NAME = "";

    public static final String KEY_ID = "market_group:id:";

    public static final String KEY_CHILDREN_ID = "market_group:children_id:";

    public static final String KEY_PARENT_ID = "market_group:parent_id:";

    @Autowired
    MarketGroupsService service;

    public void doLoad()
    {
        List<MarketGroups> marketGroupsList = service.findAll();
        for (MarketGroups marketGroups : marketGroupsList)
        {
            //存储当前对象，及其父子id，共三种信息
            saveOneTree(
                    KEY_ID + marketGroups.getMarketGroupId(),
                    JSON.toJSONString(marketGroups),
                    KEY_CHILDREN_ID + marketGroups.getMarketGroupId(),
                    marketGroups.getTypes(),
                    KEY_PARENT_ID + marketGroups.getMarketGroupId(),
                    marketGroups.getParentGroupId());

        }
    }

}
