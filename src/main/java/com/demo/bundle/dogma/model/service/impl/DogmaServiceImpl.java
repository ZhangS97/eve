package com.demo.bundle.dogma.model.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.bundle.dogma.entity.Attributes;
import com.demo.bundle.dogma.model.service.AttributesService;
import com.demo.bundle.dogma.model.service.DogmaService;
import com.demo.utils.MyRT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("dogmaService")
@Component
@ConfigurationProperties(prefix = "esi.dogma")
public class DogmaServiceImpl implements DogmaService
{
    @Autowired
    AttributesService attributesService;

    private String pre;

    private HashMap<String, Object> params;

    private static String queryParams = "/?datasource={datasource}&language={language}";

    @Override
    public void updateAttributes()
    {
        String attributesUrl = pre + "attributes/";
        String attributesDetailUrl = attributesUrl;
        String jsonStr;
        Attributes tar = null;
        List<Integer> lt = MyRT.restTemplate.getForObject(attributesUrl,
                List.class,
                params);
        for (int id : lt)
        {
            jsonStr = MyRT.getReq(attributesDetailUrl + id, params);
            System.out.println(id);
            tar = JSON.parseObject(jsonStr, Attributes.class);
            attributesService.save(tar);
        }
    }

    public void setPre(String pre)
    {
        this.pre = pre;
    }

    public void setParams(HashMap<String, Object> params)
    {
        this.params = params;
    }

    public static void setQueryParams(String queryParams)
    {
        DogmaServiceImpl.queryParams = queryParams;
    }

}
