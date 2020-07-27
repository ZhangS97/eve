package com.demo.web.bundle.universe.model.service.impl;

import com.demo.web.bundle.universe.entity.Constellation;
import com.demo.web.bundle.universe.model.dao.ConstellationDao;
import com.demo.web.bundle.universe.model.service.ConstellationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Constellation)表服务实现类
 *
 * @author makejava
 * @since 2020-06-03 15:45:54
 */
@Service("constellationsService")
public class ConstellationServiceImpl implements ConstellationService {
    @Resource
    private ConstellationDao dao;

    @Override
    public void save(Constellation constellation) {
        dao.save(constellation);
    }
}