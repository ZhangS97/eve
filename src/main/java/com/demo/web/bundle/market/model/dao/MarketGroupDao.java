package com.demo.web.bundle.market.model.dao;

import com.demo.web.bundle.market.entity.MarketGroup;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (MarketGroup)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-28 12:00:09
 */
public interface MarketGroupDao extends JpaRepository<MarketGroup, String> {
    @Query(value = "select mg.types from MarketGroup mg where mg.marketGroupId = :groupId")
    String getTypeIdsByGIdAndRId(@Param("groupId") String groupId);

    //    @Query(value = "SELECT mg FROM MarketGroup mg ORDER BY mg.parentGroupId")
//    List<MarketGroup> findAllOrderByParentGroupIdAsc();
    List<MarketGroup> findAll();
}