package com.demo.web.bundle.dogma.model.dao;

import com.demo.web.bundle.dogma.entity.Effect;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (Attribute)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 16:55:26
 */
public interface EffectDao extends JpaRepository<Effect, String> {
}