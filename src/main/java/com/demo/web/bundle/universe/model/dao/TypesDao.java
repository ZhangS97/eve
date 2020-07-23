package com.demo.web.bundle.universe.model.dao;

import com.demo.web.bundle.universe.entity.Types;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Types)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 09:55:44
 */
public interface TypesDao extends JpaRepository<Types, String>
{

}