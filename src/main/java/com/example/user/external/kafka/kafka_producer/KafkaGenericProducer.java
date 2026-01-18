package com.example.user.external.kafka.kafka_producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaGenericProducer<T> {

    @Autowired
    private KafkaTemplate<String, T> kafkaTemplate;

    public void sendMessage(String topic, T message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Sent message: " + message + " to topic: " + topic);
    }

}
