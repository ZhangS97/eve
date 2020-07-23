package com.demo.web.bundle.dogma.entity;

import lombok.Data;

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
@Data
@Table(name = "attributes")
@Entity
public class Attributes implements Serializable
{
    private static final long serialVersionUID = -41893402052442541L;

    @Id
    private String attributeId;

    private String defaultValue;

    private String description;

    private String displayName;

    private String highIsGood;

    private String name;

    private String stackable;

    private String iconId;

    private String published;

    private String unitId;

}