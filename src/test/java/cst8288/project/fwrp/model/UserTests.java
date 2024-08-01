package cst8288.project.fwrp.model;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;


public class UserTests {

    @Test
    public void testUserInfo() {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setPassword("password");

        assertEquals(1L, user.getId());
        assertEquals("Test User", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("1234567890", user.getPhone());
        assertEquals("password", user.getPassword());
    }
    
    @Test
    public void testUserType() {
        User user = new User();
        user.setType(UserType.Consumer);
        user.setCommMethod(CommMethodType.Email);
        user.setLocation("Test Location");
        user.setCreatedAt(LocalDateTime.now());

        assertEquals(UserType.Consumer, user.getType());
        assertEquals(CommMethodType.Email, user.getCommMethod());
        assertEquals("Test Location", user.getLocation());
        assertNotNull(user.getCreatedAt());
    }
    
    @Test
    public void testUserTypeCode() {
        assertEquals(1, UserType.Retailer.code);
        assertEquals(2, UserType.CharitableOrg.code);
        assertEquals(3, UserType.Consumer.code);
    }
}


