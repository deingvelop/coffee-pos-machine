package com.demo.coffeePosMachine.dataCollectPlatform;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "order", containerFactory = "kafkaListenerContainerFactory")
    public void orderListener(String orderLog) {
        try {
            log.info("▶▶▶▶▶▶▶▶ RECEIVED DATA FROM KAFKA: " + orderLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
