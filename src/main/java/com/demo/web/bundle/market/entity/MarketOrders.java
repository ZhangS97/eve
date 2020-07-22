package com.demo.web.bundle.market.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (MarketOrders)实体类
 *
 * @author makejava
 * @since 2020-06-04 20:26:22
 */
@Data
@Table(name = "market_orders")
@Entity
public class MarketOrders implements Serializable
{
    private static final long serialVersionUID = 543688007212649352L;

    private String duration;

    private String isBuyOrder;

    private String issued;

    private String locationId;

    private String minVolume;

    @Id
    private String orderId;

    private String price;

    @Column(name = "order_range")
    private String range;

    private String systemId;

    private String regionId;

    private String typeId;

    private String volumeRemain;

    private String volumeTotal;

}