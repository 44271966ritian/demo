package com.example.demo.mq.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import java.util.Map;

@SuppressWarnings({"ALL"})
@Slf4j
@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
//        log.info("spring 消费者接收到 simple.queue 的消息: ["+msg+"]");
////        throw new RuntimeException("故意的");
////        if (true){
////            throw new MessageConversionException("故意的");
////        }
////        log.info("消息处理完成");
//    }


    @RabbitListener(queues = "dix.queue")
    public void listenDlxQueue(Message msg){
        log.info("消费者 接收到 dix.queue 的消息: ["+msg+"]");
    }

    @RabbitListener(queues = "simple.queue")
    public void listenSimple(Message msg){
        log.info("消费者 接收到 simple.queue 的消息: ["+msg+"]");
    }

    /*
    * TODO:20毫秒一条消息
    *  一秒能够处理50条消息
    * */
    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(Message msg) throws InterruptedException {
        System.out.println("消费者1 接收到 work.queue 的消息: ["+msg+"]");
        Thread.sleep(20);
    }


    /*
    * TODO:一秒5条消息
    * */
    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2 接收到 work.queue 的消息: ["+msg+"]");
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1 接收到 fanout.queue1 的消息: ["+msg+"]");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2 接收到 fanout.queue2 的消息: ["+msg+"]");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct.queue1",durable = "true"),
            exchange = @Exchange(name = "hmall.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenDirectQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1 接收到 direct.queue1 的消息: ["+msg+"]");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct.queue2",durable = "true"),
            exchange = @Exchange(name = "hmall.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenDirectQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2 接收到 direct.queue2 的消息: ["+msg+"]");
    }

    @RabbitListener(queues = "topic.queue1")
    public void listenTopicQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1 接收到 topic.queue1 的消息: ["+msg+"]");
    }

    @RabbitListener(queues = "topic.queue2")
    public void listenTopicQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2 接收到 topic.queue1 的消息: ["+msg+"]");
    }

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String,Object> msg){
        System.out.println("消费者 接收到 object.queue 的消息: ["+msg+"]");
    }






}
