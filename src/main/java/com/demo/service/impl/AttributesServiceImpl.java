package com.demo.service.impl;

import com.demo.dao.AttributesDao;
import com.demo.entity.Attributes;
import com.demo.service.AttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Attributes)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 16:55:26
 */
@Service("attributesService")
public class AttributesServiceImpl implements AttributesService {
    @Autowired
    AttributesDao dao;

    @Override
    public void save(Attributes attributes) {
        dao.save(attributes);
    }
}