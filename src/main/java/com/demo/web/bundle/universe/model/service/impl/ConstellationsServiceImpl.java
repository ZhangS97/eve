package com.demo.web.bundle.universe.model.service.impl;

import com.demo.web.bundle.universe.entity.Constellations;
import com.demo.web.bundle.universe.model.dao.ConstellationsDao;
import com.demo.web.bundle.universe.model.service.ConstellationsService;
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