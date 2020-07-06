package com.demo.bundle.universe.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Types)实体类
 *
 * @author makejava
 * @since 2020-06-04 10:59:01
 */
@ToString
@Table(name = "types")
@Entity
public class Types implements Serializable
{
    private static final long serialVersionUID = 596390807839767368L;

    private String capacity;

    private String description;

    private String dogmaAttributes;

    private Integer groupId;

    private Integer iconId;

    private Integer marketGroupId;

    private String mass;

    private String name;

    private String packagedVolume;

    private String portionSize;

    private String published;

    private String radius;

    @Id
    private Integer typeId;

    private String volume;

    private String dogmaEffects;

    private Integer graphicId;

    public String getCapacity()
    {
        return capacity;
    }

    public void setCapacity(String capacity)
    {
        this.capacity = capacity;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDogmaAttributes()
    {
        return dogmaAttributes;
    }

    public void setDogmaAttributes(String dogmaAttributes)
    {
        this.dogmaAttributes = dogmaAttributes;
    }

    public Integer getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Integer groupId)
    {
        this.groupId = groupId;
    }

    public Integer getIconId()
    {
        return iconId;
    }

    public void setIconId(Integer iconId)
    {
        this.iconId = iconId;
    }

    public Integer getMarketGroupId()
    {
        return marketGroupId;
    }

    public void setMarketGroupId(Integer marketGroupId)
    {
        this.marketGroupId = marketGroupId;
    }

    public String getMass()
    {
        return mass;
    }

    public void setMass(String mass)
    {
        this.mass = mass;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPackagedVolume()
    {
        return packagedVolume;
    }

    public void setPackagedVolume(String packagedVolume)
    {
        this.packagedVolume = packagedVolume;
    }

    public String getPortionSize()
    {
        return portionSize;
    }

    public void setPortionSize(String portionSize)
    {
        this.portionSize = portionSize;
    }

    public String getPublished()
    {
        return published;
    }

    public void setPublished(String published)
    {
        this.published = published;
    }

    public String getRadius()
    {
        return radius;
    }

    public void setRadius(String radius)
    {
        this.radius = radius;
    }

    public Integer getTypeId()
    {
        return typeId;
    }

    public void setTypeId(Integer typeId)
    {
        this.typeId = typeId;
    }

    public String getVolume()
    {
        return volume;
    }

    public void setVolume(String volume)
    {
        this.volume = volume;
    }

    public String getDogmaEffects()
    {
        return dogmaEffects;
    }

    public void setDogmaEffects(String dogmaEffects)
    {
        this.dogmaEffects = dogmaEffects;
    }

    public Integer getGraphicId()
    {
        return graphicId;
    }

    public void setGraphicId(Integer graphicId)
    {
        this.graphicId = graphicId;
    }

}