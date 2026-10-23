package edu.usc.csci310.project.UnitTests.ControllerUnitTests;

import edu.usc.csci310.project.controllers.UserRegistrationController;
import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(UserRegistrationController.class)
public class RegistrationControllerUnitTests {

    @Autowired
    private UserRegistrationController registrationController;

    @MockBean
    private UserRepository userRepo;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setUsername("newUser");
        user.setPassword("password123");
    }

    @Test
    public void testRegisterUserSuccess() {
        Mockito.when(userRepo.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        Mockito.when(userRepo.save(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = registrationController.registerUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created", response.getBody());
    }

    @Test
    public void testRegisterUserAlreadyExists() {
        Mockito.when(userRepo.findByUsername(User.sha256(user.getUsername())))
                .thenReturn(Optional.of(new User(User.sha256(user.getUsername()),"pass")));

        ResponseEntity<?> response = registrationController.registerUser(user);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("That username has already been taken", response.getBody());
    }

}