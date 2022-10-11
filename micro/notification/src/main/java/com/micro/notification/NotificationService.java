package com.micro.notification;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.micro.clients.notification.NotificationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest request) {
        Notification notification = Notification.builder()
                .message(request.message())
                .toCustomerEmail(request.toCustomerEmail())
                .toCustomerId(request.toCustomerId())
                .sender("micro")
                .sentAt(LocalDateTime.now())
                .build();
        notificationRepository.saveAndFlush(notification);
    }

}
