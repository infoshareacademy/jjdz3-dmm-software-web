package com.dmmsoft.adminpanel.emailsender;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by milo on 07.07.17.
 */
public class MailSender {

    public void sendEmail(String content) {

        Session session = Session.getDefaultInstance(this.getEmailAccountProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("xxxxxxxxxxx","xxxxxxxxxx");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("xxxxxxxxxxxxx"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("xxxxxxxxxxx"));
            message.setSubject("Testing Subject");
            message.setText("Test Content");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties getEmailAccountProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }
}