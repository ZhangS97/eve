package com.demo.controllers;

import com.demo.service.MarketService;
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

    @RequestMapping(value = "/markets/orders", method = RequestMethod.GET)
    @ResponseBody
    public String getOrders(int regionId)
    {

        return null;
    }
}
