package com.dmmsoft.adminpanel.emailsender;

import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


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
public class MailSender implements ITrigerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class);
    private final String MAIL_SMTP_HOST = "mail.smtp.host";
    private final String MAIL_SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketFactory.port";
    private final String MAIL_SMTP_PORT = "mail.smtp.port";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final String TARGET_EMAIL = "targetEmail";
    private final String SMTP_CONFIG_FILE_PATH = "/home/milo/dmmfinance/";
    private final String SMTP_CONFIG_FILE_NAME = "smtpconfig.json";

    @Override
    public void executeAction() {
        try {
            this.sendEmail();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendEmail() throws IOException {

        Properties properties = this.getEmailAccountProperties();

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties
                                .getProperty(EMAIL), properties
                                .getProperty(PASSWORD));
                    }
                });
        try {
            Transport.send(this.composeMessage(session, properties));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message composeMessage(Session session, Properties properties) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty(EMAIL)));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(properties.getProperty(TARGET_EMAIL)));
            message.setSubject("Testing Subject");
            message.setText("Test Content");
            return message;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties getEmailAccountProperties() throws IOException {

        String smtpConfigFilePath = new AppConfigurationProvider()
                .getConfiguration()
                .getSmtpConfigFilePath();

        Path path = Paths.get(smtpConfigFilePath, SMTP_CONFIG_FILE_NAME);
        String content = new PathFileReader(path).getFileAsString();
        SmtpProperties smtpProperties = new UniversalJsonMapper(content).getProperties();

        LOGGER.info("CONTENT {}", content);
        LOGGER.info("smtp property {}", smtpProperties.getEmail());
        LOGGER.info("smtp property {}", smtpProperties.getTargetEmail());

        Properties props = new Properties();
        props.put(EMAIL, smtpProperties.getEmail());
        props.put(PASSWORD, smtpProperties.getPassword());
        props.put(TARGET_EMAIL, smtpProperties.getTargetEmail());
        props.put(MAIL_SMTP_HOST, smtpProperties.getSmtpHost());
        props.put(MAIL_SMTP_SOCKETFACTORY_PORT, smtpProperties.getSmtpPort());
        props.put(MAIL_SMTP_PORT, smtpProperties.getSmtpPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        return props;
    }


}