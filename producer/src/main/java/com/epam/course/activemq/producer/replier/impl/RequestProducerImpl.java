package com.epam.course.activemq.producer.replier.impl;

import com.epam.course.activemq.producer.replier.RequestProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestProducerImpl implements RequestProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${queues.request-queue}")
    private String requestQueue;

    @Value("${queues.reply-queue}")
    private String replyQueue;

    public RequestProducerImpl(@Qualifier("jmsQueueTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(String message) {
        log.info("Publishing message: {}", message);
        jmsTemplate.convertAndSend(requestQueue, message);

        String reply = String.valueOf(jmsTemplate.receiveAndConvert(replyQueue));
        log.info("Received reply message: {}", reply);
    }
}
