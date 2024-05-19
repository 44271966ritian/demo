package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger=LoggerFactory.getLogger(RabbitTemplate.class);

    @Test
    void contextLoads() {
    }

    @Test
    public void testSimpleQueue(){

        //队列名称
        String queueName = "simple.queue";
        //消息
        String message = "hello,spring amqp";
        //发送消息
        rabbitTemplate.convertAndSend(queueName,message);
        rabbitTemplate.convertAndSend(queueName,message);
        rabbitTemplate.convertAndSend(queueName,message);

    }

    @Test
    void testWorkQueue() throws InterruptedException {
        for (int i = 1; i <= 50; i++) {
            //队列名称
            String queueName = "work.queue";
            //消息
            String message = "hello,worker,message_"+i;
            //发送消息
            rabbitTemplate.convertAndSend(queueName,message);
            Thread.sleep(20);
        }
    }


    @Test
    void testFanoutQueue() throws InterruptedException {
        for (int i = 1; i <= 50; i++) {
            //队列名称
            String exchangeName = "hmall.fanout";
            //消息
            String message = "hello,everyone"+i;
            //发送消息
            rabbitTemplate.convertAndSend(exchangeName,"",message);
        }
    }

    @Test
    void testDirectQueue1() throws InterruptedException {
            //队列名称
            String exchangeName = "hmall.direct";
            //消息
            String message = "红色警报";
            //发送消息
            rabbitTemplate.convertAndSend(exchangeName,"red",message);
    }

    @Test
    void testDirectQueue2() throws InterruptedException {
        //队列名称
        String exchangeName = "hmall.direct";
        //消息
        String message = "蓝色警报";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"blusde",message);
    }


    /*
    * TODO:网上自查 confirm
    * */
    @Test
    void testTopicQueue1() throws InterruptedException {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    logger.info("消息发送成功");
                }else {
                    logger.info("消息发送失败");
                    logger.info("错误原因"+cause);
                }
            }
        });
        //队列名称
        String exchangeName = "hmall.topic";
        //消息
        String message = "中国新闻";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"china.news",message);
    }

    @Test
    void testTopicQueue2() throws InterruptedException {
        //队列名称
        String exchangeName = "hmall.topic";
        //消息
        String message = "日本新闻";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"japan.news",message);
    }

    @Test
    void testTopicQueue3() throws InterruptedException {
        //队列名称
        String exchangeName = "hmall.topic";
        //消息
        String message = "中国天气";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"china.weather",message);
    }



    @Test
    void testSendObject(){
        Map<String,Object> msg = new HashMap<>(2);
        msg.put("name","jack");
        msg.put("age",21);
        rabbitTemplate.convertAndSend("object.queue",msg);
    }

    @Test
    @SuppressWarnings("all")
    public void test3(){
        //设置交换机处理失败消息的模式
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                logger.info("return 执行了...."+returnedMessage.getReplyText());
            }

            /**
             *
             * @param message 消息对象
             * @param replyCode 错误码
             * @param replyText 错误信息
             * @param exchange 交换机名称
             * @param routingKey 路由key
             */

        });

        rabbitTemplate.convertAndSend("hmall.topic", "123", "什么鬼");
    }



    @Test
    void testConfirmCallback(){

        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        //cd.getFuture().

        //rabbitTemplate.convertAndSend("hmall.direct","red","hello",cd);
    }


    @Test
    void testPageOut(){
        Message me = MessageBuilder.withBody("hello".getBytes(StandardCharsets.UTF_8)).
                setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();
        for (int i = 0; i < 1000000; i++) {
            rabbitTemplate.convertAndSend("lazy.queue",me);
        }
    }

    @Test
    void testSendTTLMessage(){
//        Message me = MessageBuilder.withBody("hello".getBytes(StandardCharsets.UTF_8)).
//                setExpiration("30000")
//                .build();

        rabbitTemplate.convertAndSend("simple.direct", "hi", "换一个参数", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(3000));
                return message;
            }
        });
        log.info("消息发送成功");
    }


    @Test
    void testMyError(){
        Message msg = MessageBuilder.withBody("无法转化".getBytes(StandardCharsets.UTF_8))
                .build();
        rabbitTemplate.convertAndSend("simple.direct","hi",msg);
    }

}
