package at.kaindorf.htl.hosnobi.bl.db;

import at.kaindorf.htl.hosnobi.bl.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDatabaseTest {
    private User user = new User(0, "Test", new int [] {0, 0, 0});
    @Test
    @Order(0)
    void testUserCreation() {
        assertEquals(user, UserDatabase.getInstance().newUser("Test"));
    }

    @Test
    @Order(1)
    void testGetUser() {
        assertEquals(user, UserDatabase.getInstance().getUserById(0));
    }

    @Test
    @Order(2)
    void testExists() {
        assertEquals(true, UserDatabase.getInstance().userExists(0));
        assertEquals(true, UserDatabase.getInstance().userExists("Test"));
    }
}
