package com.example.buslogic.activeMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendQueue(String str) {
        jmsTemplate.convertAndSend("common-text-queue", str);
    }

    public void sendAdminQueue(String str){
        jmsTemplate.convertAndSend("admin-text-queue", str);
    }

}
