package com.demo.web.bundle.market.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.CacheableTree;
import com.demo.web.bundle.market.entity.MarketGroup;
import com.demo.web.bundle.market.model.service.MarketGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "marketGroupCache")
public class MarketGroupCache extends CacheableTree
{

    public static final String KEY_ID = "market_group:id:";

    public static final String KEY_CHILDREN_ID = "market_group:children_id:";

    public static final String KEY_PARENT_ID = "market_group:parent_id:";

    @Autowired
    @Qualifier("marketGroupsService")
    MarketGroupService service;

    public void doLoad()
    {
        List<MarketGroup> marketGroupList = service.findAll();

        //存放父id及其对应的下一层儿子id，双层树状结构
        Map<String, List> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        for (MarketGroup marketGroup : marketGroupList)
        {
            //根据父id 查看是否存在兄弟list
            list = map.get(marketGroup.getParentGroupId());
            if (list == null)
            {
                //若不存在，则直接添加
                list = new ArrayList<>();
                list.add(marketGroup.getMarketGroupId());
                map.put(marketGroup.getParentGroupId(), list);
            }
            else
            {
                //若存在则add，并更新
                list.add(marketGroup.getMarketGroupId());
                map.put(marketGroup.getParentGroupId(), list);
            }

            //存储当前对象，及其父子id，共三种信息，
            saveOneTree(
                    KEY_ID + marketGroup.getMarketGroupId(),
                    JSON.toJSONString(marketGroup),
                    KEY_CHILDREN_ID + marketGroup.getMarketGroupId(),
                    marketGroup.getTypes(),
                    KEY_PARENT_ID + marketGroup.getMarketGroupId(),
                    marketGroup.getParentGroupId());

        }

        for (String str : map.keySet())
        {
            saveOne(str, map.get(str).toString());
        }

    }

}
