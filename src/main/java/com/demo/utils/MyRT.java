package com.demo.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class MyRT {
    public static RestTemplate restTemplate = new RestTemplate();

    public static String getReq(String url, HashMap<String, Object> params) {
        ResponseEntity<String> responseEntity = null;
        String jsonStr;
        while (true) {
            try {
                responseEntity = MyRT.restTemplate.getForEntity(url, String.class, params);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            break;
        }
        jsonStr = responseEntity.getBody();
        return jsonStr;
    }

    public void testGet() {
//        // 使用方法一，传参替换
//        url = "https://story.hhui.top/detail?id={?}";
//        res = restTemplate.getForObject(url, InnerRes.class, "666106231640");
//        System.out.println(res);
//
//        // 使用方法二，map传参
//        url = "https://story.hhui.top/detail?id={id}";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", 666106231640L);
//        res = restTemplate.getForObject(url, InnerRes.class, params);
//        System.out.println(res);
//
//        // 使用方法三，URI访问
//        URI uri = URI.create("https://story.hhui.top/detail?id=666106231640");
//        res = restTemplate.getForObject(uri, InnerRes.class);
//        System.out.println(res);
    }
}