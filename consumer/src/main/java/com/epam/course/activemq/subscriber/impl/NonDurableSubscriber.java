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
public class NonDurableSubscriber implements Subscriber {

    @Value("${topics.topic}")
    private String topic;

    @Override
    @JmsListener(destination = "${topics.topic}", containerFactory = "jmsNonDurableListenerContainerFactory")
    public void process(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("Received message from NonDurableSubscriber: {}", textMessage.getText());
    }
}
