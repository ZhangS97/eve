package com.demo.controllers;

import com.demo.service.MarketService;
import com.demo.web.bundle.market.cache.MarketGroupCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("market")
public class MarketController
{
    @Autowired
    private MarketService service;

    @Autowired
    private MarketGroupCache marketGroupCache;

    @RequestMapping(value = "/markets/orders", method = RequestMethod.GET)
    @ResponseBody
    public String getOrders(int regionId)
    {

        return null;
    }

    /**
     * 查取当前节点的下一级节点
     *
     * @param regionId
     * @return
     */
    @RequestMapping(value = "/markets/getMarketOrderTree", method = RequestMethod.GET)
    @ResponseBody
    public String getMarketOrderTree(int regionId)
    {
//        marketGroupCache.fetchOne();
        return null;
    }
}
