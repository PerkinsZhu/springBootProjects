package com.perkins.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

public class ConsumerGroup {

    @Test
    public void consumer() throws Exception {
        String topic = "test";
        String group = "g-01";
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("group.id", group);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("fetch.max.bytes", "2");
        props.put("key.deserializer", Class.forName("org.apache.kafka.common.serialization.StringDeserializer"));
        props.put("value.deserializer", Class.forName("org.apache.kafka.common.serialization.StringDeserializer"));
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Arrays.asList(topic));


        Set<TopicPartition> assignment = new HashSet<>();
        // 在poll()方法内部执行分区分配逻辑，该循环确保分区已被分配。
        // 当分区消息为0时进入此循环，如果不为0，则说明已经成功分配到了分区。
        while (assignment.size() == 0) {
            consumer.poll(100);
            // assignment()方法是用来获取消费者所分配到的分区消息的
            // assignment的值为：topic-demo-3, topic-demo-0, topic-demo-2, topic-demo-1
            assignment = consumer.assignment();
        }
        System.out.println(assignment);

        for (TopicPartition tp : assignment) {
            int offset = 80;
            System.out.println("分区 " + tp + " 从 " + offset + " 开始消费");
//            consumer.seek(tp, offset);
//            consumer.seekToBeginning(); //从开始处开始消费，但是不一定为0，因为开始的数据有可能被删除掉
//            consumer.seekToEnd(); //从最后开始消费
            //指定从某一时间点开始消费
            Map<TopicPartition, Long> timestampToSearch = new HashMap<>();
            // 设置查询分区时间戳的条件：获取当前时间前一天之后的消息
            timestampToSearch.put(tp, System.currentTimeMillis() - 24 * 3600 * 1000);
            setOffsetByTime(consumer, assignment, timestampToSearch);

        }


        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            // 消费记录
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.offset() + ":" + record.value() + ":" + record.partition());
            }
        }

    }

    private void setOffsetByTime(KafkaConsumer<String, String> consumer, Set<TopicPartition> assignment, Map<TopicPartition, Long> timestampToSearch) {
        // timestampToSearch的值为{topic-demo-0=1563709541899, topic-demo-2=1563709541899, topic-demo-1=1563709541899}
        Map<TopicPartition, OffsetAndTimestamp> offsets = consumer.offsetsForTimes(timestampToSearch);
        for (TopicPartition tp : assignment) {
            // 获取该分区的offset以及timestamp
            OffsetAndTimestamp offsetAndTimestamp = offsets.get(tp);
            // 如果offsetAndTimestamp不为null，则证明当前分区有符合时间戳条件的消息
            if (offsetAndTimestamp != null) {
                consumer.seek(tp, offsetAndTimestamp.offset());
            }
        }
    }
}
