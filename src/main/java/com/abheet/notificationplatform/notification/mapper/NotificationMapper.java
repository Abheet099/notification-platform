package com.abheet.notificationplatform.notification.mapper;

import com.abheet.notificationplatform.notification.dto.CreateNotificationRequest;
import com.abheet.notificationplatform.notification.dto.NotificationResponse;
import com.abheet.notificationplatform.notification.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification toEntity(CreateNotificationRequest request) {
        return new Notification(request.recipient(), request.subject(), request.body());
    }

    public NotificationResponse toResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getRecipient(),
                notification.getSubject(),
                notification.getBody(),
                notification.getStatus(),
                notification.getProviderMessageId(),
                notification.getFailureReason(),
                notification.getCreatedAt(),
                notification.getUpdatedAt(),
                notification.getSentAt()
        );
    }
}
