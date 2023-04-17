package com.epam.course.activemq.consumer.virtual.impl;

import com.epam.course.activemq.consumer.virtual.VirtualConsumer;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VirtualConsumerImpl implements VirtualConsumer {

    @Value("${topics.virtual}")
    private String virtualTopic;

    @Override
    @JmsListener(destination = "Consumer.consumer1." + "${topics.virtual}",
        containerFactory = "jmsListenerVirtualQueueContainerFactory")
    public void consume1(Message message) throws JMSException {
        consume(message, 1);
    }

    @Override
    @JmsListener(destination = "Consumer.consumer1." + "${topics.virtual}",
        containerFactory = "jmsListenerVirtualQueueContainerFactory")
    public void consume2(Message message) throws JMSException {
        consume(message, 2);
    }

    @Override
    @JmsListener(destination = "Consumer.consumer1." + "${topics.virtual}",
        containerFactory = "jmsListenerVirtualQueueContainerFactory")
    public void consume3(Message message) throws JMSException {
        consume(message, 3);
    }

    @Override
    @JmsListener(destination = "Consumer.consumer1." + "${topics.virtual}",
        containerFactory = "jmsListenerVirtualQueueContainerFactory")
    public void consume4(Message message) throws JMSException {
        consume(message, 4);
    }

    @Override
    @JmsListener(destination = "Consumer.consumer1." + "${topics.virtual}",
        containerFactory = "jmsListenerVirtualQueueContainerFactory")
    public void consume5(Message message) throws JMSException {
        consume(message, 5);
    }

    private void consume(Message message, int consumerId) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("Received message: {} from: {}", textMessage.getText(), consumerId);
    }
}
