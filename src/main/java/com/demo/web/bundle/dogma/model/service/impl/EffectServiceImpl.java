package com.demo.web.bundle.dogma.model.service.impl;

import com.demo.web.bundle.dogma.entity.Effect;
import com.demo.web.bundle.dogma.model.dao.EffectDao;
import com.demo.web.bundle.dogma.model.service.EffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EffectServiceImpl implements EffectService {
    @Autowired
    EffectDao dao;

    @Override
    public void save(Effect effect) {
        dao.save(effect);
    }

    @Override
    public List<Effect> findAll() {
        return dao.findAll();
    }
}
