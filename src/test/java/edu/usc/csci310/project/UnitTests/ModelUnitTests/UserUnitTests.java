package edu.usc.csci310.project.UnitTests.ModelUnitTests;

import edu.usc.csci310.project.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserUnitTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(user);
        assertEquals(0, user.getUser_id());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    public void testParameterizedConstructor() {
        User paramUser = new User("testUser", "testPassword");
        assertEquals("testUser", paramUser.getUsername());
        assertEquals("testPassword", paramUser.getPassword());
    }

    @Test
    public void testFullConstructor() {
        User fullUser = new User(1, "fullUser", "fullPass");
        assertEquals(1, fullUser.getUser_id());
        assertEquals("fullUser", fullUser.getUsername());
        assertEquals("fullPass", fullUser.getPassword());
    }

    @Test
    public void testSetUserId() {
        user.setUserId(1);
        assertEquals(1, user.getUser_id());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newPass");
        assertEquals("newPass", user.getPassword());
    }

    @Test
    public void testGetUsername() {
        user.setUsername("testUser");
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        user.setPassword("testPass");
        assertEquals("testPass", user.getPassword());
    }
}