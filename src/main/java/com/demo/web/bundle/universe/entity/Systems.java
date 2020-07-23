package com.demo.web.bundle.universe.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Systems)实体类
 *
 * @author makejava
 * @since 2020-06-03 16:10:08
 */
@Data
@Table(name = "systems")
@Entity
public class Systems implements Serializable
{
    private static final long serialVersionUID = 269874505323992643L;

    private String constellationId;

    private String name;

    private String planets;

    private String position;

    private String securityClass;

    private String securityStatus;

    private String starId;

    private String stargates;

    private String stations;

    @Id
    private String systemId;

}