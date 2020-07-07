package dyliang.config;

import dyliang.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/*
    https://github.com/LCABC777/Springboot-redis
 */
@Configuration
public class MyRedisConfig {

    @Resource
    //lettuce客户端连接工厂
    private LettuceConnectionFactory lettuceConnectionFactory;
    // 日志
    private Logger logger= (Logger) LoggerFactory.getLogger(MyRedisConfig.class);
    // json序列化器
    private Jackson2JsonRedisSerializer<Account> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Account.class);
    //过期时间1天
    private Duration timeToLive = Duration.ofDays(1);

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // Redis缓存配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(this.timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();

        //缓存配置map
        Map<String,RedisCacheConfiguration> cacheConfigurationMap=new HashMap<>();

        //自定义缓存名，后面使用的@Cacheable的CacheName
        cacheConfigurationMap.put("account",config);
        cacheConfigurationMap.put("default",config);

        //根据redis缓存配置和reid连接工厂生成redis缓存管理器
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .withInitialCacheConfigurations(cacheConfigurationMap)
                .build();
        logger.debug("自定义RedisCacheManager加载完成");
        return redisCacheManager;
    }

    //redisTemplate模板提供给其他类对redis数据库进行操作
    @Bean(name = "redisTemplate")
    public RedisTemplate<String,Account> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Account> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        logger.debug("自定义RedisTemplate加载完成");
        return redisTemplate;
    }

    //redis键序列化使用StrngRedisSerializer
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    //redis值序列化使用json序列化器
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
