package com.abheet.notificationplatform.notification.async;

import static org.assertj.core.api.Assertions.assertThat;

import com.abheet.notificationplatform.notification.entity.Notification;
import com.abheet.notificationplatform.notification.entity.NotificationStatus;
import com.abheet.notificationplatform.notification.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.task.execution.pool.core-size=1")
class NotificationAsyncProcessorTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationAsyncProcessor notificationAsyncProcessor;

    @Test
    void marksNotificationSentWhenMockProviderSucceeds() throws Exception {
        Notification notification = notificationRepository.save(new Notification(
                "success@example.com",
                "Subject",
                "Body"
        ));

        notificationAsyncProcessor.process(notification.getId());
        Thread.sleep(300);

        Notification processed = notificationRepository.findById(notification.getId()).orElseThrow();
        assertThat(processed.getStatus()).isEqualTo(NotificationStatus.SENT);
        assertThat(processed.getProviderMessageId()).startsWith("mock-email-");
        assertThat(processed.getSentAt()).isNotNull();
    }

    @Test
    void marksNotificationFailedWhenMockProviderFails() throws Exception {
        Notification notification = notificationRepository.save(new Notification(
                "fail@example.com",
                "Subject",
                "Body"
        ));

        notificationAsyncProcessor.process(notification.getId());
        Thread.sleep(300);

        Notification processed = notificationRepository.findById(notification.getId()).orElseThrow();
        assertThat(processed.getStatus()).isEqualTo(NotificationStatus.FAILED);
        assertThat(processed.getFailureReason()).isEqualTo("Mock email provider rejected recipient");
    }
}
