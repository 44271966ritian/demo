package com.example.demo.mq.consumer.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MyReturnCallBack implements RabbitTemplate.ReturnsCallback {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {

//        log.info("消息路由失败,message:{},replyText:{},replyCode:{},exchange:{},routinfKey:{}",
//                returnedMessage.getMessage(),returnedMessage.getReplyText(),
//                returnedMessage.getReplyCode(), returnedMessage.getExchange(),
//                returnedMessage.getReplyText());

        log.info("消息路由失败"+returnedMessage.getReplyText());


    }
}
