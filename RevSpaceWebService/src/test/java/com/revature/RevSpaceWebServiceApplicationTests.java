package com.revature;

import com.revature.revspace.app.RevSpaceWebServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes=RevSpaceWebServiceApplication.class)
class RevSpaceWebServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
