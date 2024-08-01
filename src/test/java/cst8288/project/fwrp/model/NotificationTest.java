package cst8288.project.fwrp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class NotificationTest {

    @Test
    public void testNotificationBuilder() {
        User user = new User();
        Item item = new Item();
        LocalDateTime sentTime = LocalDateTime.now();
        
        Notification notification = new Notification.Builder()
            .setUser(user)
            .setItem(item)
            .setMessage("Test Message")
            .setCommMethod(CommMethodType.Email)
            .setSentTime(sentTime)
            .build();

        assertNotNull(notification);
        assertEquals(user, notification.getUser());
        assertEquals(item, notification.getItem());
        assertEquals("Test Message", notification.getMessage());
        assertEquals(CommMethodType.Email, notification.getCommMethod());
        assertEquals(sentTime, notification.getSentTime());
    }
    
    @Test
    public void testWrongNotificationMethod() {
        User user = new User();
        Item item = new Item();
        LocalDateTime sentTime = LocalDateTime.now();
        
        Notification notification = new Notification.Builder()
            .setUser(user)
            .setItem(item)
            .setMessage("Test Message")
            .setCommMethod(CommMethodType.Both)
            .setSentTime(sentTime)
            .build();

        assertNotNull(notification);
        assertEquals(user, notification.getUser());
        assertEquals(item, notification.getItem());
        assertEquals("Test Message", notification.getMessage());
        assertNotEquals(CommMethodType.Email, notification.getCommMethod());
        assertEquals(sentTime, notification.getSentTime());
    }
    
    @Test
    public void testCommMethodTypeCode() {
        assertEquals(1, CommMethodType.Email.code);
        assertEquals(2, CommMethodType.Phone.code);
        assertEquals(3, CommMethodType.Both.code);
    }
}
