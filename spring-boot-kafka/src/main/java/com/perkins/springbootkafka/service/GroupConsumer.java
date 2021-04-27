package com.perkins.springbootkafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GroupConsumer {

    @KafkaListener(topics = "test", groupId = "group01")
    public void listen(ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("groupId = 01-listen01, topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }


    @KafkaListener(topics = "test", groupId = "group01")
    public void listen2(ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("groupId = 01-listen02,  topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }

    @KafkaListener(topics = "test", groupId = "group02")
    public void listen3(ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("groupId = 02-listen01, topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }
}
