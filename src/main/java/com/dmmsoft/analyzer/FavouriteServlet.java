package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.InvestmentRevenue.ContentWrapper;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;
import com.dmmsoft.container.IModelContainerService;
import com.dmmsoft.analyzer.analysis.InvestmentRevenue.PersistedInvestmentRevenueCriteria;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 24.05.17.
 */

@WebServlet(urlPatterns = "/auth/userview/favourite")
public class FavouriteServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FavouriteServlet.class);
    private final String CRITERIA_MODERATION_MESSAGE = "Note! Your input data does not correspond to current investment history of quotations. \n" +
            "    For analysis system used nearest possible quoutations acording to dates from submitted form.\n" +
            "    User criteria moderated by system are listed in User input moderation report.";

    private final String AUTH_USER = "authenticatedUser";
    private final String CONTENT_WRAPPER_COLLECTION ="contentWrappers";
    private final String USER_FAVOURITE_CUSTOM_NAME = "userCustomName";
    private final String CRITERIA_ID =  "criteriaId";



    @Inject
    IModelContainerService container;
    @Inject
    IUserService userService;
    @Inject
    IFavouriteService favouriteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(AUTH_USER);

        List<PersistedInvestmentRevenueCriteria> criteriaList = favouriteService.getAllUserFavoutiteCriteria(user.getId());


        LOGGER.info("Current user Favourites to display {}", criteriaList.size());

        List<ContentWrapper> contentWrappers = new ArrayList<>();

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

            req.setAttribute(CONTENT_WRAPPER_COLLECTION, contentWrappers);
            req.getRequestDispatcher("../userview/favourite.jsp").forward(req, resp);

        } catch (NoDataForCriteria ex) {

            LOGGER.error("error" + ex.getMessage());
            LOGGER.info(user.getLogin());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String criteriaId = req.getParameter(CRITERIA_ID);
        String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);

        User dbUser = userService.get(((User) req.getSession().getAttribute(AUTH_USER)).getId());

        List<PersistedInvestmentRevenueCriteria> criteras = dbUser.getFavourites();


        if (criteras!=null && criteras.size()!=0) {
            LOGGER.info("criteria list size{}", criteras.size());

            int i = 0;
            for (PersistedInvestmentRevenueCriteria c : criteras) {
                if (c.getId()== Long.parseLong(criteriaId)) {
                    LOGGER.info("criteria array Id:{}", i);
                    break;
                }
                i++;
            }

            if (req.getParameter("updateAction") != null) {

                criteras.get(i).setUserCustomName(userCustomName);

            } else if (req.getParameter("deleteAction") != null) {
                criteras.get(i).setFavourite(false);

                LOGGER.info("Analysis removed from favourites - deleteAction");
            }
            dbUser.setFavourites(criteras);
            userService.update(dbUser);
        }
       resp.sendRedirect("../userview/favourite");
    }




}
