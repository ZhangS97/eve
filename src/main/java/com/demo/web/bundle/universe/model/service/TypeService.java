package com.demo.web.bundle.universe.model.service;

import com.demo.web.bundle.universe.entity.Type;

import java.util.List;

/**
 * (Types)表服务接口
 *
 * @author makejava
 * @since 2020-06-04 09:55:44
 */
public interface TypeService
{
    void save(Type type);

    Type findByTypeId(String id);

    List<Type> findAllMarketTypes();

}