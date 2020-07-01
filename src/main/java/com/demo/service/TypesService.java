package com.demo.service;

import com.demo.entity.Types;

/**
 * (Types)表服务接口
 *
 * @author makejava
 * @since 2020-06-04 09:55:44
 */
public interface TypesService {
    void save(Types types);

    Types findByTypeId(int id);
}