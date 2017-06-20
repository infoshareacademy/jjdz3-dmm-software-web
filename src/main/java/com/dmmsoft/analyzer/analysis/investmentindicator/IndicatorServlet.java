package com.dmmsoft.analyzer.analysis.investmentindicator;

/**
 * Created by milo on 15.04.17.
 */

import com.dmmsoft.app.analyzer.analyses.indicator.Indicator;
import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorCriteria;
import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorResult;

import com.dmmsoft.container.IModelContainerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dmmsoft.ConstantsProvider.*;

@WebServlet(urlPatterns = "/auth/userview/comparator")
public class IndicatorServlet extends HttpServlet {

    @Inject
    IModelContainerService container;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("../userview/comparator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nameA = req.getParameter(INVESTMENT_NAME_A);
        String nameB = req.getParameter(INVESTMENT_NAME_B);

        IndicatorResult resultA = new Indicator().getResult(container.getInvestments()
                , new IndicatorCriteria(nameA));

        IndicatorResult  resultB = new Indicator().getResult(container.getInvestments()
                , new IndicatorCriteria(nameB));

        req.setAttribute(INVESTMENT_STAT_RESULT_A, resultA);
        req.setAttribute(INVESTMENT_STAT_RESULT_B, resultB);

        req.getRequestDispatcher("../userview/comparatorResult.jsp").forward(req, resp);

    }
}
