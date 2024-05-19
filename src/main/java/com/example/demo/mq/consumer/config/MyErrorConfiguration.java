package com.example.demo.mq.consumer.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.rabbitmq.listener.simple.retry",name = "enabled",havingValue = "true")
public class MyErrorConfiguration {

    //交换机
    @Bean
    public DirectExchange errorExchange(){
        return ExchangeBuilder.directExchange("myError.exchange").build();
    }

    //队列
    @Bean
    public Queue errorQueue(){
        return new Queue("myError.queue");
    }

    //连接
    @Bean
    public Binding errorBinding1(){
        return BindingBuilder.bind(errorQueue()).to(errorExchange()).with("myError");
    }

    //创建错误处理

    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate){
        return new RepublishMessageRecoverer(rabbitTemplate, "myError.exchange", "myError");
    }

}
