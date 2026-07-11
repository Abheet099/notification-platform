package com.abheet.notificationplatform.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateNotificationRequest(
        @NotBlank @Email @Size(max = 320) String recipient,
        @NotBlank @Size(max = 255) String subject,
        @NotBlank String body
) {
}
