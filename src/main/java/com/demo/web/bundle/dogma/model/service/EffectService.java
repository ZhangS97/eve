package com.demo.web.bundle.dogma.model.service;

import com.demo.web.bundle.dogma.entity.Effect;

import java.util.List;

public interface EffectService {
    void save(Effect effect);

    List<Effect> findAll();
}
