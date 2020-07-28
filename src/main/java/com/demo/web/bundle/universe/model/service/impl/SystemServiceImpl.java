package com.demo.web.bundle.universe.model.service.impl;

import com.demo.web.bundle.universe.entity.System;
import com.demo.web.bundle.universe.model.dao.SystemDao;
import com.demo.web.bundle.universe.model.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * (System)表服务实现类
 *
 * @author makejava
 * @since 2020-06-03 16:10:08
 */
@Service("systemsService")
public class SystemServiceImpl implements SystemService
{
    @Autowired
    SystemDao dao;

    @Override
    public void save(System system)
    {
        dao.save(system);
    }

    @Override
    public List<System> findAll()
    {
        return dao.findAll();
    }

    @Override
    public List<String> findAllStationIds()
    {
        List<String> res = new ArrayList<>();
        for (String str : dao.findAllStationIds())
        {
            res.addAll(Arrays.asList(str.split(",")));
        }
        //res一位存一个id
        return res;
    }
}