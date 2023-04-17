package com.epam.course.activemq.consumer.virtual;

import javax.jms.JMSException;
import javax.jms.Message;

public interface VirtualConsumer {

    void consume1(Message message) throws JMSException;

    void consume2(Message message) throws JMSException;

    void consume3(Message message) throws JMSException;

    void consume4(Message message) throws JMSException;

    void consume5(Message message) throws JMSException;
}
