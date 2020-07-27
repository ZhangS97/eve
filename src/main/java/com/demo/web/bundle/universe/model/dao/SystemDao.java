package com.demo.web.bundle.universe.model.dao;

import com.demo.web.bundle.universe.entity.System;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (System)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-03 16:10:08
 */
public interface SystemDao extends JpaRepository<System, String> {
}