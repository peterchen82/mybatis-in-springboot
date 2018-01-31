package com.github.peterchen82.mybatis.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;

@Configuration
@EnableCaching
@Profile({"dev","prod"})
public class RedisCacheConfig {
    /**
     * 重新配置RedisCacheManager
     * @param rd
     */
    @Autowired
    public void configRedisCacheManger(RedisCacheManager rd){
        rd.setDefaultExpiration(100L);
    }
}
