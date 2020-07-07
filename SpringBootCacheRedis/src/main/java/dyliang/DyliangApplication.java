package dyliang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("dyliang.mapper")
@EnableCaching
public class DyliangApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyliangApplication.class, args);
    }

}
