package com.demo.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
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
        List<Future<List<String>>> futures = new ArrayList<>();
        MyRT myRTProxy = SpringUtils.getBean(MyRT.class);
        for (List<String> lt : idList)
        {
//            Log.logger.info(lt.toString());
            System.out.println(lt);
            futures.add(myRTProxy.getReqMultiByIdTask(url,
                    queryParams,
                    localParams,
                    lt));
        }

        for (Future<List<String>> future : futures)
        {
            try
            {
                resList.addAll(future.get());
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

    @Async
    Future<List<String>> getReqMultiByIdTask(String url,
            String queryParams,
            Map localParams,
            List<String> ids)
    {
        System.out.println(ids);
        System.out.println(System.currentTimeMillis());
        String jsonStr;
        List<String> list = new ArrayList<>();

        for (String id : ids)
        {
            jsonStr = MyRT.getReq(url + id + queryParams, localParams);
            list.add(jsonStr);
        }
        return new AsyncResult<>(list);
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