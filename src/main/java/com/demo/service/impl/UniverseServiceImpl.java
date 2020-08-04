package com.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.demo.service.UniverseService;
import com.demo.utils.MyRT;
import com.demo.utils.StringUtil;
import com.demo.web.bundle.universe.entity.System;
import com.demo.web.bundle.universe.entity.*;
import com.demo.web.bundle.universe.model.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
     * 根据 参数 自动 获取该类型全部的id
     *
     * @param url         链接
     * @param queryParams 待填充的参数模板
     * @param localParams 实际参数
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
     * 在版本更新时调用
     * 例如：
     * Region,Constellation,System,Type
     */
    @Override
    public void updateUniverseInfo()
    {
        //更新天体
        updateRegions();
        updateConstellations();
        updateSystems();
        //更新人造建筑
        updateStations();
        //更新物品
        updateTypes();
    }

    /**
     * 遍历每个typeid并查询详细数据，存入数据库
     */
    @Override
    public void updateTypes()
    {
        String typesUrl = pre + "types/";

        //获取所有ids
        List<String> ids = getAllIdsByPage(typesUrl,
                queryParams,
                params);

        //获取所有type的信息jsonList
        List<String> jsonList = MyRT.getReqMultiById(typesUrl,
                queryParams,
                params,
                ids);

        //遍历，处理后存储
        for (String str : jsonList)
        {
            typeService.save(JSON.parseObject(str, Type.class));
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
        List<String> ids = MyRT.getReqCastIntListToStrList(regionsUrl,
                params);

        //获取所有region的信息jsonList
        List<String> jsonList = MyRT.getReqMultiById(regionsDetailUrl,
                queryParams,
                params,
                ids);

        //遍历，处理后存储
        for (String str : jsonList)
        {
            tar = JSON.parseObject(str, Region.class);
            //将 [1,2,3]变为 1,2,3
            //设置星座
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
        Constellation tar;

        //获取所有id
        List<String> ids = MyRT.getReqCastIntListToStrList(constellatonsUrl,
                params);

        //获取所有region的信息jsonList
        List<String> jsonList = MyRT.getReqMultiById(constellatonsDetailUrl,
                queryParams,
                params,
                ids);

        //遍历，处理后存储
        for (String str : jsonList)
        {
            tar = JSON.parseObject(str, Constellation.class);
            //将 [1,2,3]变为 1,2,3
            //设置星座
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
        System tar;

        //获取所有id
        List<String> ids = MyRT.getReqCastIntListToStrList(systemsUrl,
                params);

        //获取所有system的信息jsonList
        List<String> jsonList = MyRT.getReqMultiById(systemsDetailUrl,
                queryParams,
                params,
                ids);

        //遍历，处理后存储
        for (String str : jsonList)
        {
            tar = JSON.parseObject(str, System.class);
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

    /**
     * 空间站相关
     */
    @Override
    public void updateStations()
    {
        String stationDetailUrl = pre + "stations/";
        //从数据库中获取所有stationIds
        List<String> stationIds = systemService.findAllStationIds();

        //获取所有station的信息jsonList
        List<String> jsonList = MyRT.getReqMultiById(stationDetailUrl,
                queryParams,
                params,
                stationIds);

        //遍历，处理后存储
        for (String str : jsonList)
        {
            stationService.save(JSON.parseObject(str, Station.class));
        }
    }

    @Override
    public List<String> getHighSecureRegionsID()
    {
        return regionService.getHighSecureRegionsID();
    }

}
