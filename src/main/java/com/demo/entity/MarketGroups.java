package com.demo.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (MarketGroups)实体类
 *
 * @author makejava
 * @since 2020-05-28 12:00:09
 */
@ToString
@Table(name = "market_groups")
@Entity
public class MarketGroups implements Serializable {
    private static final long serialVersionUID = -46034883049783062L;

    private String description;

    @Id
    private int marketGroupId;

    private int parentGroupId;

    private String name;

    private String types;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMarketGroupId() {
        return marketGroupId;
    }

    public void setMarketGroupId(int marketGroupId) {
        this.marketGroupId = marketGroupId;
    }


    public int getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(int parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

}