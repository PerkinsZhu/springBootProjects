package com.perkins.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class MQProducer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");

        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("producer");
        producer.start();
        try {
            int count = 0;
            while (true) {
                Thread.sleep(300); //每秒发送一次
                Message msg = new Message("itmayiedu-topic", // topic 主题名称
                        "TagA", // tag 临时值
                        ("itmayiedu-" + count++).getBytes()// body 内容
                );
                SendResult sendResult = producer.send(msg);
//                System.out.println(sendResult.toString());
                System.out.println("--->" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

}