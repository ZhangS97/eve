package com.demo.service.impl;

import com.demo.dao.TypesDao;
import com.demo.entity.Types;
import com.demo.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Types)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 09:55:44
 */
@Service("typesService")
public class TypesServiceImpl implements TypesService {
    @Autowired
    TypesDao dao;

    @Override
    public void save(Types types) {
        dao.save(types);
    }

    @Override
    public Types findByTypeId(int id) {
        return dao.findById(id).get();
    }
}