package com.demo.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Component
public class AsyncUtil
{
    /**
     * 单个线程
     */
    @Async
    Future<List<String>> getReqMultiByIdTask(String url,
            String queryParams,
            Map localParams,
            List<String> ids)
    {
        Log.logger.info(ids.toString());
        String jsonStr;
        List<String> list = new ArrayList<>();

        for (String id : ids)
        {
            jsonStr = MyRT.getReq(url + id + queryParams, localParams);
            list.add(jsonStr);
        }
        return new AsyncResult<>(list);
    }

}
