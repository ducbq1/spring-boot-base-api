package com.example.demo.user.service;


import com.example.demo.user.domain.User;
import com.example.demo.user.event.UserCreatedEvent;
import com.example.demo.user.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;


    public UserService(ApplicationEventPublisher eventPublisher, UserRepository userRepository) {
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;
    }

    public void createUser(String userId) {
        System.out.println("✅ Creating user with ID: " + userId);
        UserCreatedEvent event = new UserCreatedEvent(this, userId);
        eventPublisher.publishEvent(event);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) {
        userRepository.insert(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void pathUser(Long id, Map<String, Object> updates) {
        userRepository.patch(id, updates);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
