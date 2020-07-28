package com.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.demo.service.UniverseService;
import com.demo.utils.ListUtils;
import com.demo.utils.MyRT;
import com.demo.utils.SpringUtils;
import com.demo.web.bundle.universe.entity.Constellation;
import com.demo.web.bundle.universe.entity.Region;
import com.demo.web.bundle.universe.entity.System;
import com.demo.web.bundle.universe.entity.Type;
import com.demo.web.bundle.universe.model.service.ConstellationService;
import com.demo.web.bundle.universe.model.service.RegionService;
import com.demo.web.bundle.universe.model.service.SystemService;
import com.demo.web.bundle.universe.model.service.TypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    TypeService typeService;

    private String pre;

    private String pre163;

    private int threadNums;

    private HashMap<String, Object> params;

    private static String queryParams = "/?datasource={datasource}&language={language}&page={page}";

    private static String queryParams163 = "/?datasource={datasource163}&language={language}&page={page}";

    /*
    从网易api获取数据
    获取所有typeid并返回list
     */
    @Override
    public List<String> getAllTypeID()
    {
        HashMap<String, Object> localParams = params;
        String querys = queryParams163;
        String typesUrl = pre163 + "types/";
        List<String> types = new ArrayList<String>();
        String jsonSting;
        int pageNum = 1;
        while (true)
        {
            localParams.put("page", pageNum);
            jsonSting = MyRT.getReq(typesUrl + querys, localParams);
            if (jsonSting.equals("[]"))
            {
                break;
            }
            types.addAll(JSONArray.parseArray(jsonSting)
                    .toJavaList(String.class));
            pageNum++;
        }
        return types;
    }

    /*
    遍历每个typeid并查询详细数据，存入数据库
     */
    @Override
    public void updateTypes()
    {
        List<String> allTypes = getAllTypeID();
        int length = allTypes.size() / threadNums + 1;
        List<List<String>> typeIDList = ListUtils.groupList(allTypes, length);
        UniverseServiceImpl universeServiceImplProxy = SpringUtils.getBean(
                UniverseServiceImpl.class);
        for (List<String> lt : typeIDList)
        {
            universeServiceImplProxy.updateTypesTask(lt);
        }
        java.lang.System.out.println("updateTypes() finished");
    }

    @Async
    public void updateTypesTask(List<String> types)
    {
        HashMap<String, Object> localParams = params;
        String querys = this.queryParams163;
        String typesUrl = pre163 + "types/";
        String typesDetailUrl = typesUrl;
        String jsonStr;
        for (String id : types)
        {
            jsonStr = MyRT.getReq(typesDetailUrl + id + querys, localParams);
            java.lang.System.out.println("id" + id);
            typeService.save(JSON.parseObject(jsonStr, Type.class));
        }
    }

    @Override
    public void updateRegions()
    {
        String regionsUrl = pre + "regions/";
        String regionsDetailUrl = regionsUrl;
        String jsonStr;
        List<String> lt = MyRT.restTemplate.getForObject(regionsUrl,
                List.class,
                params);
        for (String regionID : lt)
        {
            jsonStr = MyRT.getReq(regionsDetailUrl + regionID + queryParams,
                    params);
            java.lang.System.out.println(regionID + jsonStr);
            Region tar = JSON.parseObject(jsonStr, Region.class);
            regionService.save(tar);
        }
    }

    @Override
    public void updateConstellations()
    {
        String constellatonsUrl = pre + "constellations/";
        String constellatonsDetailUrl = constellatonsUrl;
        String jsonStr;
        Constellation tar;
        List<String> lt = MyRT.restTemplate.getForObject(constellatonsUrl,
                List.class,
                params);
        for (String id : lt)
        {
            jsonStr = MyRT.getReq(constellatonsDetailUrl + id + queryParams,
                    params);
            tar = JSON.parseObject(jsonStr, Constellation.class);
            constellationService.save(tar);
        }
        java.lang.System.out.println("updateConstellations finish");
    }

    @Override
    public void updateSystems()
    {
        String systemsUrl = pre + "systems/";
        String systemsDetailUrl = systemsUrl;
        String jsonStr;
        ResponseEntity<String> responseEntity;
        HttpStatus status;
        System tar;
        List<String> lt = MyRT.restTemplate.getForObject(systemsUrl,
                List.class,
                params);
        for (String id : lt)
        {
            responseEntity = MyRT.restTemplate.getForEntity(
                    systemsDetailUrl + id + queryParams, String.class, params);
            jsonStr = responseEntity.getBody();
            status = responseEntity.getStatusCode();
            if (status.is5xxServerError())
            {
                java.lang.System.out.println("5xx");
            }
            java.lang.System.out.println(id + jsonStr);
            tar = JSON.parseObject(jsonStr, System.class);
            systemService.save(tar);
        }
    }

    @Override
    public List<String> showHighSecureRegionsID()
    {
        return regionService.showHighSecureRegionsID();
    }

}
