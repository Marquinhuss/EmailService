package com.marcusvinicius.emailservice.Adapters;

public interface EmailSenderGateway {
    
    void sendEmail(String to, String subject, String body);
}
