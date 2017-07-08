package com.dmmsoft.adminpanel.email;

import com.dmmsoft.adminpanel.report.ReportComponents;
import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by milo on 08.07.17.
 */

public class MessageGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageGenerator.class);
    private final String EMAIL = "email";
    private final String TARGET_EMAIL = "targetEmail";
    private final String TMP_FILE_NAME = "tmp/reportComponents.csv";
    private ReportComponents reportComponents;

    public MessageGenerator(ReportComponents reportComponents) {
        this.reportComponents = reportComponents;
    }

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

        List<String> lines = this.generateCSVLines(reportComponents);

        BodyPart messageBodyPart = new MimeBodyPart();

        FileWriter fw = new FileWriter(TMP_FILE_NAME);
        for (String item : lines) {
            fw.write(item);
        }
        fw.close();

        File tempFilePath = new File(TMP_FILE_NAME);
        String absolutePath = tempFilePath.getAbsolutePath();
        LOGGER.info("absolute file path {}", absolutePath);

        DataSource source = new FileDataSource(absolutePath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(TMP_FILE_NAME);
        return messageBodyPart;
    }

    private List<String> generateCSVLines(ReportComponents reportComponents) {

        List<String> lines = new ArrayList<>();

        List<PersistedInvestmentRevenueCriteria> criteria = reportComponents
                .getFavouriteService()
                .getAllRevenueCriteria();

        lines.add("InvestmentName,InvestedCapital,BuyDate,SellDate"
                .concat(System.getProperty("line.separator")));

        for (InvestmentRevenueCriteria item : criteria) {

            String separator = ",";
            StringBuilder sb = new StringBuilder();

            sb.append(item.getInvestmentName()).append(separator);
            sb.append(item.getInvestedCapital()).append(separator);
            sb.append(item.getBuyDate().toString()).append(separator);
            sb.append(item.getSellDate().toString());
            sb.append(System.getProperty("line.separator"));
            lines.add(sb.toString());
            LOGGER.info("Adding Line to CSV: {}", sb.toString());
        }
        return lines;
    }

}



