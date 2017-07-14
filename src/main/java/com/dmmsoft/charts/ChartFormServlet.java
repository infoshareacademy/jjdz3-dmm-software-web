package com.dmmsoft.charts;

import com.dmmsoft.adminpanel.AppSettingsServlet;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.trend.QuotationSeriesCriteria;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.container.IModelContainerService;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.dmmsoft.ConstantsProvider.*;

/**
 * Created by milo on 10.07.17.
 */

@WebServlet(urlPatterns = "/auth/userview/chart")
public class ChartServlet extends HttpServlet {

    @Inject
    private IModelContainerService container;


    private static final Logger LOGGER = LoggerFactory.getLogger(ChartServlet.class);

/*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<JFreeChart> charts = (List<JFreeChart>) req.getSession().getAttribute("charts");

        JFreeChart chartA = charts.get(0);
        JFreeChart chartB = charts.get(1);

        req.getSession().setAttribute("chartA", chartA);
        req.getSession().setAttribute("chartB", chartB);

        req.getRequestDispatcher("../auth/userview/chartA").forward(req, resp);
    }
*/


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nameA = req.getParameter(INVESTMENT_NAME_A);
            String nameB = req.getParameter(INVESTMENT_NAME_B);
            String endDate = req.getParameter("endDate");
            String startDate = req.getParameter("startDate");

            /* // TODO Chart Comparison persistence
            boolean isFavouriteChecked = req.getParameter(IS_FAVOURITE) != null;
            String userCustomName = req.getParameter(USER_FAVOURITE_CUSTOM_NAME);

            List<String> names = new ArrayList<>();
            names.add(nameA);
            names.add(nameB);*/

            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            List<JFreeChart> charts = new ArrayList<>();

            LocalDate startDATE = LocalDate.parse(startDate, formatter);
            LocalDate endDATE = LocalDate.parse(endDate, formatter);

            QuotationSeriesCriteria criteriaA = new QuotationSeriesCriteria(nameA, startDATE, endDATE);
            QuotationSeriesCriteria criteriaB = new QuotationSeriesCriteria(nameB, startDATE, endDATE);

            ChartGenerator chartGeneratorA = new ChartGenerator(container, criteriaA);
            JFreeChart chartA = chartGeneratorA.renderChart();
            ChartGenerator chartGeneratorB = new ChartGenerator(container, criteriaB);
            JFreeChart chartB = chartGeneratorB.renderChart();

            req.getSession().setAttribute("chartA", chartA);
            req.getSession().setAttribute("chartB", chartB);

            req.getRequestDispatcher("../userview/chartResult.jsp").forward(req, resp);
        } catch (NoDataForCriteria ex) {
            LOGGER.error("Failed to render chart.");
        }
    }
}

