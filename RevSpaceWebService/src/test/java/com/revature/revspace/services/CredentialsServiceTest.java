package com.revature.revspace.services;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.User;
import com.revature.revspace.repositories.CredentialsRepo;
import com.revature.revspace.testutils.ModelGenerators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.revature.revspace.app.RevSpaceWebServiceApplication.class)
public class CredentialsServiceTest {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @Autowired
    CredentialsService credentialsService;

    @MockBean
    CredentialsRepo credentialsRepo;

    @Test
    void getRepoGetsRepo()
    {
        Assertions.assertNotNull(this.credentialsService.getRepo());
    }

    @Test
    void getIdForCredentialsGetsId()
    {
        int expectedId = RANDOM.nextInt(100) + 1; // random int in range [1,100]
        Credentials credentials = ModelGenerators.makeRandomCredentials();
        credentials.setCredentialsId(expectedId);
        int actualId = this.credentialsService.getIDFor(credentials);
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void getByEmail(){
        User user = new User("abc@email.com", "name","name", 8708779L, 234243234L, "git", "title", "location", "aboutme");
        Credentials credentials = new Credentials(user, "password");

        Mockito.when(credentialsRepo.findByUserEmail(user.getEmail())).thenReturn(credentials);
        Credentials actual = credentialsService.getByEmail(user.getEmail());
        assertEquals("abc@email.com", actual.getUser().getEmail());
    }

    @Test
    void getIdByUserId()
    {
        Credentials creds = ModelGenerators.makeRandomCredentials();
        int id = creds.getUser().getUserId();
        Mockito.when(credentialsRepo.findByUserUserId(id))
                .thenReturn(creds);
        assertEquals(creds.getUser().getUserId(), credentialsService.getIdByUserId(id));
    }
}
