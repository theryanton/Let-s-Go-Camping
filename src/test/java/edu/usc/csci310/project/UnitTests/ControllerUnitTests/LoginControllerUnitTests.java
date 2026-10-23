package edu.usc.csci310.project.UnitTests.ControllerUnitTests;

import edu.usc.csci310.project.controllers.LoginController;
import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(LoginController.class)
public class LoginControllerUnitTests {

    @Autowired
    private LoginController loginController;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        //Reset login attempts and time elapsed for each test
        LoginController.setLoginAttempts(0);
        LoginController.setFirstAttemptTime(0L);
        LoginController.setLastAttemptTime(0L);
    }

    @Test
    public void testGetLoginAttempts() {
        assertEquals(0, LoginController.getLoginAttempts());
    }

    @Test
    public void testNewFirstAttempt() {
        //should make loginAttempts = 0, then complete login attempt so loginAttempts is 1

        User user = new User("username","password");
        User foundUser = new User("username",BCrypt.hashpw("pasSword",BCrypt.gensalt()));

        Mockito.when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(foundUser));

        LoginController.setFirstAttemptTime(System.currentTimeMillis()-10000);
        LoginController.setLastAttemptTime(System.currentTimeMillis());

        loginController.login(user);

        assertEquals(1,LoginController.getLoginAttempts());

    }

    @Test
    public void testLoginAttemptsReset() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        Mockito.when(userRepository.findByUsername("username")).thenReturn(Optional.empty());

        LoginController.setFirstAttemptTime(0);
        LoginController.setLastAttemptTime(65000);
        loginController.login(user);
        assertEquals(1,LoginController.getLoginAttempts());
    }

    @Test
    public void testLoginAttemptsThirtySecReset() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        Mockito.when(userRepository.findByUsername("username")).thenReturn(Optional.empty());
        LoginController.setLoginAttempts(1);
        LoginController.setFirstAttemptTime(0);
        LoginController.setLastAttemptTime(35000);
        loginController.login(user);
        assertEquals(1,LoginController.getLoginAttempts());
    }

    @Test
    public void testAccountBlocked() {
        // Simulate three failed login attempts within 60 seconds
        User user = new User();
        user.setUsername("invalidUser");
        user.setPassword("incorrectPassword");

        LoginController.setLoginAttempts(3);
        LoginController.setFirstAttemptTime(System.currentTimeMillis()-25000);
        LoginController.setLastAttemptTime(System.currentTimeMillis()-20000);


        ResponseEntity<?> response = loginController.login(user);
        ResponseEntity<?> expected = ResponseEntity.status(401).body(Map.of("message","Account Blocked"));

        assertEquals(expected.getStatusCode(), response.getStatusCode());
        assertEquals(Map.of("message","Account Blocked","user_id",-1), response.getBody());
    }

    @Test
    public void testSuccessfulLogin() {
        User user = new User();
        user.setUsername("validUser");
        user.setPassword("C0rrectpassword");

        User foundUser = new User();
        foundUser.setUserId(0);
        foundUser.setUsername(User.sha256("validUser"));
        foundUser.setPassword(BCrypt.hashpw("C0rrectpassword", BCrypt.gensalt()));

        Mockito.when(userRepository.findByUsername(User.sha256("validUser"))).thenReturn(Optional.of(foundUser));

        ResponseEntity<?> response = loginController.login(user);
        ResponseEntity<?> expected = ResponseEntity.status(200).body(Map.of("message","Login Successful","user_id",0L));

        try {
            Map<String, Object> exp = (Map<String, Object>) (expected.getBody());
            Map<String, Object> res = (Map<String, Object>) (response.getBody());

            assertEquals(expected.getStatusCode(), response.getStatusCode());

            assertEquals(exp.get("user_id"), res.get("user_id"));
            assertEquals(exp.get("message"),res.get("message"));
        }
        catch(Exception e) {
            fail();
        }

    }

    @Test
    public void testIncorrectPassword() {
        User user = new User();
        user.setUsername("validUser");
        user.setPassword("incorrectPassword");

        User foundUser = new User();
        foundUser.setUsername("validUser");
        foundUser.setPassword(BCrypt.hashpw("correctPassword", BCrypt.gensalt()));

        Mockito.when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(foundUser));

        ResponseEntity<?> response = loginController.login(user);
        ResponseEntity<?> expected = ResponseEntity.status(400).body(Map.of("message","Incorrect password","user_id",-1));

        assertEquals(expected.getStatusCode(), response.getStatusCode());
        assertEquals(Map.of("message","Incorrect password","user_id",-1), response.getBody());
    }

    @Test
    public void testInvalidPasswordAfterTwoAttempts() {
        User user = new User();
        user.setUsername("validUser");
        user.setPassword("incorrectPassword");

        User foundUser = new User();
        foundUser.setUsername("validUser");
        foundUser.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));

        LoginController.setLoginAttempts(2);
        LoginController.setFirstAttemptTime(System.currentTimeMillis()-25000);
        LoginController.setLastAttemptTime(System.currentTimeMillis()-20000);

        Mockito.when(userRepository.findByUsername("validUser")).thenReturn(Optional.of(foundUser));

        ResponseEntity<?> response = loginController.login(user);
        ResponseEntity<?> expected = ResponseEntity.status(401).body(Map.of("message","Account Blocked"));

        assertEquals(expected.getStatusCode(), response.getStatusCode());
        assertEquals(Map.of("message","Account Blocked","user_id",-1), response.getBody());
    }

    @Test
    public void testInvalidUsername() {
        User user = new User();
        user.setUsername("invalidUser");
        user.setPassword("P4ssword");

        Mockito.when(userRepository.findByUsername("invalidUser")).thenReturn(Optional.empty());

        ResponseEntity<?> response = loginController.login(user);
        ResponseEntity<?> expected = ResponseEntity.status(400).body(Map.of("message","Invalid username","user_id",-1));

        assertEquals(expected.getStatusCode(), response.getStatusCode());
        assertEquals(expected.getBody(), response.getBody());
    }

    @Test
    public void testInvalidUsernameAfterTwoAttempts() {
        User user = new User();
        user.setUsername("invalidUser");
        user.setPassword("P4ssword");

        Mockito.when(userRepository.findByUsername("invalidUser")).thenReturn(Optional.empty());

        LoginController.setLoginAttempts(2);
        LoginController.setFirstAttemptTime(System.currentTimeMillis()-25000);
        LoginController.setLastAttemptTime(System.currentTimeMillis()-20000);

        ResponseEntity<?> response = loginController.login(user);
        ResponseEntity<?> expected = ResponseEntity.status(401).body(Map.of("message","Account Blocked","user_id",-1));

        assertEquals(expected.getStatusCode(), response.getStatusCode());
        assertEquals(Map.of("message","Account Blocked","user_id",-1), response.getBody());
    }

}