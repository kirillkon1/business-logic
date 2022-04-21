package com.example.buslogic.controller;

import com.example.buslogic.activeMQ.MessageSender;
import com.example.buslogic.model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/msg")
public class MessageController {

    @Autowired
    MessageSender messageSender;

    @PutMapping()
    public void sendMessage(@RequestBody MessageDTO dto){
        messageSender.sendQueue(dto.getContent());
    }
}
