package com.example.user.external.kafka.kafka_consumer;

import com.example.user.config.KafkaConstant;
import com.example.user.dto.CreateUserRequest;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationConsumer {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaConstant.USER_REGISTRATION_TOPIC, groupId = KafkaConstant.USER_GROUP_ID)
    public void consumeUserRegistration(String message) {
        log.info("Received user registration event: {}", message);
        
        try {
            CreateUserRequest event = objectMapper.readValue(message, CreateUserRequest.class);
            
            // Check if user already exists
            if (userRepository.existsByEmail(event.getEmail())) {
                log.warn("User with email {} already exists, skipping creation", event.getEmail());
                return;
            }
            
            // Create and save new user
            User user = User.builder()
                    .email(event.getEmail())
                    .fullname(event.getFullname() != null ? event.getFullname() : event.getEmail())
                    .phoneNumber(event.getPhoneNumber())
                    .isActive(true)
                    .build();
            
            userRepository.save(user);
            log.info("User created successfully from registration event: {}", event.getEmail());
            
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize user registration event: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Failed to process user registration event: {}", e.getMessage());
        }
    }
}
