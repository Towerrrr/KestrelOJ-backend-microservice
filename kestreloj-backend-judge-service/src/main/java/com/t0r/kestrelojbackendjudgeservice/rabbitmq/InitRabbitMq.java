package com.t0r.kestrelojbackendjudgeservice.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 用于创建测试程序用到的交换机和队列，程序启动前执行一次即可
 */
@Slf4j
public class InitRabbitMq {

    public static void doInit() {
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Channel channel;
            try (Connection connection = factory.newConnection()) {
                channel = connection.createChannel();
            String EXCHANGE_NAME = "code_exchange";
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            // 创建队列，随机分配一个队列名
            String queueName = "code_queue";
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, EXCHANGE_NAME, "my_routedKey");
            log.info("创建交换机和队列成功");
            }
        } catch (IOException | TimeoutException e) {
            log.error("创建交换机和队列失败", e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        doInit();
    }
}
