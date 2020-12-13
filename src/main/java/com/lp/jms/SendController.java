package com.lp.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendController {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping("/send")
    public void sendUserMessage(String topic){
        User user = new User("David","12345",new Date(),22);
        kafkaSender.send(topic,user);
    }
}
