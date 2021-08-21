package com.spring.practice_01.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class websocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Boolean greeting(Dto message) throws Exception {
        Thread.sleep(1000); // simulated delay

        System.out.println("스프링 웹소켓 컨트롤러에는 들어옴");
        return true;
    }
}
