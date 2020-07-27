package com.demo.web.bundle.market.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (MarketGroup)实体类
 *
 * @author makejava
 * @since 2020-05-28 12:00:09
 */
@Data
@Table(name = "market_groups")
@Entity
public class MarketGroup implements Serializable {
    private static final long serialVersionUID = -46034883049783062L;

    private String description;

    @Id
    private String marketGroupId;

    private String parentGroupId;

    private String name;

    private String types;

}