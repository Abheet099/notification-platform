package com.abheet.notificationplatform.notification.repository;

import com.abheet.notificationplatform.notification.entity.Notification;
import com.abheet.notificationplatform.notification.entity.NotificationStatus;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    Page<Notification> findByStatus(NotificationStatus status, Pageable pageable);

    Page<Notification> findByRecipientContainingIgnoreCase(String recipient, Pageable pageable);

    Page<Notification> findByStatusAndRecipientContainingIgnoreCase(
            NotificationStatus status,
            String recipient,
            Pageable pageable
    );
}
