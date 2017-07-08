package com.dmmsoft.adminpanel.email;

import com.dmmsoft.adminpanel.report.CSVGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by milo on 08.07.17.
 */

public class MessageGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageGenerator.class);
    private final String EMAIL = "email";
    private final String TARGET_EMAIL = "targetEmail";


    public Message composeMessage(Session session, Properties properties) throws IOException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty(EMAIL)));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(properties.getProperty(TARGET_EMAIL)));

            message.setSubject("Testing Subject");
            message.setText("Test Content");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(this.generateEmailAttachment());

            message.setContent(multipart);

            return message;

        } catch (MessagingException e) {
            LOGGER.error("Failed to send email:{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private BodyPart generateEmailAttachment() throws MessagingException, IOException {

        List<String> lines = new CSVGenerator().generateCSVLines();

        BodyPart messageBodyPart = new MimeBodyPart();
        String filename = "tmp/report.csv";

        FileWriter fw = new FileWriter(filename);
        for (String item : lines) {
            fw.write("add a line\n" + item);  //appends the string to the file
        }
        fw.close();

        File tempFilePath = new File("tmp/report.csv");
        String absolutePath = tempFilePath.getAbsolutePath();
        LOGGER.info("absolute file path {}", absolutePath);

        DataSource source = new FileDataSource(absolutePath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("actual_report.csv");
        return messageBodyPart;
    }

}



