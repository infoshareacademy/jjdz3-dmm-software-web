package com.dmmsoft.analyzer.analysis.investmentindicator;

/**
 * Created by milo on 15.04.17.
 */

import com.dmmsoft.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.indicator.Indicator;
import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorCriteria;
import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorResult;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
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

        String nameA = req.getParameter(INVESTMENT_NAME_A);
        String nameB = req.getParameter(INVESTMENT_NAME_B);
        Boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;
        String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);

        PersistedComparatorIndicatorCriteria criteriaToCompare = new PersistedComparatorIndicatorCriteria();
        criteriaToCompare.getInvestmentNamesToCompare().add(nameA);
        criteriaToCompare.getInvestmentNamesToCompare().add(nameB);
        criteriaToCompare.setFavouriteChecked(isFavouriteChecked);
        criteriaToCompare.setUserCustomName(userCustomName);

        Set<PersistedIndicatorCriteria> criteriaSet = new LinkedHashSet<>();

        for(String item : criteriaToCompare.getInvestmentNamesToCompare()) {

            PersistedIndicatorCriteria criteria = new PersistedIndicatorCriteria();
            criteria.setInvestmentName(item);
            criteria.setUserCustomName(userCustomName);
            criteria.setFavourite(isFavouriteChecked);
            criteria.setModifiedBySuggester(false);
            criteriaSet.add(criteria);
        }

        AnalysisComparisonContainer comparisonContainer = new AnalysisComparisonContainer();
        comparisonContainer.setCriteriaSet(new ArrayList<>(criteriaSet));

        User user = (User) req.getSession().getAttribute(AUTH_USER);
        user.getFavourireIndicatorsCompareSet().add(criteriaToCompare);
        user.getFavouriteInvestmentIndicators().addAll(criteriaSet);
        user.getComparisonContainers().add(comparisonContainer);

        IndicatorResult resultA = new Indicator().getResult(container.getInvestments()
                , new IndicatorCriteria(nameA));

        IndicatorResult  resultB = new Indicator().getResult(container.getInvestments()
                , new IndicatorCriteria(nameB));

        userService.update(user);

        req.setAttribute(INVESTMENT_STAT_RESULT_A, resultA);
        req.setAttribute(INVESTMENT_STAT_RESULT_B, resultB);

        req.getRequestDispatcher("../userview/comparatorResult.jsp").forward(req, resp);

        LOGGER.debug("Number of favourite indicators: {}", user.getFavourireIndicatorsCompareSet().size());

    }
}
