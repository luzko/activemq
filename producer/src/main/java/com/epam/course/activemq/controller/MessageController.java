package com.epam.course.activemq.controller;

import com.epam.course.activemq.model.Message;
import com.epam.course.activemq.producer.replier.RequestProducer;
import com.epam.course.activemq.producer.virtual.Producer;
import com.epam.course.activemq.publisher.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/producer")
@RequiredArgsConstructor
public class MessageController {

    private final Publisher publisher;

    private final RequestProducer requestProducer;

    private final Producer producer;

    @PostMapping("topic")
    public void publishMessage(@RequestBody Message message) {
        publisher.send(message.toString());
    }

    @PostMapping("reply")
    public void sendMessageWithReply(@RequestBody Message message) {
        requestProducer.send(message.toString());
    }

    @PostMapping("virtual")
    public void sendMessageToVirtualTopic(@RequestBody Message message) {
        producer.send(message.toString());
    }
}
