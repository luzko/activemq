package com.epam.course.activemq.publisher.impl;

import com.epam.course.activemq.publisher.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublisherImpl implements Publisher {

    private final JmsTemplate jmsTemplate;

    @Value("${topics.topic}")
    private String topic;

    public PublisherImpl(@Qualifier("jmsTopicTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(String message) {
        log.info("Publishing message: {}", message);
        jmsTemplate.convertAndSend(topic, message);
    }
}
