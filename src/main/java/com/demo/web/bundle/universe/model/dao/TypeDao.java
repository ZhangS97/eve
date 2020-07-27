package com.demo.web.bundle.universe.model.dao;

import com.demo.web.bundle.universe.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (Types)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 09:55:44
 */
public interface TypeDao extends JpaRepository<Type, String>
{

    @Query("SELECT t FROM Type t WHERE t.marketGroupId IS NOT NULL")
    List<Type> findAllMarketTypes();
}