package com.demo.web.bundle.universe.cache;

import com.alibaba.fastjson.JSON;
import com.demo.component.Cacheable;
import com.demo.web.bundle.universe.entity.Type;
import com.demo.web.bundle.universe.model.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TypeCache extends Cacheable
{

    private static final long serialVersionUID = -7876629741785837545L;

    public static final String KEY_ID = "type:id:";

    public static final String KEY_GROUP_ID = "type:group_id:";

    public static final String KEY_MARKET_GROUP_ID = "type:market_group_id:";

    @Autowired
    TypeService typeService;

    public void doLoad()
    {

    }

    public void doLoadMarketTypes()
    {
        List<Type> types = typeService.findAllMarketTypes();
        //存放父id及其对应的下一层儿子具体信息，双层树状结构
        Map<String, List> marketGroupMap = new HashMap<>();
        //中间量
        List<String> list = new ArrayList<>();
        for (Type type : types)
        {
            //根据typeId存储当前对象
            saveOne(KEY_ID + type.getTypeId(), JSON.toJSONString(type));

            //以marketGroupId为root，以及下属对象为孩子，构建二层数
            //寻找当前type所属的树，并存入完整type
            list = marketGroupMap.get(type.getMarketGroupId());
            if (list == null)
            {
                //若不存在，则直接添加
                list = new ArrayList<>();
                list.add(JSON.toJSONString(type));
            }
            else
            {
                //若存在则直接add
                list.add(type.getMarketGroupId());
            }
            marketGroupMap.put(type.getMarketGroupId(), list);
        }

        Set<String> keys = marketGroupMap.keySet();
        //遍历每个marketGroupId并存入相应的值
        for (String marketGroupId : keys)
        {
            saveOne(KEY_MARKET_GROUP_ID + marketGroupId,
                    JSON.toJSONString(marketGroupMap.get(marketGroupId)));
        }
    }
}
