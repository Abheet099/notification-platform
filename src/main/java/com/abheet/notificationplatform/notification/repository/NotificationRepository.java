package com.abheet.notificationplatform.notification.repository;

import com.abheet.notificationplatform.notification.entity.Notification;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
