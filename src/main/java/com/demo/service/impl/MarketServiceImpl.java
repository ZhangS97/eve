package com.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.demo.service.MarketService;
import com.demo.service.UniverseService;
import com.demo.utils.ListUtils;
import com.demo.utils.MyRT;
import com.demo.utils.BeanUtils;
import com.demo.utils.StringUtil;
import com.demo.web.bundle.market.entity.MarketGroup;
import com.demo.web.bundle.market.entity.MarketOrder;
import com.demo.web.bundle.market.model.service.MarketGroupService;
import com.demo.web.bundle.market.model.service.MarketOrderService;
import com.demo.web.bundle.universe.entity.Type;
import com.demo.web.bundle.universe.model.service.TypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Service("marketService")
@ConfigurationProperties(prefix = "esi.market")
public class MarketServiceImpl implements MarketService
{
    @Autowired
    private MarketGroupService marketGroupService;

    @Autowired
    private MarketOrderService marketOrderService;

    @Autowired
    private UniverseService universeService;

    @Autowired
    private TypeService typeService;

    private String markets;

    private HashMap<String, Object> params;

    private static String queryParams = "/?datasource={datasource}&language={language}&page={page}";

    /**
     * 首先获取所有marketGroupID
     * 然后逐个请求详细数据
     */
    public void updateMarketGroups()
    {
        String groupUrl = markets + "groups/";
        MarketGroup tar;

        //获取所有id
        List<String> ids = MyRT.getReqCastIntListToStrList(groupUrl, params);

        //获取所有system的信息jsonList
        List<String> jsonList = MyRT.getReqMultiById(groupUrl,
                queryParams,
                params,
                ids);
        
        //遍历，处理后存储
        for (String str : jsonList)
        {
            tar = JSON.parseObject(str, MarketGroup.class);
            //将 [1,2,3]变为 1,2,3
            tar.setTypes(StringUtil.handelListInEsiRes(tar.getTypes()));
            marketGroupService.save(tar);
        }

    }

    @Async("taskExecutor")
    public void marketOrdersTask(List<String> regionIds)
    {
        String orderUrl = markets;
        String orderDetailUrl = orderUrl;
        String jsonStr;
        int pageNum;
        HashMap<String, Object> orderParams = params;
        MarketOrder tar;
        for (String id : regionIds)
        {
            pageNum = 1;
            while (true)
            {
//              System.out.println("id" + id + "pageNum" + pageNum);
                orderParams.put("page", pageNum);
                jsonStr = MyRT.getReq(markets + id + "/orders" + queryParams,
                        orderParams);
                if (jsonStr.equals("[]"))
                {
                    break;
                }
                JSONArray jArray = JSONArray.parseArray(jsonStr);
                for (Object o : jArray)
                {
                    tar = JSON.parseObject(o.toString(), MarketOrder.class);
                    tar.setRegionId(id);
                    marketOrderService.save(tar);
                }
                pageNum++;
            }
        }
    }

    /**
     * 根据特定marketGroupId查找下属所有type的细节
     * 返回list<type>
     */
    @Override
    public List<Type> getTypeDetails(String marketGroupId)
    {
        JSONArray typesArray = JSONArray.parseArray(marketGroupService.getTypeIdsByGIdAndRId(
                marketGroupId));
        List<Type> typeList = new ArrayList<Type>();
        for (Object o : typesArray)
        {
            typeList.add(typeService.findByTypeId((String) o));
        }
        return typeList;
    }

    /**
     * 根据特定marketGroupId查找下属所有typeid
     * 返回list<int>
     */
    @Override
    public List<String> getAllTypeIds(String marketGroupId)
    {
        JSONArray typesArray = JSONArray.parseArray(marketGroupService.getTypeIdsByGIdAndRId(
                marketGroupId));
        List<String> typeIds = new ArrayList<>();
        for (Object o : typesArray)
        {
            typeIds.add((String) o);
        }
        return typeIds;
    }

    /**
     * 根据groupId查找当前group下所有typesID
     * 在根据typeID和regionId查找相关的所有type订单
     * 并返回相关orders的List<list>
     */
    @Override
    public List<List<MarketOrder>> getOrders(String regionId,
            List<String> typeIds)
    {
        List<List<MarketOrder>> tar = new ArrayList<>();
        for (String typeId : typeIds)
        {
            tar.add(marketOrderService.getOrders(typeId, regionId));
        }
        return tar;
    }

    @Override
    public void updateMarketOrders()
    {
        List<String> regionID = universeService.getHighSecureRegionsID();
        List<List<String>> regionIDList = ListUtils.groupList(regionID);
        MarketServiceImpl marketServiceImplProxy = BeanUtils.getBean(
                MarketServiceImpl.class);
        for (List<String> lt : regionIDList)
        {
            marketServiceImplProxy.marketOrdersTask(lt);
        }
    }

}
