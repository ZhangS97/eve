package com.demo.web.bundle.dogma.model.service.impl;

import com.demo.web.bundle.dogma.entity.Attribute;
import com.demo.web.bundle.dogma.model.dao.AttributeDao;
import com.demo.web.bundle.dogma.model.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Attribute)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 16:55:26
 */
@Service("attributesService")
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    AttributeDao dao;

    @Override
    public void save(Attribute attribute) {
        dao.save(attribute);
    }
}