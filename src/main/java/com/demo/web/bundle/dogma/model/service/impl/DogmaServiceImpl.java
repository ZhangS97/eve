package com.demo.web.bundle.dogma.model.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.utils.MyRT;
import com.demo.web.bundle.dogma.entity.Attribute;
import com.demo.web.bundle.dogma.model.service.AttributeService;
import com.demo.web.bundle.dogma.model.service.DogmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("dogmaService")
@Component
@ConfigurationProperties(prefix = "esi.dogma")
public class DogmaServiceImpl implements DogmaService {
    @Autowired
    AttributeService attributeService;

    private String pre;

    private HashMap<String, Object> params;

    private static String queryParams = "/?datasource={datasource}&language={language}";

    @Override
    public void updateAttributes() {
        String attributesUrl = pre + "attributes/";
        String attributesDetailUrl = attributesUrl;
        String jsonStr;
        Attribute tar = null;
        List<Integer> lt = MyRT.restTemplate.getForObject(attributesUrl,
                List.class,
                params);
        for (int id : lt) {
            jsonStr = MyRT.getReq(attributesDetailUrl + id, params);
            System.out.println(id);
            tar = JSON.parseObject(jsonStr, Attribute.class);
            attributeService.save(tar);
        }
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public static void setQueryParams(String queryParams) {
        DogmaServiceImpl.queryParams = queryParams;
    }

}
