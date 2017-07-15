package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.dmmsoft.analyzer.analysis.wrapper.WrappingService;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.user.IUserService;
import com.dmmsoft.user.User;
import com.dmmsoft.user.report.IUserActivityService;
import com.dmmsoft.user.report.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.dmmsoft.ConstantsProvider.*;

/**
 * Created by milo on 24.05.17.
 */

@WebServlet(urlPatterns = "/auth/userview/favouriteindicatorcomparator")
public class FavouriteIndicatorComparatorServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FavouriteIndicatorComparatorServlet.class);
    private final String USER_ACTIVITY_FAVOURITES_DISPLAY = "User displayed Favourites: IndicatorComparator analysis.";

    @Inject
    IUserService userService;
    @Inject
    IFavouriteService favouriteService;
    @Inject
    WrappingService wrappingService;
    @Inject
    IUserActivityService userActivityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = ((User) req.getSession().getAttribute(AUTH_USER));

            List<AnalysisComparisonContainer> comparisonContainers =
                    favouriteService.getAllUserFavouriteAnalysisContainers(user.getId());

            userActivityService.saveActivity(new UserActivity(user.getLogin(),
                    USER_ACTIVITY_FAVOURITES_DISPLAY,
                    req.getSession().getId()));

            req.setAttribute(CONTENT_WRAPPER_COLLECTION, wrappingService.getWrapperedContentList(comparisonContainers));
            req.getRequestDispatcher("../userview/favouriteIndicatorComparator.jsp").forward(req, resp);

            LOGGER.info("Analysis comparison containers list size: {}", comparisonContainers.size());
        } catch (NoDataForCriteria ex) {
            LOGGER.error("Failed to generate favourites", ex.getMessage());
            req.getRequestDispatcher("../userview/favouriteIndicatorComparator.jsp").forward(req, resp);
        }
    }

        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            //  TODO favourites update

        }
    }
