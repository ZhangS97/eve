package com.demo.web.bundle.market.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.CacheableTree;
import com.demo.web.bundle.market.entity.MarketGroups;
import com.demo.web.bundle.market.model.service.MarketGroupsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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
        //存放父id及其对应的下一层儿子id，双层树状结构
        Map<String, List> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        for (MarketGroups marketGroups : marketGroupsList)
        {
            //根据父id 查看是否存在兄弟list
            list = map.get(marketGroups.getParentGroupId());
            if (list == null)
            {
                //若不存在，则直接添加
                map.put(marketGroups.getParentGroupId(),
                        Arrays.asList(marketGroups.getMarketGroupId()));
            }
            else
            {
                //若存在则add，并更新
                list.add(marketGroups.getMarketGroupId());
                map.put(marketGroups.getParentGroupId(), list);
            }

            //存储当前对象，及其父子id，共三种信息，
            saveOneTree(
                    KEY_ID + marketGroups.getMarketGroupId(),
                    JSON.toJSONString(marketGroups),
                    KEY_CHILDREN_ID + marketGroups.getMarketGroupId(),
                    marketGroups.getTypes(),
                    KEY_PARENT_ID + marketGroups.getMarketGroupId(),
                    marketGroups.getParentGroupId());

        }

        for (String str : map.keySet())
        {
            saveOne(str, map.get(str).toString());
        }

    }

}
