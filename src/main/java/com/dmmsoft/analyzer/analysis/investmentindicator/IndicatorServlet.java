package com.dmmsoft.analyzer.analysis.investmentindicator;

/**
 * Created by milo on 15.04.17.
 */

import com.dmmsoft.analyzer.analysis.wrapper.AnalysisContent;
import com.dmmsoft.analyzer.analysis.wrapper.ComparisonContentWrapper;
import com.dmmsoft.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.indicator.Indicator;
import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorCriteria;
import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorResult;

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
import java.util.*;

import static com.dmmsoft.ConstantsProvider.*;

@WebServlet(urlPatterns = "/auth/userview/comparator")
public class IndicatorServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorServlet.class);

    @Inject
    IModelContainerService container;

    @Inject
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("../userview/comparator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String nameA = req.getParameter(INVESTMENT_NAME_A);
            String nameB = req.getParameter(INVESTMENT_NAME_B);
            boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;
            String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);

            List<String> names = new ArrayList<>();
            names.add(nameA);
            names.add(nameB);

            List<PersistedIndicatorCriteria> criteriaList = new ArrayList<>();
            ComparisonContentWrapper wrapper = new ComparisonContentWrapper();

            wrapper.setUserCustomName(userCustomName);

            for (String item : names) {
                PersistedIndicatorCriteria criteria = new PersistedIndicatorCriteria(
                        item, userCustomName, isFavouriteChecked);

                IndicatorResult result = new Indicator(container.getMainContainer()
                        , new IndicatorCriteria(item)).getResult();


                criteriaList.add(criteria);
                wrapper.getAnanysisContentList().add(new AnalysisContent(criteria, result));
            }

            AnalysisComparisonContainer comparisonContainer =
                    new AnalysisComparisonContainer(isFavouriteChecked
                            , userCustomName, new ArrayList<>(criteriaList));

            User user = (User) req.getSession().getAttribute(AUTH_USER);
            user.getComparisonContainers().add(comparisonContainer);
            userService.update(user);
            req.setAttribute(CONTENT_WRAPPER, wrapper);
            req.getRequestDispatcher("../userview/comparatorResult.jsp").forward(req, resp);

        } catch (NoDataForCriteria exception) {

            req.getRequestDispatcher("../userview/comparator.jsp").forward(req, resp);
            LOGGER.error("Failed to calculate Investment indicators.{}", exception.getMessage());
        }
    }
}
