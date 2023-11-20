package com.marcusvinicius.emailservice.Core;

public record EmailRequest(String to, String subject, String body) {
    
}
