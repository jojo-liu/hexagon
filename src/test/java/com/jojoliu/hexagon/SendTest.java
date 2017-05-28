package com.jojoliu.hexagon;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Jojo on 24/05/2017.
 */
public class SendTest {
    private static String QUEUE_NAME="test";

    public static void main(String[] argv) throws Exception{
        //create connection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        //create channel
        Channel channel = connection.createChannel();
        //create queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //finally
        channel.close();
        connection.close();
    }
}
