package com.marcusvinicius.emailservice.Core;

public interface EmailSenderUseCase {
    
    void sendEmail(String to, String subject, String body);
}
