package com.mausamrayamajhi.rabbitmqconsumer.configuration;

import org.springframework.amqp.core.*;
import com.mausamrayamajhi.rabbitmqconsumer.listner.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class RabbitMqConfiguration {
    public static final String ROUTING_KEY = "my.queue.key";

    @Bean
    Queue queue() {
        return new Queue(ROUTING_KEY, true);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("my_queue_exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    SimpleMessageListenerContainer container (ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(ROUTING_KEY);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter myQuereListner(MessageListener listener){
        return new MessageListenerAdapter(listener,"onMessage");
    }
}
