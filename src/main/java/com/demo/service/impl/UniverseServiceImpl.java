package com.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.demo.service.UniverseService;
import com.demo.utils.ListUtils;
import com.demo.utils.MyRT;
import com.demo.utils.SpringUtils;
import com.demo.utils.StringUtil;
import com.demo.web.bundle.universe.entity.System;
import com.demo.web.bundle.universe.entity.*;
import com.demo.web.bundle.universe.model.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Service("universeService")
@Component
@ConfigurationProperties(prefix = "esi.universe")
public class UniverseServiceImpl implements UniverseService
{
    @Autowired
    RegionService regionService;

    @Autowired
    ConstellationService constellationService;

    @Autowired
    SystemService systemService;

    @Autowired
    StationService stationService;

    @Autowired
    TypeService typeService;

    private String pre;

    private String pre163;

    private int threadNums;

    //实际参数
    private HashMap<String, Object> params;

    //待填充的参数模板
    private static String queryParams = "/?datasource={datasource}&language={language}&page={page}";

    private static String queryParams163 = "/?datasource={datasource163}&language={language}&page={page}";

    /**
     * @Param url
     * @Param queryParams
     * @Param localParams
     * 根据 参数 自动 获取该类型全部的id
     */
    public List<String> getAllIdsByPage(String url, String queryParams,
            Map<String, Object> localParams)
    {
        List<String> ids = new ArrayList<>();
        String jsonSting;
        //起始页码为1
        int pageNum = 1;
        while (true)
        {
            localParams.put("page", pageNum);
            jsonSting = MyRT.getReq(url + queryParams, localParams);
            if (jsonSting.equals("[]"))
            {
                break;
            }
            ids.addAll(JSONArray.parseArray(jsonSting)
                    .toJavaList(String.class));
            pageNum++;
        }
        return ids;
    }

    /**
     * 遍历每个typeid并查询详细数据，存入数据库
     */
    @Override
    public void updateTypes()
    {
        HashMap<String, Object> localParams = params;
        String querys = queryParams163;
        String typesUrl = pre163 + "types/";

        List<String> allTypes = getAllIdsByPage(typesUrl,
                queryParams163,
                params);

        List<List<String>> typeIDList = ListUtils.groupList(allTypes);
        UniverseServiceImpl universeServiceImplProxy = SpringUtils.getBean(
                UniverseServiceImpl.class);
        for (List<String> lt : typeIDList)
        {
            universeServiceImplProxy.updateTypesTask(lt);
        }
    }

    @Async
    public void updateTypesTask(List<String> types)
    {
        HashMap<String, Object> localParams = params;
        String querys = queryParams163;
        String typesDetailUrl = pre163 + "types/";
        String jsonStr;

        for (String id : types)
        {
            jsonStr = MyRT.getReq(typesDetailUrl + id + querys, localParams);
            typeService.save(JSON.parseObject(jsonStr, Type.class));
        }
    }

    /**
     * 星域相关
     * region相关
     */
    @Override
    public void updateRegions()
    {
        String regionsUrl = pre + "regions/";
        String regionsDetailUrl = regionsUrl;
        Region tar;
        //获取所有id
        List<String> lt = MyRT.restTemplate.getForObject(regionsUrl,
                List.class,
                params);

        //获取所有region的信息list
        List<String> jsonList = MyRT.getReqMultiById(regionsDetailUrl,
                queryParams,
                params,
                lt);

        //遍历，处理后存储
        for (String str : jsonList)
        {
            tar = JSON.parseObject(str, Region.class);
            //将 [1,2,3]变为 1,2,3
            tar.setConstellations(
                    StringUtil.handelListInEsiRes(
                            tar.getConstellations()));
            regionService.save(tar);
        }
    }

    /**
     * 星座相关
     */
    @Override
    public void updateConstellations()
    {
        String constellatonsUrl = pre + "constellations/";
        String constellatonsDetailUrl = constellatonsUrl;
        String jsonStr;
        Constellation tar;

        List<Integer> lt = MyRT.restTemplate.getForObject(constellatonsUrl,
                List.class,
                params);
        for (int id : lt)
        {
            jsonStr = MyRT.getReq(constellatonsDetailUrl + id + queryParams,
                    params);
            tar = JSON.parseObject(jsonStr, Constellation.class);
            //将 [1,2,3]变为 1,2,3
            //星系
            tar.setSystems(
                    StringUtil.handelListInEsiRes(
                            tar.getSystems()));
            constellationService.save(tar);
        }
    }

    /**
     * 星系相关
     */
    @Override
    public void updateSystems()
    {
        String systemsUrl = pre + "systems/";
        String systemsDetailUrl = systemsUrl;
        String jsonStr;
        System tar;

        List<String> lt = MyRT.restTemplate.getForObject(systemsUrl,
                List.class,
                params);
        for (String id : lt)
        {

            jsonStr = MyRT.getReq(systemsDetailUrl + id + queryParams,
                    params);
            tar = JSON.parseObject(jsonStr, System.class);
            //将 [1,2,3]变为 1,2,3
            //星门
            tar.setStargates(
                    StringUtil.handelListInEsiRes(
                            tar.getStargates()));
            //空间站
            tar.setStations(
                    StringUtil.handelListInEsiRes(
                            tar.getStations()));
            systemService.save(tar);

        }
    }

    @Override
    public void updateStations()
    {
        String stationDetailUrl = pre163 + "stations/";
        HashMap<String, Object> localParams = params;
        String querys = this.queryParams163;
        List<String> stationIds = systemService.findAllStationIds();
        String jsonStr;

        for (String id : stationIds)
        {
            jsonStr = MyRT.getReq(stationDetailUrl + id + querys,
                    localParams);
            stationService.save(JSON.parseObject(jsonStr, Station.class));
        }

    }

    @Override
    public List<String> showHighSecureRegionsID()
    {
        return regionService.showHighSecureRegionsID();
    }

}
