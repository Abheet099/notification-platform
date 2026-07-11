package com.abheet.notificationplatform.notification.service;

import com.abheet.notificationplatform.common.exception.ResourceNotFoundException;
import com.abheet.notificationplatform.notification.async.NotificationAsyncProcessor;
import com.abheet.notificationplatform.notification.dto.CreateNotificationRequest;
import com.abheet.notificationplatform.notification.dto.NotificationResponse;
import com.abheet.notificationplatform.notification.entity.Notification;
import com.abheet.notificationplatform.notification.mapper.NotificationMapper;
import com.abheet.notificationplatform.notification.repository.NotificationRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final NotificationAsyncProcessor notificationAsyncProcessor;

    public NotificationService(
            NotificationRepository notificationRepository,
            NotificationMapper notificationMapper,
            NotificationAsyncProcessor notificationAsyncProcessor
    ) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.notificationAsyncProcessor = notificationAsyncProcessor;
    }

    @Transactional
    public NotificationResponse create(CreateNotificationRequest request) {
        Notification notification = notificationMapper.toEntity(request);
        Notification savedNotification = notificationRepository.save(notification);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                notificationAsyncProcessor.process(savedNotification.getId());
            }
        });
        return notificationMapper.toResponse(savedNotification);
    }

    @Transactional(readOnly = true)
    public NotificationResponse getById(UUID id) {
        return notificationRepository.findById(id)
                .map(notificationMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> list() {
        return notificationRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(notificationMapper::toResponse)
                .toList();
    }
}
