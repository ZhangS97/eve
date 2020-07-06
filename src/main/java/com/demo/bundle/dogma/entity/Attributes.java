package com.demo.bundle.dogma.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Attributes)实体类
 *
 * @author makejava
 * @since 2020-06-04 16:55:26
 */
@ToString
@Table(name = "attributes")
@Entity
public class Attributes implements Serializable
{
    private static final long serialVersionUID = -41893402052442541L;

    @Id
    private Integer attributeId;

    private String defaultValue;

    private String description;

    private String displayName;

    private String highIsGood;

    private String name;

    private String stackable;

    private Integer iconId;

    private String published;

    private Integer unitId;

    public Integer getAttributeId()
    {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId)
    {
        this.attributeId = attributeId;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getHighIsGood()
    {
        return highIsGood;
    }

    public void setHighIsGood(String highIsGood)
    {
        this.highIsGood = highIsGood;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStackable()
    {
        return stackable;
    }

    public void setStackable(String stackable)
    {
        this.stackable = stackable;
    }

    public Integer getIconId()
    {
        return iconId;
    }

    public void setIconId(Integer iconId)
    {
        this.iconId = iconId;
    }

    public String getPublished()
    {
        return published;
    }

    public void setPublished(String published)
    {
        this.published = published;
    }

    public Integer getUnitId()
    {
        return unitId;
    }

    public void setUnitId(Integer unitId)
    {
        this.unitId = unitId;
    }

}