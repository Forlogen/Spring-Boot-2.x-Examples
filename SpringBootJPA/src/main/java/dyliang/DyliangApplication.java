package dyliang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EntityScan("dyliang")
public class DyliangApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyliangApplication.class, args);
    }

}
