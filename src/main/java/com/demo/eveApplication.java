package com.demo;

import com.demo.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableJpaAuditing
@SpringBootApplication
@EnableAsync
public class eveApplication implements CommandLineRunner {
    @Autowired
//    DogmaService service;
            UniverseService service;
//            RegionService service;
//            MarketService service;
//            MarketGroupService service;

    public static void main(String[] args) {
        SpringApplication.run(eveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map map = new HashMap();
        List list1 = Arrays.asList("a", "b", "C");
        List list2 = Arrays.asList("a", "b", "C");
        map.put("a", list1);
        map.put("b", list2);

        List<String> str = Arrays.asList(
                "[41024,33539,41025,40456,17893,12302,41027,41028,41029,30497,41,53291,16686,53299,3643,3773,41023,2368,2369,41026,2371,11588,2373,2374,2375,2376,2377,40549,40548,17894,3685,9826,15331,3812,3771,3814,3687,17897,17895,53476,15353,40701]"
                        .split(","));
        String str1 = map.toString();
//        System.out.println(service.getOrders(10000002, service.getTypeDetails(516)));
//        service.getTypeIdsBy
//        GIdAndRId(516);
//        service.updateTypes();
//        MarketGroupCache marketGroupCache = new MarketGroupCache();
//        marketGroupCache.doLoad();
    }

}
