package com.demo.web.bundle.market.model.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.demo.service.UniverseService;
import com.demo.utils.ListUtils;
import com.demo.utils.MyRT;
import com.demo.utils.SpringUtils;
import com.demo.web.bundle.market.entity.MarketGroups;
import com.demo.web.bundle.market.entity.MarketOrders;
import com.demo.web.bundle.market.model.service.MarketGroupsService;
import com.demo.web.bundle.market.model.service.MarketOrdersService;
import com.demo.web.bundle.market.model.service.MarketService;
import com.demo.web.bundle.universe.entity.Types;
import com.demo.web.bundle.universe.model.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("marketService")
@Component
@ConfigurationProperties(prefix = "esi.market")
public class MarketServiceImpl implements MarketService
{
    @Autowired
    private MarketGroupsService marketGroupsService;

    @Autowired
    private MarketOrdersService marketOrdersService;

    @Autowired
    private UniverseService universeService;

    @Autowired
    private TypesService typesService;

    private String markets;

    private HashMap<String, Object> params;

    private static String queryParams = "/?datasource={datasource}&language={language}&page={page}";

    /*
    首先获取所有marketGroupID
    然后逐个请求详细数据
     */
    public void updateMarketGroups()
    {
        String groupUrl = markets + "groups/";
        String groupDetailUrl = groupUrl;
        String jsonStr;
        List<Integer> lt = MyRT.restTemplate.getForObject(groupUrl,
                List.class,
                params);
        for (int marketGroupID : lt)
        {
            jsonStr = MyRT.getReq(groupDetailUrl + marketGroupID + queryParams,
                    params);
            System.out.println(marketGroupID + jsonStr);
            MarketGroups tar = JSON.parseObject(jsonStr, MarketGroups.class);
            marketGroupsService.save(tar);
        }
    }

    @Override
    public void updateMarketOrders()
    {
        List<String> regionID = universeService.showHighSecureRegionsID();
        List<List<String>> regionIDList = ListUtils.groupList(regionID, 17);
        MarketServiceImpl marketServiceImplProxy = SpringUtils.getBean(
                MarketServiceImpl.class);
        for (List<String> lt : regionIDList)
        {
            marketServiceImplProxy.marketOrdersTask(lt);
        }
    }

    /*
    根据特定marketGroupId查找下属所有type的细节
    返回list<type>
     */
    @Override
    public List<Types> getTypeDetails(String marketGroupId)
    {
        JSONArray typesArray = JSONArray.parseArray(marketGroupsService.getTypeIdsByGIdAndRId(
                marketGroupId));
        List<Types> typesList = new ArrayList<Types>();
        for (Object o : typesArray)
        {
            typesList.add(typesService.findByTypeId((String) o));
        }
        return typesList;
    }

    /*
    根据特定marketGroupId查找下属所有typeid
    返回list<int>
     */
    @Override
    public List<String> getAllTypeIds(String marketGroupId)
    {
        JSONArray typesArray = JSONArray.parseArray(marketGroupsService.getTypeIdsByGIdAndRId(
                marketGroupId));
        List<String> typeIds = new ArrayList<>();
        for (Object o : typesArray)
        {
            typeIds.add((String) o);
        }
        return typeIds;
    }

    /*
    根据groupId查找当前group下所有typesID
    在根据typeID和regionId查找相关的所有type订单
    并返回相关orders的List<list>
     */
    @Override
    public List<List<MarketOrders>> getOrders(String regionId,
            List<String> typeIds)
    {
        List<List<MarketOrders>> tar = new ArrayList<>();
        for (String typeId : typeIds)
        {
            tar.add(marketOrdersService.getOrders(typeId, regionId));
        }
        System.out.println(tar.toString());
        return tar;
    }

    @Async("taskExecutor")
    public void marketOrdersTask(List<String> regionIds)
    {
        String orderUrl = markets;
        String orderDetailUrl = orderUrl;
        String jsonStr;
        int pageNum;
        HashMap<String, Object> orderParams = params;
        MarketOrders tar;
        for (String id : regionIds)
        {
            pageNum = 1;
            System.out.println("id" + id);
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
                    tar = JSON.parseObject(o.toString(), MarketOrders.class);
                    tar.setRegionId(id);
                    marketOrdersService.save(tar);
                }
                pageNum++;
            }
        }
    }

    public void setMarkets(String markets)
    {
        this.markets = markets;
    }

    public void setParams(HashMap<String, Object> params)
    {
        this.params = params;
    }
}
