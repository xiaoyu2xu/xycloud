package com.xy.kafka;

import lombok.extern.java.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log
public class EhrAgreementListener {
    //@KafkaListener(topics = {"annul"}, groupId = "myContainer2")
    public void annul1(ConsumerRecord<String, String> record) {
        log.info("groupId = myContainer2, message = " + record.toString());
    }

    //@KafkaListener(topics = {"signed"}, groupId = "myContainer2")
    public void signed2(ConsumerRecord<String, String> record) {
        log.info("groupId = myContainer2, message = " + record.toString());
    }
}
