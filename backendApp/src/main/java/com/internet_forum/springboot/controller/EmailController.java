package com.internet_forum.springboot.controller;


import com.internet_forum.springboot.model.EmailRequest;
import com.internet_forum.springboot.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return "Email sent successfully";
    }
}
