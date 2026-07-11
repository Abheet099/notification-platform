package com.abheet.notificationplatform.notification.dto;

import com.abheet.notificationplatform.notification.entity.NotificationStatus;
import java.time.Instant;
import java.util.UUID;

public record NotificationResponse(
        UUID id,
        String recipient,
        String subject,
        String body,
        NotificationStatus status,
        String providerMessageId,
        String failureReason,
        Instant createdAt,
        Instant updatedAt,
        Instant sentAt
) {
}
