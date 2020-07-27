package com.demo.web.bundle.market.model.dao;

import com.demo.web.bundle.market.entity.MarketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * (MarketOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 20:24:18
 */
public interface MarketOrderDao extends JpaRepository<MarketOrder, String> {
    List<MarketOrder> findByTypeIdAndRegionId(String typeId, String regionId);
}