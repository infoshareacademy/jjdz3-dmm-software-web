package com.dmmsoft.manualtest;

import com.dmmsoft.analyzer.analysis.PersistedInvestmentRevenueCriteria;
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
    private IUserService userStorage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // initialization of this servlet populates db with data for manual testing
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            BigDecimal capital = new BigDecimal("120000");
            LocalDate BUY_DATE = LocalDate.parse("2009-09-12", formatter);
            LocalDate SELL_DATE = LocalDate.parse("2017-04-04", formatter);

            PersistedInvestmentRevenueCriteria criteriaCHF = new PersistedInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "CHF", true);
            PersistedInvestmentRevenueCriteria criteriaUSD = new PersistedInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "USD", true);
            PersistedInvestmentRevenueCriteria criteriaAUD = new PersistedInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "AUD", true);
            PersistedInvestmentRevenueCriteria criteriaAIP = new PersistedInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "AIP001", true);

            User userWithMultipleCriteria = new User();
            userWithMultipleCriteria.setLogin("user1@user1.com");
            userWithMultipleCriteria.setAdmin(false);

            User userWithOneCriteria = new User();
            userWithOneCriteria.setLogin("user2@user2.com");
            userWithOneCriteria.setAdmin(false);

            User userWithNoCriteria = new User();
            userWithNoCriteria.setLogin("user3@user3.com");
            userWithNoCriteria.setAdmin(false);

            User admin = new User();
            admin.setLogin("admin@admin.com");
            admin.setAdmin(true);


            userWithMultipleCriteria.getFavourites().add(criteriaCHF);
            userWithMultipleCriteria.getFavourites().add(criteriaUSD);
            userWithMultipleCriteria.getFavourites().add(criteriaAUD);
            userWithOneCriteria.getFavourites().add(criteriaAIP);

            userStorage.add(userWithMultipleCriteria);
            userStorage.add(userWithOneCriteria);
            userStorage.add(userWithNoCriteria);
            userStorage.add(admin);

            LOGGER.info("Manual test servlet initialized.");


        } catch (Exception ex) {
            LOGGER.error("Manual tests servlet inintialization failure - possible cause: database error.{}", ex.getMessage());
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
        out.println("To start manual testing, start with: </br>");
        out.println("http://localhost:8080/financial-app/login </br>");
        out.println("http://localhost:8080/financial-app/login.jsp </br>");
    }

}
