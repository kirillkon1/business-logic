package com.example.buslogic.activeMQ;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.time.LocalDateTime;

@Component
@Slf4j
public class MessageConsumer {

    @JmsListener(destination = "common-text-queue")
    public void receiveMessage(Message message) {
        ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
        try {
            System.out.println(LocalDateTime.now() + ": " + activeMQTextMessage.getText());
//            log.info(activeMQTextMessage.getText());
        } catch (JMSException e) {
            log.error("{0}", e);
        }

    }

    @JmsListener(destination = "admin-text-queue")
    public void receiveAdminMessage(Message message) {
        ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
        try {
            System.out.println(LocalDateTime.now() + " [ADMIN INFO]: " + activeMQTextMessage.getText());
        } catch (JMSException e) {
            log.error("{0}", e);
        }

    }

}
