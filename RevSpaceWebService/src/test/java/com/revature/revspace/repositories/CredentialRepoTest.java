package com.revature.revspace.repositories;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.revature.revspace.app.RevSpaceWebServiceApplication.class)
@Transactional
public class CredentialRepoTest {

    @Autowired
    CredentialsRepo credentialsRepo;

    @Autowired
    UserRepo userRepo;

    @Test
    @Rollback
    void findByEmail(){
        User user = new User("abc@email.com", "name", "name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        user = userRepo.save(user);

        Credentials credentials = new Credentials(user, "password");
        credentials = credentialsRepo.save(credentials);

        Credentials test = credentialsRepo.findByUserEmail("abc@email.com");
        System.out.println(test);
        assertEquals("abc@email.com", test.getUser().getEmail());
        assertNotNull(test);
    }
}