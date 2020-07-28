package com.demo.web.bundle.universe.model.dao;

import com.demo.web.bundle.universe.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (Region)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-03 14:17:53
 */
public interface RegionDao extends JpaRepository<Region, String>
{
    @Query(value = "select r.regionId from Region r ")
    Page findAllHighSecureRegion(Pageable pageable);

    List<Region> findAll();
}