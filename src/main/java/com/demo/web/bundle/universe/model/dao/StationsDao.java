package com.demo.web.bundle.universe.model.dao;

import com.demo.web.bundle.universe.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Stations)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-28 16:56:51
 */
public interface StationsDao extends JpaRepository<Station, String>
{
}