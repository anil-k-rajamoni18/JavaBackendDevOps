package service;

import org.akteach.model.User;
import org.akteach.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testAddUser() {
        User user = new User("1", "Alice", "alice@example.com");
        userService.addUser(user);

        User retrievedUser = userService.getUser("1");
        assertNotNull(retrievedUser);
        assertEquals("Alice", retrievedUser.getName());
    }

    @Test
    void testGetUser() {
        User user = new User("1", "Alice", "alice@example.com");
        userService.addUser(user);

        User retrievedUser = userService.getUser("1");
        assertEquals("Alice", retrievedUser.getName());
    }

    @Test
    void testDeleteUser() {
        User user = new User("1", "Alice", "alice@example.com");
        userService.addUser(user);

        userService.deleteUser("1");
        assertNull(userService.getUser("1"));
    }
}