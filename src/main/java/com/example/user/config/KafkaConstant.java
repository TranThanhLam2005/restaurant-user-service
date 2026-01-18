package com.example.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaConstant {
    public static final String USER_GROUP_ID = "user-group-id";
    public static final String KAFKA_PORT = "9092";
    public static final String KAFKA_HOST_URL = "localhost:" + KAFKA_PORT;

    public static final String RESPONSE = "-response";
    public static final String USER_TOPIC = "user-topic";
    public static final String USER_TOPIC_RESPONSE = USER_TOPIC + RESPONSE;
    
    // Topic for user registration events (consumed from identity service)
    public static final String USER_REGISTRATION_TOPIC = "user-registration-topic";
}
