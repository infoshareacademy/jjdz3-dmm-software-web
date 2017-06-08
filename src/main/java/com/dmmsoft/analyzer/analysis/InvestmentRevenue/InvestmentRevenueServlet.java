package com.dmmsoft.analyzer.analysis.InvestmentRevenue;

import com.dmmsoft.ConstantsProvider;
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
    private final String TEST_BUY_DATE = "2009-09-10";
    private final String TEST_SELL_DATE = "2017-04-02";

    private ContentWrapper wrapper = new ContentWrapper();
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
            //TODO form validation
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantsProvider.DATE_PATTERN);
            String investmentName = req.getParameter(ConstantsProvider.INVESTMENT_NAME);
            String capital = req.getParameter(ConstantsProvider.CAPITAL);
            String buyDate = req.getParameter(ConstantsProvider.BUY_DATE);
            String sellDate = req.getParameter(ConstantsProvider.SELL_DATE);
            String userCustomName = req.getParameter(ConstantsProvider.USER_FAVOURITE_CUSTOM_NAME);


            Boolean isFavouriteChecked = req.getParameter(ConstantsProvider.IS_FAVOURITE) != null;

            //TODO remove default values for manual tests
            if (buyDate == null || buyDate.isEmpty())
                buyDate = TEST_BUY_DATE;
            if (sellDate == null || sellDate.isEmpty())
                sellDate = TEST_SELL_DATE;

            BigDecimal investmentCapital = new BigDecimal(capital);
            LocalDate investmentBuyDate = LocalDate.parse(buyDate, formatter);
            LocalDate investmentSellDate = LocalDate.parse(sellDate, formatter);

            InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria(investmentCapital
                    , investmentBuyDate
                    , investmentSellDate
                    , investmentName
                    , isFavouriteChecked);

            InvestmentRevenueResult result = (new InvestmentRevenue(container.getMainContainer(), criteria))
                    .getResult();

            User dbUser = userService.get(((User) req.getSession()
                    .getAttribute(ConstantsProvider.AUTH_USER)).getId());

            dbUser.getFavourites().add(new PersistedInvestmentRevenueCriteria()
                    .getCriteria(criteria, userCustomName));
            userService.update(dbUser);

            req.setAttribute(ConstantsProvider.CONTENT_WRAPPER, this.getContent(criteria, result));
            req.getRequestDispatcher("../userview/investmentRevenue.jsp").forward(req, resp);

            LOGGER.info("Criteria Submitted by user Id:{}, login:{}", dbUser.getId(), dbUser.getLogin());

        } catch (NoDataForCriteria ex) {
            req.setAttribute(ConstantsProvider.APP_MESSAGE, ConstantsProvider.NO_DATA_FOR_CRITERIA_MESSAGE);
            req.getRequestDispatcher("../userview/investmentRevenue.jsp").forward(req, resp);
            LOGGER.warn(ex.getMessage());
        }
    }

    private ContentWrapper getContent(InvestmentRevenueCriteria criteria, InvestmentRevenueResult result) {
        wrapper.setCriteria(criteria);
        wrapper.setResult(result);
        if (result.getFinallyEvaluatedInput().getModifiedBySuggester()) {
            wrapper.setMessage(ConstantsProvider.CRITERIA_MODERATION_MESSAGE);
        }
        return wrapper;
    }

}
