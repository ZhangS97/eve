package com.demo.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class MyRT
{
    public static RestTemplate restTemplate = new RestTemplate();

    /**
     * 单次操作
     */
    public static String getReq(String url, Map<String, Object> params)
    {
        ResponseEntity<String> responseEntity;
        while (true)
        {
            try
            {
                responseEntity = MyRT.restTemplate.getForEntity(url,
                        String.class,
                        params);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
            break;
        }
        return responseEntity.getBody();
    }

    /**
     * getForObject默认返回List<Integer>
     * 需要通过流的方式转为List<String>
     */
    public static List<String> getReqCastIntListToStrList(String url,
            Map<String, Object> params)
    {
        List<Integer> integerList = restTemplate.getForObject(url,
                List.class,
                params);
        return integerList.stream().map(String::valueOf).collect(
                Collectors.toList());
    }

    /**
     * @Param List<String> id
     * @Param url          链接前缀
     * @Param queryParams 待填入参数模板
     * @Param localParams 具体参数
     * 多线程发送http请求
     */
    public static List<String> getReqMultiById(String url, String queryParams,
            Map localParams,
            List<String> ids)
    {
        List<List<String>> idList = ListUtils.groupList(ids);
        List<String> resList = new ArrayList<>();
        AsyncUtil asyncUtil = SpringUtils.getBean(AsyncUtil.class);
        for (List<String> lt : idList)
        {
            Log.logger.info(lt.toString());
            try
            {
                resList.addAll(asyncUtil.getReqMultiByIdTask(url,
                        queryParams,
                        localParams,
                        lt).get());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }
        return resList;
    }

    public void testGet()
    {
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