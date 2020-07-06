package com.demo.bundle.dogma.model.service.impl;

import com.demo.bundle.dogma.entity.Attributes;
import com.demo.bundle.dogma.model.dao.AttributesDao;
import com.demo.bundle.dogma.model.service.AttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Attributes)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 16:55:26
 */
@Service("attributesService")
public class AttributesServiceImpl implements AttributesService
{
    @Autowired
    AttributesDao dao;

    @Override
    public void save(Attributes attributes)
    {
        dao.save(attributes);
    }
}