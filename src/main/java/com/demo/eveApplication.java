package com.demo;

import com.demo.service.UniverseService;
import com.demo.web.bundle.market.cache.MarketGroupCache;
import com.demo.web.bundle.universe.cache.ConstellationCache;
import com.demo.web.bundle.universe.cache.RegionCache;
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

//    DogmaService service;
//            RegionService service;
//            MarketService service;
//            MarketGroupService service;

    public static void main(String[] args)
    {
        SpringApplication.run(eveApplication.class, args);
    }

    @Autowired
    MarketGroupCache marketGroupCache;

    @Autowired
    RegionCache regionCache;

    @Autowired
    ConstellationCache constellationCache;

    @Autowired
    UniverseService universeService;

    @Override
    public void run(String... args) throws Exception
    {
//        marketGroupCache.doLoad();
        universeService.updateSystems();
//        service.updateUniverseInfo();
    }

}
