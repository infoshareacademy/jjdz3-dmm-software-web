package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.dmmsoft.analyzer.analysis.wrapper.WrappingService;
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
import java.util.*;

import static com.dmmsoft.ConstantsProvider.*;


/**
 * Created by milo on 24.05.17.
 */

@WebServlet(urlPatterns = "/auth/userview/favouriteindicatorcomparator")
public class FavouriteIndicatorComparatorServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FavouriteIndicatorComparatorServlet.class);

    @Inject
    IUserService userService;
    @Inject
    IFavouriteService favouriteService;
    @Inject
    WrappingService wrappingService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<AnalysisComparisonContainer> comparisonContainers =
                favouriteService.getAllUserFavouriteAnalysisContainers(((User) req.getSession()
                        .getAttribute(AUTH_USER)).getId());

        req.setAttribute(CONTENT_WRAPPER_COLLECTION, wrappingService.getWrapperedContentList(comparisonContainers));
        req.getRequestDispatcher("../userview/favouriteIndicatorComparator.jsp").forward(req, resp);

        LOGGER.info("Analysis comparison containers list size: {}", comparisonContainers.size());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      //  TODO favourites update

    }
}
