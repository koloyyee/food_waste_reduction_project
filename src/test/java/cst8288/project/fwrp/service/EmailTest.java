package cst8288.project.fwrp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.PropertiesLoader;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {
    
    private EmailService emailService;
    private Logger logger; // Mock logger for verification

    @BeforeEach
    void setUp() {
        emailService = new EmailService();
        logger = Mockito.mock(Logger.class);
        emailService.setLogger(logger); // Inject the mock logger
    }


}
