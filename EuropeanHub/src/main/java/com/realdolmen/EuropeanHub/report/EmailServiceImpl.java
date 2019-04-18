/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Base64;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;

    String username = System.getenv("USERNAME");

    // @PostConstruct
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment, String[] pictures) throws MessagingException, ConnectException, FileNotFoundException, IOException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("aanrijdingsformulier.pdf", file);
        for (int i = 0; i < pictures.length; i++) {
            byte[] bytes = Base64.getMimeDecoder().decode(pictures[i]);
            try (FileOutputStream fos = new FileOutputStream("C:\\Users\\" + username + "\\Pictures\\picture" + i + ".jpg")) {
                fos.write(bytes);
            }
            File filePicture = new File("C:\\Users\\" + username + "\\Pictures\\picture" + i + ".jpg");
            helper.addAttachment("picture" + i + ".jpg", filePicture);
        }

        emailSender.send(message);
    }

}
