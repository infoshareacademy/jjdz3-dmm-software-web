package com.dmmsoft.manualtest;

import com.dmmsoft.analyzer.analysis.LocalInvestmentRevenueCriteria;
import com.dmmsoft.user.IUserService;
import com.dmmsoft.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by milo on 22.05.17.
 */


@WebServlet(urlPatterns = "/manualtest")
public class ManualTestServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManualTestServlet.class);

    @Inject
    IUserService userstorage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // initialization of this servlet populates db with test data
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            BigDecimal capital = new BigDecimal("120000");
            LocalDate BUY_DATE = LocalDate.parse("2009-09-12", formatter);
            LocalDate SELL_DATE = LocalDate.parse("2017-04-04", formatter);

            LocalInvestmentRevenueCriteria input1 = new LocalInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "CHF", true);
            LocalInvestmentRevenueCriteria input2 = new LocalInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "USD", true);
            LocalInvestmentRevenueCriteria input3 = new LocalInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "AUD", true);
            LocalInvestmentRevenueCriteria input4 = new LocalInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "AIP001", true);

            User user1 = new User();
            user1.setLogin("user1@user1.com");
            user1.setAdmin(false);

            User user2 = new User();
            user2.setLogin("user2@user2.com");
            user2.setAdmin(false);

            User user3 = new User();
            user3.setLogin("user3@user3.com");
            user3.setAdmin(false);

            User admin = new User();
            admin.setLogin("admin@admin.com");
            admin.setAdmin(true);


            user1.getFavourites().add(input1);
            user1.getFavourites().add(input2);
            user1.getFavourites().add(input3);
            user2.getFavourites().add(input4);

            userstorage.add(user1);  // 3 favourite criteria
            userstorage.add(user2);  // 1 favourite criteria
            userstorage.add(user3);  // no favourite criteria
            userstorage.add(admin);  // no favourite criteria

            LOGGER.info("Manual test servlet initialized.");


        } catch (Exception ex) {
            LOGGER.error("Manual tests servlet inintialization failure - possible cause: database error. " + ex.getMessage());
            throw new ServletException(ex);

        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // manual tests message

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("DMM-FINANCE WEB - MANUAL TESTS </br>");
        out.println("</br>");
        out.println("This servlet initialization populates db with test data </br>");
        out.println("http://localhost:8080/financial-app/manualtest </br>");
        out.println("</br>");
        out.println("To start manual testing start with: </br>");
        out.println("http://localhost:8080/financial-app/login </br>");
    }

}
