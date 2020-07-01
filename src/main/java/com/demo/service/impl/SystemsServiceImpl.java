package com.demo.service.impl;

import com.demo.dao.SystemsDao;
import com.demo.entity.Systems;
import com.demo.service.SystemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Systems)表服务实现类
 *
 * @author makejava
 * @since 2020-06-03 16:10:08
 */
@Service("systemsService")
public class SystemsServiceImpl implements SystemsService {
    @Autowired
    SystemsDao dao;

    @Override
    public void save(Systems systems) {
        dao.save(systems);
    }
}