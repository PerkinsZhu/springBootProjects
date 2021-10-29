package com.perkins.rabbitmq.simple;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: perkins Zhu
 * @date: 2021/10/29 16:12
 * @description:
 **/
public class SimpleTest {
    private String EXCHANGE_NAME = "directeExchange04";

    @Test
    public void testProdicer() throws IOException, TimeoutException, InterruptedException {
        Channel channel = getChannel();
        channel.confirmSelect();

        // 2.为通道声明exchange和exchange的类型
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true);
        for (int i = 0; i < 100000; i++) {
            String bindKey = "info";
            if (i % 2 == 0) {
                bindKey = "debug";
            }

            String msg = " hello rabbitmq, this is publish/subscribe mode mssage:   " + i;
            // 3.发送消息到指定的exchange,队列指定为空,由exchange根据情况判断需要发送到哪些队列
            channel.basicPublish(EXCHANGE_NAME, bindKey, null, msg.getBytes());

            System.out.println("product send a msg: " + msg);
            if (channel.waitForConfirms()) {
                System.out.println("发送成功");
            } else {
                //发送失败这里可进行消息重新投递的逻辑
                System.out.println("发送失败");
            }
            Thread.sleep(500);
        }

    }


    private Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        //设置访问的用户
        factory.setUsername("root");
        factory.setPassword("root");

        // 1.创建连接和通道
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }
// test
    public static void main(String[] args) throws IOException, TimeoutException {
        SimpleTest test = new SimpleTest();
        test.testConsumer();
    }

    public void testConsumer() throws IOException, TimeoutException {
        Channel channel = getChannel();
        // 2.为通道声明exchange以及exchange类型
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);

        // 3.创建随机名字的队列
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "test_queue_01";
        channel.queueDeclare(queueName, true, false, false, null);

        String bindKey = "debug";

        // 4.建立exchange和队列的绑定关系
        channel.queueBind(queueName, EXCHANGE_NAME, bindKey);
        channel.queueBind(queueName, EXCHANGE_NAME, "info");
        System.out.println(" **** Consumer1 keep alive ,waiting for messages, and then deal them" + "--" + queueName);
        // 5.通过回调生成消费者并进行监听
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws IOException {

                // 获取消息内容然后处理
                String msg = new String(body, "UTF-8");
                System.out.println("*********** Consumer1" + " get message :[" + msg + "]");
                // 手动进行ACK
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };


        // 6.消费消息
//        channel.basicConsume(queueName, true, consumer);
        channel.basicConsume(queueName, false, consumer);
    }


}
