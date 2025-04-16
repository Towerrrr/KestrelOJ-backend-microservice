package com.t0r.kestrelojbackendquestionservice.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MyMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param exchange
     * @param routingKey
     * @param message
     */
    public void sendMessage(String exchange, String routingKey, String message) {
        log.info("Sending message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
