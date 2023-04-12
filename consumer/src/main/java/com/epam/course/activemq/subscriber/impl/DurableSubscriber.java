package com.epam.course.activemq.subscriber.impl;

import com.epam.course.activemq.subscriber.Subscriber;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DurableSubscriber implements Subscriber {

    @Value("${topics.topic}")
    private String topic;

    @Override
    @JmsListener(destination = "${topics.topic}", containerFactory = "jmsDurableListenerContainerFactory")
    public void process(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("Received message from DurableSubscriber: {}", textMessage.getText());
    }
}
