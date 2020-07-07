package com.demo.web.bundle.universe.entity;

import lombok.Data;

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
@Data
@Table(name = "constellations")
@Entity
public class Constellations implements Serializable {
    private static final long serialVersionUID = 181417256459091281L;

    @Id
    private Integer constellationId;

    private String name;

    private String position;

    private Integer regionId;

    private String systems;

}