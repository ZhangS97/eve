package com.demo.dao;


import com.demo.entity.Regions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * (Regions)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-03 14:17:53
 */
public interface RegionsDao extends JpaRepository<Regions, Integer> {
    @Query(value = "select r.regionId from Regions r ")
    Page findAllHighSecureRegion(Pageable pageable);
}