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
//            RegionsService service;
//            MarketService service;
//            MarketGroupsService service;

    public static void main(String[] args)
    {
        SpringApplication.run(eveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
//        System.out.println(service.getOrders(10000002, service.getTypeDetails(516)));
//        service.getTypeIdsBy
//        GIdAndRId(516);
//        service.updateTypes();
        MarketGroupCache marketGroupCache = new MarketGroupCache();
        marketGroupCache.doLoad();
    }

}
