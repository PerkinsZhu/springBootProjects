package com.perkins.springbootkafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GroupConsumer {

    @KafkaListener(topics = "test", groupId = "group01")
    public void listen(ConsumerRecord<?, ?> record) throws Exception {
        log.info("groupId = 01-listen01, topic = {}, offset = {}, value = {}", record.topic(), record.offset(), record.value());
    }


    @KafkaListener(topics = "test", groupId = "group01")
    public void listen2(ConsumerRecord<?, ?> record) throws Exception {
        log.info("groupId = 01-listen02, topic = {}, offset = {}, value = {}", record.topic(), record.offset(), record.value());
    }

    @KafkaListener(topics = "test", groupId = "group02")
    public void listen3(ConsumerRecord<?, ?> record) throws Exception {
        log.info("groupId = 02-listen01, topic = {}, offset = {}, value = {}", record.topic(), record.offset(), record.value());
    }

    @KafkaListener(topics = "streamOut", groupId = "group01")
    public void streamOut(ConsumerRecord<?, ?> record) throws Exception {
        log.info("groupId = streamOut-listen01, topic = {}, offset = {}, value = {}", record.topic(), record.offset(), record.value());
    }
}
