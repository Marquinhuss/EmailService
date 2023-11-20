package com.marcusvinicius.emailservice.Controllers;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcusvinicius.emailservice.Application.EmailSenderService;
import com.marcusvinicius.emailservice.Core.EmailRequest;
import com.marcusvinicius.emailservice.Core.Exceptions.EmailServiceException;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
    
    private final EmailSenderService emailService;

    public EmailSenderController(EmailSenderService emailService){
        this.emailService = emailService;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
        try{
            this.emailService.sendEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("Email sended sucessfuly");
        }catch(EmailServiceException ex){
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error while sending email");
        }
    }
}