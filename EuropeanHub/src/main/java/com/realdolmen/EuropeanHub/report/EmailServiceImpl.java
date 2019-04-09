/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.report;

import java.io.File;
import java.net.ConnectException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;

   // @PostConstruct
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException, ConnectException {
            MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

          FileSystemResource file
     = new FileSystemResource(new File(pathToAttachment));
   helper.addAttachment("aanrijdingsformulier.pdf", file);
         
        emailSender.send(message);        
    }

}
