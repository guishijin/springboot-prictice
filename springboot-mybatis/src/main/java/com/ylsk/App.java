package com.ylsk;

import com.ylsk.dto.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//
//@Controller
//@EnableScheduling
//@SpringBootApplication
//public class SpringbootMybatisApplication {
//
//    public static void main(String[] args) {
//
//        String str = "";
//        for(int i=0; i< 128; i++)
//        {
//            String temp = "'"+i+"Hz/"+i*60/2+"r/m',";
//            str+=temp;
//        }
//        System.out.println(str);
//
//        SpringApplication.run(SpringbootMybatisApplication.class, args);
//    }
//
//
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    @GetMapping("/")
//    public String index() {
//        return "index.html";
//    }
//
//    @MessageMapping("/send")
//    @SendTo("/topic/send")
//    public SocketMessage send(SocketMessage message) throws Exception {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        message.date = df.format(new Date());
//        return message;
//    }
//
//    @Scheduled(fixedRate = 1000)
//    @SendTo("/topic/callback")
//    public Object callback() throws Exception {
//        // 发现消息
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
//        return "callback";
//    }
//}

@Controller
@EnableScheduling
@SpringBootApplication
public class App {

    public static void main(String[] args) {

        String str = "";
        for(int i=0; i< 128; i++)
        {
            String temp = "'"+i+"Hz/"+i*60/2+"r/m',";
            str+=temp;
        }
        System.out.println(str);

        SpringApplication.run(App.class, args);
    }



    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @MessageMapping("/send")
    @SendTo("/topic/send")
    public SocketMessage send(SocketMessage message) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = df.format(new Date());
        return message;
    }

    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/callback")
    public Object callback() throws Exception {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
        return "callback";
    }
}