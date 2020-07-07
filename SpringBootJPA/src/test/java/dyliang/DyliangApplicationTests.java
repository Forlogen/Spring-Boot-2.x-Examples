package dyliang;

import dyliang.domain.Account;
import dyliang.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class DyliangApplicationTests {


    @Autowired
    private AccountService accountService;

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {

        System.out.println(dataSource);
        List<Account> all = accountService.findAll();
        for (Account account : all) {
            System.out.println(account);
        }

    }

}
