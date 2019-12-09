package com.xy.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "kafka管理服务", tags = {"kafka管理"})
public class kafkaController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendTest(String msg) {
        kafkaTemplate.send("signed", msg);
    }

}

