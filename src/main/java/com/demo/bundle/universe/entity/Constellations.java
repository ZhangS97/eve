package com.demo.bundle.universe.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Constellations)实体类
 * 星座
 *
 * @author makejava
 * @since 2020-06-03 15:50:14
 */
@ToString
@Table(name = "constellations")
@Entity
public class Constellations implements Serializable
{
    private static final long serialVersionUID = 181417256459091281L;

    @Id
    private Integer constellationId;

    private String name;

    private String position;

    private Integer regionId;

    private String systems;

    public Integer getConstellationId()
    {
        return constellationId;
    }

    public void setConstellationId(Integer constellationId)
    {
        this.constellationId = constellationId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public Integer getRegionId()
    {
        return regionId;
    }

    public void setRegionId(Integer regionId)
    {
        this.regionId = regionId;
    }

    public String getSystems()
    {
        return systems;
    }

    public void setSystems(String systems)
    {
        this.systems = systems;
    }

}