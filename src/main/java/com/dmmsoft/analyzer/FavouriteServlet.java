package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.InvestmentRevenue.ContentWrapper;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;
import com.dmmsoft.container.IModelContainerService;
import com.dmmsoft.analyzer.analysis.InvestmentRevenue.PersistedInvestmentRevenueCriteria;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 24.05.17.
 */

@WebServlet(urlPatterns = "/auth/userview/favourite")
public class FavouriteServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FavouriteServlet.class);
    private static final String CRITERIA_MODERATION_MESSAGE = "Note! Your input data does not correspond to current investment history of quotations. \n" +
            "    For analysis system used nearest possible quoutations acording to dates from submitted form.\n" +
            "    User criteria moderated by system are listed in User input moderation report.";

    @Inject
    IModelContainerService container;

    @Inject
    IFavouriteService favouriteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("authenticatedUser");
        List<PersistedInvestmentRevenueCriteria> criteriaList = favouriteService.getAllUserFavoutiteCriteria(user.getId());

        List<ContentWrapper> contentWrappers =new ArrayList<>();

        LOGGER.info("Current user Favourites to display {}",criteriaList.size());

        try {
            for (PersistedInvestmentRevenueCriteria criteria : criteriaList) {
                InvestmentRevenueResult result = (new InvestmentRevenue(container.getMainContainer(),
                        criteria.getEqualEquivalent(criteria))).getResult();

                ContentWrapper wrapper = new ContentWrapper();
                wrapper.setCriteria(criteria);
                wrapper.setResult(result);
                if (result.getFinallyEvaluatedInput().getModifiedBySuggester()) {
                    wrapper.setMessage(CRITERIA_MODERATION_MESSAGE);
                }

                LOGGER.info(result.getCapitalRevenueDeltaPrecentValue().toString());
                LOGGER.info(result.getCapitalRevenueValue().toString());
                contentWrappers.add(wrapper);
            }
            LOGGER.info(user.getLogin());

            req.setAttribute("contentWrappers", contentWrappers);
            req.getRequestDispatcher("../userview/favourite.jsp").forward(req,resp);

        } catch (NoDataForCriteria ex) {

            LOGGER.error("error" + ex.getMessage());
            LOGGER.info(user.getLogin());
        }


    }
}
