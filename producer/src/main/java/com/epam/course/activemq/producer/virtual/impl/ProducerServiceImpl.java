package com.epam.course.activemq.producer.virtual.impl;

import com.epam.course.activemq.producer.virtual.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProducerServiceImpl implements Producer {

    private final JmsTemplate jmsTemplate;

    @Value("${topics.virtual}")
    private String virtualTopic;

    public ProducerServiceImpl(@Qualifier("jmsTopicTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(String message) {
        log.info("Publishing message: {}", message);
        jmsTemplate.convertAndSend(virtualTopic, message);
    }
}
