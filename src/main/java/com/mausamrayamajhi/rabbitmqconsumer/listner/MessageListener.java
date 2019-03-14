package com.mausamrayamajhi.rabbitmqconsumer.listner;

public interface MessageListener {
    public void onMessage(String message) throws InterruptedException;
}
