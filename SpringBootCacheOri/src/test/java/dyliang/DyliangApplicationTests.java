package dyliang;

import dyliang.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
class DyliangApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    CacheManager cacheManager;

    @Test
    void contextLoads() {
//        System.out.println(dataSource);
        Cache account = cacheManager.getCache("account");
    }
}
