package com.perkins.kafka;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        String input = "test";   //输入 topic
        String output = "streamOut";  //输出 topic

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "logProcessor");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        //使用Serdes类创建序列化/反序列化所需的Serde实例 Serdes类为以下类型提供默认的实现：String、Byte array、Long、Integer和Double。
        Serde<String> stringSerde = Serdes.String();

        // 从 input中消费数据，然后作为生产者转发到output，之后 output的消费者可以消费到数据
        // 在这个转发的过程中，可以对数据流进行处理过滤组合等操作
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> simpleFirstStream = builder.stream(input, Consumed.with(stringSerde, stringSerde));
        KStream<String, String> upperCasedStream = simpleFirstStream.mapValues(line -> {
            System.out.printf("中转消息:" + line);
            return line;
        });
        // 把转换结果输出到另一个topic
        upperCasedStream.to(output, Produced.with(stringSerde, stringSerde));

        //创建和启动KStream
        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), properties);
        kafkaStreams.start();
    }
}