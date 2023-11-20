package com.marcusvinicius.emailservice.Infra.Ses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.marcusvinicius.emailservice.Adapters.EmailSenderGateway;
import com.marcusvinicius.emailservice.Core.Exceptions.EmailServiceException;

@Service
public class SesEmailSender implements EmailSenderGateway{

    
    private final AmazonSimpleEmailService aws;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService aws){
        this.aws = aws;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
            .withSource(to)
            .withDestination(new Destination().withToAddresses(to))
            .withMessage(new Message()
                    .withSubject(new Content(subject))
                    .withBody(new Body().withText(new Content(body))))
            ;
            try {
                this.aws.sendEmail(request);
            }catch(AmazonServiceException ex){
                throw new EmailServiceException("Failure while sending email", ex);
            }

    }


    
}
