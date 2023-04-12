package com.epam.course.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.DefaultTransportListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@EnableJms
@Configuration
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String url;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.packages.trust-all}")
    private boolean trustAll;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(url);
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);
        connectionFactory.setTrustAllPackages(trustAll);
        connectionFactory.setTransportListener(new DefaultTransportListener());
        return connectionFactory;
    }

    @Bean
    @Qualifier("jmsDurableListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsDurableListenerContainerFactory() {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory());
        containerFactory.setPubSubDomain(true);
        containerFactory.setSubscriptionDurable(true);
        containerFactory.setClientId("durable-client-id");
        return containerFactory;
    }

    @Bean
    @Qualifier("jmsNonDurableListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsNonDurableListenerContainerFactory() {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory());
        containerFactory.setPubSubDomain(true);
        containerFactory.setSubscriptionDurable(false);
        return containerFactory;
    }

    @Bean
    @Qualifier("jmsListenerQueueContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerQueueContainerFactory() {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory());
        return containerFactory;
    }

    @Bean
    @Qualifier("jmsListenerVirtualQueueContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerVirtualQueueContainerFactory() {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory());
        containerFactory.setConcurrency("4-5");
        return containerFactory;
    }
}
