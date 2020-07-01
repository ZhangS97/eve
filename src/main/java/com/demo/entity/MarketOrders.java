package com.demo.entity;

import lombok.ToString;

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
@ToString
@Table(name = "market_orders")
@Entity
public class MarketOrders implements Serializable {
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

    private Integer systemId;

    private Integer regionId;

    private Integer typeId;

    private String volumeRemain;

    private String volumeTotal;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIsBuyOrder() {
        return isBuyOrder;
    }

    public void setIsBuyOrder(String isBuyOrder) {
        this.isBuyOrder = isBuyOrder;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(String minVolume) {
        this.minVolume = minVolume;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getVolumeRemain() {
        return volumeRemain;
    }

    public void setVolumeRemain(String volumeRemain) {
        this.volumeRemain = volumeRemain;
    }

    public String getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(String volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}