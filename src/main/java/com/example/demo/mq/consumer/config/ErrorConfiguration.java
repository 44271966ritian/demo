//package com.example.demo.mq.consumer.config;
//
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.retry.MessageRecoverer;
//import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///*
//* TODO:确保消费者消费到了消息
//*  确定消费者消费的可靠性
//*  首先是三个bean对象
//*  创建了交换机，队列，连接
//*  第四个bean是重点，消息恢复程序，设置重新发布消息，给rabbitTemplate和交换机名称以及路由key就行了
//*  然后就会将本地重试三次的信息发送到指定错误消息队列
//*
//* */
//
//@Configuration
////当某个属性满足条件时失效
//@ConditionalOnProperty(prefix = "spring.rabbitmq.listener.simple.retry",name = "enabled",havingValue = "true")
//public class ErrorConfiguration {
//
//    @Bean
//    public DirectExchange directExchange(){
//        return ExchangeBuilder.directExchange("error.direct").build();
//    }
//
//    @Bean
//    public Queue errorQueue(){
//        return new Queue("error.queue");
//    }
//
//    @Bean
//    public Binding errorBinding(){
//        return BindingBuilder.bind(errorQueue()).to(directExchange()).with("error");
//    }
//
//    @Bean
//    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate){
//        return new RepublishMessageRecoverer(rabbitTemplate,"error.direct","error");
//    }
//
//}
