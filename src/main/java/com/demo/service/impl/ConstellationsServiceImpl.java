package com.demo.service.impl;

import com.demo.dao.ConstellationsDao;
import com.demo.entity.Constellations;
import com.demo.service.ConstellationsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Constellations)表服务实现类
 *
 * @author makejava
 * @since 2020-06-03 15:45:54
 */
@Service("constellationsService")
public class ConstellationsServiceImpl implements ConstellationsService {
    @Resource
    private ConstellationsDao dao;

    @Override
    public void save(Constellations constellations) {
        dao.save(constellations);
    }
}