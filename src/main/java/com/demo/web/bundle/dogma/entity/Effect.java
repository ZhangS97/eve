package com.demo.web.bundle.dogma.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Effect)实体类
 *
 * @author makejava
 * @since 2020-07-28 23:10:17
 */
@Data
@Table(name = "effects")
@Entity
public class Effect implements Serializable {
    private static final long serialVersionUID = -34169494067175869L;

    @Id
    private String effectId;

    private String description;

    private String dischargeAttributeId;

    private String displayName;

    private String durationAttributeId;

    private String effectCategory;

    private String isWarpSafe;

    private String name;

    private String disallowAutoRepeat;

    private String electronicChance;

    private String falloffAttributeId;

    private String iconId;

    private String isAssistance;

    private String isOffensive;

    private String modifiers;

    private String postExpression;

    private String preExpression;

    private String published;

    private String rangeAttributeId;

    private String rangeChance;

    private String trackingSpeedAttributeId;
}

