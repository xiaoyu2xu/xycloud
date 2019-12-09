package com.xy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class kafkaContrller {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendTest(String msg) {
        kafkaTemplate.send("signed", msg);
    }

}

