package dyliang;

import dyliang.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
class DyliangApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

    @Autowired
    RedisTemplate redisTemplate;  //k-v都是对象的


    @Test
    void contextLoads() {
//        System.out.println(dataSource);
        Cache account = cacheManager.getCache("account");
    }

    @Test
    public void testRedis(){
        //给redis中保存数据
        stringRedisTemplate.opsForValue().append("msg","hello");
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);

		stringRedisTemplate.opsForList().leftPush("mylist","1");
		stringRedisTemplate.opsForList().leftPush("mylist","2");
    }
}
