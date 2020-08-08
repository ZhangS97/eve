package com.demo.web.bundle.market.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.CacheableTree;
import com.demo.utils.ListUtils;
import com.demo.utils.SpringUtils;
import com.demo.web.bundle.market.entity.MarketGroup;
import com.demo.web.bundle.market.model.service.MarketGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Component(value = "marketGroupCache")
public class MarketGroupCache extends CacheableTree
{

    public static final String KEY_ID = "market_group:id:";

    public static final String KEY_CHILDREN_ID = "market_group:children_id:";

    public static final String KEY_PARENT_ID = "market_group:parent_id:";

    //存放父id及其对应的下一层儿子id，双层树状结构
    private static Map<String, List<String>> map = new Hashtable<>();

    @Autowired
    @Qualifier("marketGroupsService")
    MarketGroupService service;

    public void doLoad()
    {
        List<List<MarketGroup>> allMarketGroups = ListUtils.groupList(service.findAll());

        MarketGroupCache marketGroupCacheProxy = SpringUtils.getBean(
                MarketGroupCache.class);

        for (List<MarketGroup> marketGroupList : allMarketGroups)
        {
            marketGroupCacheProxy.doLoadTask(marketGroupList);
        }

        for (String str : map.keySet())
        {
            saveOne(KEY_PARENT_ID + str, JSON.toJSONString(map.get(str)));
        }
    }

    @Async
    void doLoadTask(List<MarketGroup> marketGroups)
    {
        List<String> list;
        for (MarketGroup marketGroup : marketGroups)
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

            //存储当前对象
            saveOne(KEY_ID + marketGroup.getMarketGroupId(),
                    JSON.toJSONString(marketGroup));

            //存储子级物品单位
            if (null != marketGroup.getTypes() ||
                    (!"".equals(marketGroup.getTypes())))
            {
                //非空则存储
                saveOne(KEY_CHILDREN_ID + marketGroup.getMarketGroupId(),
                        marketGroup.getTypes());
            }

        }
    }
}
