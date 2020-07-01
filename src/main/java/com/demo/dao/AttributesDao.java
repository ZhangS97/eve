package com.demo.dao;

import com.demo.entity.Attributes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Attributes)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 16:55:26
 */
public interface AttributesDao extends JpaRepository<Attributes, Integer> {
}