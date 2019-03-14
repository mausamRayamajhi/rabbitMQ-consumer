package com.mausamrayamajhi.rabbitmqconsumer.listner;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageListenerImpl implements MessageListener {
    @Override
    public void onMessage(String message) throws InterruptedException {
        System.out.println(new Date());
        Thread.sleep(5000);
        System.out.println("Message recieved : "+message);
        System.out.println(new Date());
    }
}
