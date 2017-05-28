package com.jojoliu.hexagon.service;

import com.alibaba.fastjson.JSONObject;
import com.jojoliu.hexagon.annotation.MessageCache;
import com.jojoliu.hexagon.model.CacheMessage;
import com.jojoliu.hexagon.util.MessageCacheUtil;
import com.jojoliu.hexagon.util.MessageFatalExceptionStrategy;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Jojo on 23/05/2017.
 */

@Component
@RabbitListener(queues = "${spring.rabbitmq.queueName}")
public class OrderRecv {
    private static final Logger logger = LoggerFactory.getLogger(OrderRecv.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MessageCache(cacheName="order",cacheKey="message",messageArgMapper="message")
    @RabbitHandler
    public void receive(String message,Channel channel) {
        CacheMessage cacheMessage= JSONObject.parseObject(message, CacheMessage.class);
        try{
            logger.debug("消息处理！" + message);
            //人为制造错误测试
            String a=null;
            a.equals("");
            logger.info("消息处理失败！" + message);
            MessageCacheUtil.remove(cacheMessage.getCacheCorrelationData().getCacheName(),cacheMessage.getCacheCorrelationData().getId());
        }catch(Exception e){
            //失败后交给死信，所以不通过缓存重发
            MessageCacheUtil.remove(cacheMessage.getCacheCorrelationData().getCacheName(),cacheMessage.getCacheCorrelationData().getId());
            //自动发送basic.nack如果requeue为true，重新入队
            //官方原文When a listener throws an exception, it is wrapped in a ListenerExecutionFailedException and, normally the message is rejected and requeued by the broker
            //throw new NullPointerException();
            //自动发送basic.nack如果requeue为false，有死信路由将被路由到死信队列，或直接被丢弃
            //AmqpRejectAndDontRequeueException可以不管requeue的设置，直接不重入队列
            //官方原文the listener can throw an AmqpRejectAndDontRequeueException to conditionally control this behavior
            //Setting defaultRequeueRejected to false will cause messages to be discarded (or routed to a dead letter exchange)
            //SimpleRabbitListenerContainerFactory可以设置defaultRequeueRejected
            //如果是交给ErrorHandle处理的异常，就不需要手动nack
            System.out.println("------------"+!MessageFatalExceptionStrategy.causeIsFatal(e.getCause())+"----------");
            if(!MessageFatalExceptionStrategy.causeIsFatal(e.getCause())){
                //捕获异常后，要使ErrorHandle生效，必须调用e.getCause().getClass()
                logger.debug("非致命错误！"+e.getCause().getClass());
                throw new AmqpRejectAndDontRequeueException(e.getMessage());
            }
        }
    }
}
