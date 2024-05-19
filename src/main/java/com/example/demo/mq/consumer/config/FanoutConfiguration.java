package com.example.demo.mq.consumer.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings({"all"})
//@Configuration
public class FanoutConfiguration {

    @Bean
    public DirectExchange directExchange(){
//        return ExchangeBuilder.fanoutExchange("hmall.fanout2").build();
        return new DirectExchange("hmall.direct");
    }

    @Bean
    public Queue directQueue2(){
//        return QueueBuilder.durable("fanout.queue3").build();
        return new Queue("direct.queue2");
    }

    @Bean
    public Binding directQueue1BindingRed(Queue directQueue2,DirectExchange directExchange){
        return BindingBuilder.bind(directQueue2).to(directExchange).with("red");
    }

    @Bean
    public Binding directQueue1BindingBlue(Queue directQueue1,DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("yellow");
    }



}
