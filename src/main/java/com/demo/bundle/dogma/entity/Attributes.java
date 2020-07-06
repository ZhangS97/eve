package com.demo.bundle.dogma.entity;

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
public class Attributes implements Serializable {
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

}