package com.demo.entity;

import lombok.ToString;

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
@ToString
@Table(name = "systems")
@Entity
public class Systems implements Serializable {
    private static final long serialVersionUID = 269874505323992643L;

    private Integer constellationId;

    private String name;

    private String planets;

    private String position;

    private String securityClass;

    private String securityStatus;

    private Integer starId;

    private String stargates;

    private String stations;
    @Id
    private Integer systemId;


    public Integer getConstellationId() {
        return constellationId;
    }

    public void setConstellationId(Integer constellationId) {
        this.constellationId = constellationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanets() {
        return planets;
    }

    public void setPlanets(String planets) {
        this.planets = planets;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSecurityClass() {
        return securityClass;
    }

    public void setSecurityClass(String securityClass) {
        this.securityClass = securityClass;
    }

    public String getSecurityStatus() {
        return securityStatus;
    }

    public void setSecurityStatus(String securityStatus) {
        this.securityStatus = securityStatus;
    }

    public Integer getStarId() {
        return starId;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    public String getStargates() {
        return stargates;
    }

    public void setStargates(String stargates) {
        this.stargates = stargates;
    }

    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

}