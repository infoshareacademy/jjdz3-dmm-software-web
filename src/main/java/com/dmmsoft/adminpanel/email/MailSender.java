package com.dmmsoft.adminpanel.email;

import com.dmmsoft.adminpanel.email.confing.JsonSerializer;
import com.dmmsoft.adminpanel.email.confing.PathFileReader;
import com.dmmsoft.adminpanel.email.confing.SmtpProperties;
import com.dmmsoft.adminpanel.trigger.ITriggerable;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.mail.*;

/**
 * Created by milo on 07.07.17.
 */
public class MailSender implements ITriggerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class);
    private final String MAIL_SMTP_HOST = "mail.smtp.host";
    private final String MAIL_SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketFactory.port";
    private final String MAIL_SMTP_PORT = "mail.smtp.port";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final String TARGET_EMAIL = "targetEmail";
    private final String SMTP_CONFIG_FILE_NAME = "smtpconfig.json";
    private Properties emailProps = new Properties();

    @Override
    public void executeAction() {
        try {
            this.sendEmail();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendEmail() throws IOException {

        this.getEmailAccountProperties();

        Session session = Session.getDefaultInstance(emailProps,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailProps
                                .getProperty(EMAIL), emailProps
                                .getProperty(PASSWORD));
                    }
                });
        try {
            LOGGER.info("before compose message");

            Transport.send(new MessageGenerator().composeMessage(session, emailProps));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties getEmailAccountProperties() throws IOException {

        SmtpProperties smtpProperties = this.getSMTPProps();

        emailProps.put(EMAIL, smtpProperties.getEmail());
        emailProps.put(PASSWORD, smtpProperties.getPassword());
        emailProps.put(TARGET_EMAIL, smtpProperties.getTargetEmail());
        emailProps.put(MAIL_SMTP_HOST, smtpProperties.getSmtpHost());
        emailProps.put(MAIL_SMTP_SOCKETFACTORY_PORT, smtpProperties.getSmtpPort());
        emailProps.put(MAIL_SMTP_PORT, smtpProperties.getSmtpPort());
        emailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        emailProps.put("mail.smtp.auth", "true");
        return emailProps;
    }

    private SmtpProperties getSMTPProps() throws IOException {
        String smtpConfigFilePath = new AppConfigurationProvider()
                .getConfiguration()
                .getSmtpConfigFilePath();

        Path path = Paths.get(smtpConfigFilePath, SMTP_CONFIG_FILE_NAME);
        String content = new PathFileReader(path).getFileAsString();
        return new JsonSerializer(content).getProperties();
    }

}