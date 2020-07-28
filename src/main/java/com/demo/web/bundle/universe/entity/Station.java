package com.demo.web.bundle.universe.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Stations)实体类
 *
 * @author makejava
 * @since 2020-07-28 16:57:28
 */
@Data
@Table(name = "stations")
@Entity
public class Station implements Serializable
{
    private static final long serialVersionUID = -23512892887867888L;

    private String maxDockableShipVolume;

    private String name;

    private String officeRentalCost;

    private String owner;

    private String position;

    private String raceId;

    private String reprocessingEfficiency;

    private String reprocessingStationsTake;

    private String services;

    @Id
    private String stationId;

    private String systemId;

    private String typeId;

}