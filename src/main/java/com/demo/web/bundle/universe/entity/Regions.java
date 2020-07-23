package com.demo.web.bundle.universe.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Regions)实体类
 *
 * @author makejava
 * @since 2020-06-03 14:17:53
 */
@Data
@Table(name = "regions")
@Entity
public class Regions implements Serializable
{
    private static final long serialVersionUID = -51929224482986887L;

    private String constellations;

    private String description;

    private String name;

    @Id
    private String regionId;

}