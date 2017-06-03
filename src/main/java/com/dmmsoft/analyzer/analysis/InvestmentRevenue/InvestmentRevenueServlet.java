package com.dmmsoft.analyzer.analysis.InvestmentRevenue;

import com.dmmsoft.analyzer.IFavouriteService;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;
import com.dmmsoft.container.IModelContainerService;
import com.dmmsoft.user.IUserService;
import com.dmmsoft.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by milo on 12.05.17.
 */

@WebServlet(urlPatterns = "/auth/userview/investmentrevenue")
public class InvestmentRevenueServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentRevenueServlet.class);
    private static final String CRITERIA_MODERATION_MESSAGE = "Note! Your input data does not correspond to current investment history of quotations. \n" +
            "    For analysis system used nearest possible quoutations acording to dates from submitted form.\n" +
            "    User criteria moderated by systen are listed in User input moderation report.";

    private static final String NO_DATA_FOR_CRITERIA_MESSAGE = "Error! No data for current criteria!";


    @Inject
    private IModelContainerService container;
    @Inject
    private IFavouriteService favouriteService;

    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("../userview/investmentRevenue.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String InvestmentName = req.getParameter("investmenName");
            String sCapital = req.getParameter("capital");
            String SBUY_DATE = req.getParameter("buyDate");
            String SSELL_DATE = req.getParameter("sellDate");
            Boolean isFavouriteChecked = req.getParameter("isFavourite") != null;

            // default form test values

            if (SBUY_DATE == null || SBUY_DATE.toString().isEmpty())
                SBUY_DATE = "2009-09-10";

            if (SSELL_DATE == null || SSELL_DATE.toString().isEmpty())
                SSELL_DATE = "2017-04-02";

            if (sCapital == null || sCapital.toString().isEmpty())
                sCapital = "120000";

            if (InvestmentName == null || InvestmentName.isEmpty())
                InvestmentName = "CHF";

            BigDecimal capital = new BigDecimal(sCapital);
            LocalDate BUY_DATE = LocalDate.parse(SBUY_DATE, formatter);
            LocalDate SELL_DATE = LocalDate.parse(SSELL_DATE, formatter);

            InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(capital
                    ,BUY_DATE
                    ,SELL_DATE
                    ,InvestmentName
                    ,isFavouriteChecked);

            InvestmentRevenueResult result = (new InvestmentRevenue(container.getMainContainer(),criteria))
                    .getResult();

            User user = (User)req.getSession().getAttribute("authenticatedUser");
            user.getFavourites().add(new PersistedInvestmentRevenueCriteria(criteria));
            userService.update(user);

            DisplayWrapper wrapper = new DisplayWrapper();
            wrapper.setCriteria(criteria);
            wrapper.setResult(result);
            wrapper.setMessage(CRITERIA_MODERATION_MESSAGE);

            req.setAttribute("displayWrapper", wrapper);

            if (result.getFinallyEvaluatedInput().getModifiedBySuggester() == true) {
                req.setAttribute("message", CRITERIA_MODERATION_MESSAGE);
            }
            req.getRequestDispatcher("../userview/investmentRevenue.jsp").forward(req, resp);
            LOGGER.info("Criteria Submitted by user Id:{}, login:{}", user.getId(), user.getLogin());

        } catch (NoDataForCriteria ex) {
            req.setAttribute("message", NO_DATA_FOR_CRITERIA_MESSAGE);
            req.getRequestDispatcher("../userview/investmentRevenue.jsp").forward(req, resp);
            LOGGER.warn(ex.getMessage());
        }

    }




}
