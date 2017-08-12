package com.darksci.pardot.api.rest.handlers.email;

import com.darksci.pardot.api.response.email.Email;
import com.darksci.pardot.api.rest.handlers.BaseResponseHandlerTest;
import com.darksci.pardot.api.rest.handlers.user.UserQueryResponseHandlerTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EmailReadResponseHandlerTest extends BaseResponseHandlerTest {
    private static final Logger logger = LoggerFactory.getLogger(EmailReadResponseHandlerTest.class);

    /**
     * Validates we can parse a User Read
     */
    @Test
    public void testRead() throws IOException {
        final String input = readFile("emailRead.xml");
        final Email email = new EmailReadResponseHandler().parseResponse(input);
        logger.info("Result: {}", email);

        assertNotNull("Should not be null", email);
        assertEquals("Has correct id", 12345L, (long) email.getId());
        assertEquals("Has correct  name", "Test Email To Stephen", email.getName());
        assertEquals("Has correct subject", "This is the Subject", email.getSubject());
        assertNotNull("Has non-null message", email.getMessage());
        assertNotNull("Has text message", email.getMessage().getText());
        assertNotNull("Has html message", email.getMessage().getHtml());
        assertTrue("Is One-to-One email", email.isOneToOne());
        assertEquals("Has correct createdAt", "2017-08-11T21:41:08.000", email.getCreatedAt().toString());
    }
}