package com.demo.web.bundle.universe.model.dao;

import com.demo.web.bundle.universe.entity.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Constellation)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-03 15:45:54
 */
public interface ConstellationDao
        extends JpaRepository<Constellation, String> {

}