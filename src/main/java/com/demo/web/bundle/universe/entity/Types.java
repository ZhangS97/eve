package com.demo.web.bundle.universe.entity;

import lombok.Data;

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
@Data
@Table(name = "types")
@Entity
public class Types implements Serializable {
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

}