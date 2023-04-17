package com.epam.course.activemq.consumer.replier;

import javax.jms.JMSException;
import javax.jms.Message;

public interface ReplierConsumer {

    String consume(Message message) throws JMSException;
}
