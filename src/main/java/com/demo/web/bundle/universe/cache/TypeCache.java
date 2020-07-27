package com.demo.web.bundle.universe.cache;

import com.demo.component.Cacheable;
import com.demo.web.bundle.universe.entity.Type;
import com.demo.web.bundle.universe.model.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map groupMap = new HashMap();
        Map marketGroupMap = new HashMap();

        for (Type type : types)
        {

        }
    }
}
