//package com.example.demo.mq.consumer.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.ReturnedMessage;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@SuppressWarnings({"ALL"})
//@Configuration
//public class MqConfirmConfig implements ApplicationContextAware {
//
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
//        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//            @Override
//            public void returnedMessage(ReturnedMessage returnedMessage) {
//                log.info("收到消息的return callback,exchange:{},key:{},message:{},code:{},text:{}",
//                        returnedMessage.getExchange(),
//                        returnedMessage.getRoutingKey(),returnedMessage.getMessage(),
//                        returnedMessage.getReplyCode(),returnedMessage.getReplyText());
//            }
//        });
//    }
//}
