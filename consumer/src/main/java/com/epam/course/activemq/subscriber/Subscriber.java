package com.epam.course.activemq.subscriber;

import javax.jms.JMSException;
import javax.jms.Message;

public interface Subscriber {

    void process(Message message) throws JMSException;
}
