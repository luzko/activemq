package com.epam.course.activemq.consumer.replier.impl;

import com.epam.course.activemq.consumer.replier.ReplierConsumer;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReplierConsumerImpl implements ReplierConsumer {

    @Value("${queues.request-queue}")
    private String requestQueue;

    @Override
    @JmsListener(destination = "${queues.request-queue}", containerFactory = "jmsListenerQueueContainerFactory")
    @SendTo("${queues.reply-queue}")
    public String consume(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("Received message from ReplierConsumer: {}", textMessage.getText());
        return "OK";
    }
}
