package dyliang;

import dyliang.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DyliangApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		userService.hello();
	}

}
