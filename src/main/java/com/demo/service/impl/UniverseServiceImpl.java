package com.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.demo.bundle.universe.entity.Constellations;
import com.demo.bundle.universe.entity.Regions;
import com.demo.bundle.universe.entity.Systems;
import com.demo.bundle.universe.entity.Types;
import com.demo.bundle.universe.model.service.ConstellationsService;
import com.demo.bundle.universe.model.service.RegionsService;
import com.demo.bundle.universe.model.service.SystemsService;
import com.demo.bundle.universe.model.service.TypesService;
import com.demo.service.UniverseService;
import com.demo.utils.ListUtils;
import com.demo.utils.MyRT;
import com.demo.utils.SpringUtils;
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

@Service("universeService")
@Component
@ConfigurationProperties(prefix = "esi.universe")
public class UniverseServiceImpl implements UniverseService
{
    @Autowired
    RegionsService regionsService;

    @Autowired
    ConstellationsService constellationsService;

    @Autowired
    SystemsService systemsService;

    @Autowired
    TypesService typesService;

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
    public List<Integer> getAllTypeID()
    {
        HashMap<String, Object> localParams = params;
        String querys = queryParams163;
        String typesUrl = pre163 + "types/";
        List<Integer> types = new ArrayList<Integer>();
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
                    .toJavaList(Integer.class));
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
        List<Integer> allTypes = getAllTypeID();
        int length = allTypes.size() / threadNums + 1;
        List<List<Integer>> typeIDList = ListUtils.groupList(allTypes, length);
        UniverseServiceImpl universeServiceImplProxy = SpringUtils.getBean(
                UniverseServiceImpl.class);
        for (List<Integer> lt : typeIDList)
        {
            universeServiceImplProxy.updateTypesTask(lt);
        }
        System.out.println("updateTypes() finished");
    }

    @Async
    public void updateTypesTask(List<Integer> types)
    {
        HashMap<String, Object> localParams = params;
        String querys = this.queryParams163;
        String typesUrl = pre163 + "types/";
        String typesDetailUrl = typesUrl;
        String jsonStr;
        for (int id : types)
        {
            jsonStr = MyRT.getReq(typesDetailUrl + id + querys, localParams);
            System.out.println("id" + id);
            typesService.save(JSON.parseObject(jsonStr, Types.class));
        }
    }

    @Override
    public void updateRegions()
    {
        String regionsUrl = pre + "regions/";
        String regionsDetailUrl = regionsUrl;
        String jsonStr;
        List<Integer> lt = MyRT.restTemplate.getForObject(regionsUrl,
                List.class,
                params);
        for (int regionID : lt)
        {
            jsonStr = MyRT.getReq(regionsDetailUrl + regionID + queryParams,
                    params);
            System.out.println(regionID + jsonStr);
            Regions tar = JSON.parseObject(jsonStr, Regions.class);
            regionsService.save(tar);
        }
    }

    @Override
    public void updateConstellations()
    {
        String constellatonsUrl = pre + "constellations/";
        String constellatonsDetailUrl = constellatonsUrl;
        String jsonStr;
        Constellations tar;
        List<Integer> lt = MyRT.restTemplate.getForObject(constellatonsUrl,
                List.class,
                params);
        for (int id : lt)
        {
            jsonStr = MyRT.getReq(constellatonsDetailUrl + id + queryParams,
                    params);
            tar = JSON.parseObject(jsonStr, Constellations.class);
            constellationsService.save(tar);
        }
        System.out.println("updateConstellations finish");
    }

    @Override
    public void updateSystems()
    {
        String systemsUrl = pre + "systems/";
        String systemsDetailUrl = systemsUrl;
        String jsonStr;
        ResponseEntity<String> responseEntity;
        HttpStatus status;
        Systems tar;
        List<Integer> lt = MyRT.restTemplate.getForObject(systemsUrl,
                List.class,
                params);
        for (int id : lt)
        {
            responseEntity = MyRT.restTemplate.getForEntity(
                    systemsDetailUrl + id + queryParams, String.class, params);
            jsonStr = responseEntity.getBody();
            status = responseEntity.getStatusCode();
            if (status.is5xxServerError())
            {
                System.out.println("5xx");
            }
            System.out.println(id + jsonStr);
            tar = JSON.parseObject(jsonStr, Systems.class);
            systemsService.save(tar);
        }
    }

    @Override
    public List<Integer> showHighSecureRegionsID()
    {
        return regionsService.showHighSecureRegionsID();
    }

    public static void setQueryParams163(String queryParams163)
    {
        UniverseServiceImpl.queryParams163 = queryParams163;
    }

    public void setPre(String pre)
    {
        this.pre = pre;
    }

    public void setParams(HashMap<String, Object> params)
    {
        this.params = params;
    }

    public void setPre163(String pre163)
    {
        this.pre163 = pre163;
    }

    public void setThreadNums(int threadNums)
    {
        this.threadNums = threadNums;
    }
}
