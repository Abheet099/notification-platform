package com.abheet.notificationplatform.notification.async;

import com.abheet.notificationplatform.notification.entity.Notification;
import com.abheet.notificationplatform.notification.repository.NotificationRepository;
import com.abheet.notificationplatform.notification.service.MockEmailProvider;
import java.util.UUID;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NotificationAsyncProcessor {

    private final NotificationRepository notificationRepository;
    private final MockEmailProvider mockEmailProvider;

    public NotificationAsyncProcessor(
            NotificationRepository notificationRepository,
            MockEmailProvider mockEmailProvider
    ) {
        this.notificationRepository = notificationRepository;
        this.mockEmailProvider = mockEmailProvider;
    }

    @Async
    @Transactional
    public void process(UUID notificationId) {
        notificationRepository.findById(notificationId).ifPresent(this::processNotification);
    }

    private void processNotification(Notification notification) {
        notification.markProcessing();
        try {
            String providerMessageId = mockEmailProvider.send(
                    notification.getRecipient(),
                    notification.getSubject(),
                    notification.getBody()
            );
            notification.markSent(providerMessageId);
        } catch (RuntimeException exception) {
            notification.markFailed(exception.getMessage());
        }
    }
}
