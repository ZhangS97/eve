package com.demo;

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
//    @Autowired
//    DogmaService service;
//    UniverseService service;
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

    @Override
    public void run(String... args) throws Exception
    {
//        marketGroupCache.doLoad();
        long t = System.currentTimeMillis();
        regionCache.doLoad();
        constellationCache.doLoad();
        System.out.println(System.currentTimeMillis() - t);
//        service.updateUniverseInfo();
    }

}
