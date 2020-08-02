package com.demo;

import com.demo.service.UniverseService;
import com.demo.web.bundle.market.cache.MarketGroupCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing
@SpringBootApplication
@EnableAsync
public class eveApplication implements CommandLineRunner
{
    @Autowired
//    DogmaService service;
            UniverseService service;
//            RegionService service;
//            MarketService service;
//            MarketGroupService service;

    public static void main(String[] args)
    {
        SpringApplication.run(eveApplication.class, args);
    }

    @Autowired
    MarketGroupCache marketGroupCache;

    @Override
    public void run(String... args) throws Exception
    {
//        MarketGroupCache marketGroupCache = new MarketGroupCache();
//        marketGroupCache.doLoad();
        service.updateRegions();
        service.updateConstellations();
        service.updateSystems();
    }

}
