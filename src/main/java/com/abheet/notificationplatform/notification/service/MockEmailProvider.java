package com.abheet.notificationplatform.notification.service;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class MockEmailProvider {

    public String send(String recipient, String subject, String body) {
        if (recipient.toLowerCase().contains("fail")) {
            throw new MockEmailProviderException("Mock email provider rejected recipient");
        }
        return "mock-email-" + UUID.randomUUID();
    }
}
