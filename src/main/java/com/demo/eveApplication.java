package com.demo;

import com.demo.web.bundle.market.cache.MarketGroupCache;
import com.demo.web.bundle.market.model.service.MarketGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
@EnableAsync
public class eveApplication implements CommandLineRunner {
    //    @Autowired
//    DogmaService service;
//    @Autowired
//    UniverseService service;

    //            RegionsService service;
//            MarketService service;
    @Autowired
    MarketGroupsService service;
    @Autowired
    MarketGroupCache marketGroupCache;

    public static void main(String[] args) {
        SpringApplication.run(eveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(service.getOrders(10000002, service.getTypeDetails(516)));
//        service.getTypeIdsBy
//        GIdAndRId(516);
//        service.updateTypes();

        marketGroupCache.doLoad();
    }

}
