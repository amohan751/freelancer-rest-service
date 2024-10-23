package com.tadaah.freelancer.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tadaah.freelancer.entity.Notification;
import com.tadaah.freelancer.repository.NotificationRepository;

@Service
public class NotificationConsumerService {
    @Autowired
    NotificationRepository notificationRepository;

    @Async
    public void consumeNotification(String message){
        Notification notification = new Notification();
        notification.setNotificationMessage(message);
        notification.setNotificationTime(LocalDateTime.now());
        notificationRepository.save(notification);
    }

}
