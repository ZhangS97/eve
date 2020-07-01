package com.demo.dao;

import com.demo.entity.MarketOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * (MarketOrders)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 20:24:18
 */
public interface MarketOrdersDao extends JpaRepository<MarketOrders, String> {
    List<MarketOrders> findByTypeIdAndRegionId(int typeId, int regionId);
}