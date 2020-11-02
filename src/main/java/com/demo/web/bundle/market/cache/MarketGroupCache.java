package com.demo.web.bundle.market.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.cache.CacheableTree;
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

    public static final String NAME = "";

    // 根据 markerGroup得id存取数据
    public static final String KEY_ID = "market_group:id:";
    // 根据 markerGroup得id存取该数据得所有孩子
    public static final String KEY_PARENT_ID = "market_group:parent_id:";

    @Autowired
    @Qualifier("marketGroupsService")
    MarketGroupService service;

    public void doLoad()
    {
        List<MarketGroup> marketGroupList = service.findAll();

        //存放父id及其对应的下一层儿子id，双层树状结构
        Map<String, List> map = new HashMap<>();

        List<String> list;
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

            //存储当前对象
            saveOne(KEY_ID + marketGroup.getMarketGroupId(),
                    JSON.toJSONString(marketGroup));
        }

        //根据父级id存储下属所有节点
        for (String str : map.keySet())
        {
            saveOne(KEY_PARENT_ID + str, JSON.toJSONString(map.get(str)));
        }

    }

    // 取整层
    public List<MarketGroup> fetchOneLeveByParentId(String pid){
        String[] ids = fetchOne(KEY_PARENT_ID+pid).split(",");
        List<MarketGroup> marketGroupList = new ArrayList<>();
        for (String id:ids){
            marketGroupList.add(fetchOneById(id));
        }
        return marketGroupList;
    }

    /**
     *
     * @param pid
     * @return
     */
    public List<MarketGroup> fetchOneLeveAndParentByParentId(String pid){
         List<MarketGroup> marketGroupList = fetchOneLeveByParentId(pid);
         marketGroupList.add(0,fetchOneById(pid));
        return marketGroupList;
    }


    /**
     * 取单个
     * @param id
     * @return
     */
    public MarketGroup fetchOneById(String id){
        return JSON.parseObject(fetchOne(KEY_ID+id),MarketGroup.class);
    }

}
