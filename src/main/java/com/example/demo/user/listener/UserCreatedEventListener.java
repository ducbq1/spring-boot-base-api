package com.example.demo.user.listener;

import com.example.demo.user.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventListener {

    // Tạo logger cho lớp
    private static final Logger logger = LoggerFactory.getLogger(UserCreatedEventListener.class);

    @Async
    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        System.out.println("📢 Received event for user ID: " + event.getUserId());
        logger.info("\uD83D\uDCE2 Received event for user ID: {}", event.getUserId());
    }
}
