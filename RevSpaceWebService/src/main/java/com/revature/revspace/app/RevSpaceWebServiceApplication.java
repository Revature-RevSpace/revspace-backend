package com.revature.revspace.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.revature")
@ComponentScan("com.revature")
@EntityScan("com.revature.models")
@EnableJpaRepositories("com.revature.repositories")
public class RevSpaceWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevSpaceWebServiceApplication.class, args);
	}

}
