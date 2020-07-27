package com.demo.web.bundle.dogma.model.dao;

import com.demo.web.bundle.dogma.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Attribute)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 16:55:26
 */
public interface AttributeDao extends JpaRepository<Attribute, String> {
}