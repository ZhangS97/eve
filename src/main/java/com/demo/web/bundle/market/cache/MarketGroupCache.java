package com.demo.web.bundle.market.cache;

import com.demo.web.bundle.market.model.service.MarketGroupsService;
import org.springframework.beans.factory.annotation.Autowired;

public class MarketGroupCache {

    public static final String NAME = "marketGroup:class";
    public static final String KEY = "";
    public static final String KEY_ID = "marketGroup:class:id";
    public static final String KEY_CHILDREN_ID = "";
    public static final String KEY_PARENT_ID = "marketGroup:class:parent_id";

    @Autowired
    MarketGroupsService marketGroupsService;

    private boolean loaded = false;

    public void doLoad() {

    }
}
