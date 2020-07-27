//package com.demo.config;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//
///**
// * @ Author ：zhang. @ Date ：Created in 20:57 2020/1/10 @ Description： @ Modified By：
// */
//@Configuration
//@EnableCaching
//public class RedisConfiguration extends CachingConfigurerSupport {
//
//    /**
//     * 采用RedisCacheManager作为缓存管理器
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(
//            RedisConnectionFactory redisConnectionFactory) {
//        return RedisCacheManager.create(redisConnectionFactory);
//    }
//}
