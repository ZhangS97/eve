package main.java.com.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @ Author ：zhang. @ Date ：Created in 20:57 2020/1/10 @ Description： @ Modified By：
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport
{

//    /**
//     * 自定义生成key的规则
//     *
//     * @return
//     */
//    @Override
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object o, Method method, Object... objects) {
//                // 格式化缓存key字符串
//                StringBuilder sb = new StringBuilder();
//                // 追加类名
//                sb.append(o.getClass().getName());
//                // 追加方法名
//                sb.append(method.getName());
//                // 遍历参数并且追加
//                for (Object obj : objects) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }

    /**
     * 采用RedisCacheManager作为缓存管理器
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(
            RedisConnectionFactory redisConnectionFactory)
    {
        // 设置缓存时间
        // RedisCacheManager.setDefaultEx..(60)  60s
        return RedisCacheManager.create(redisConnectionFactory);
    }
}
