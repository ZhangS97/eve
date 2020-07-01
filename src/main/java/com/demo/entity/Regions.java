package com.demo.entity;

import lombok.ToString;

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
@ToString
@Table(name = "regions")
@Entity
public class Regions implements Serializable {
    private static final long serialVersionUID = -51929224482986887L;

    private String constellations;

    private String description;

    private String name;
    @Id
    private int regionId;


    public String getConstellations() {
        return constellations;
    }

    public void setConstellations(String constellations) {
        this.constellations = constellations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

}