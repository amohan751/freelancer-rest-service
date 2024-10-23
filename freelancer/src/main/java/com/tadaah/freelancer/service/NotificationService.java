package com.tadaah.freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationConsumerService consumerService;

    @Async
    public void sendNotification(String message){
        System.out.println("notification sent :"+message);
        consumerService.consumeNotification(message);
    }

}
