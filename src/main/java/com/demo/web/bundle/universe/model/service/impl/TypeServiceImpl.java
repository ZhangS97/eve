package com.demo.web.bundle.universe.model.service.impl;

import com.demo.web.bundle.universe.entity.Type;
import com.demo.web.bundle.universe.model.dao.TypeDao;
import com.demo.web.bundle.universe.model.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Types)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 09:55:44
 */
@Service("typesService")
public class TypeServiceImpl implements TypeService
{
    @Autowired
    TypeDao dao;

    @Override
    public void save(Type type)
    {
        dao.save(type);
    }

    @Override
    public Type findByTypeId(String id)
    {
        return dao.findById(id).get();
    }

    @Override
    public List<Type> findAllMarketTypes()
    {

        return dao.findAllMarketTypes();
    }
}