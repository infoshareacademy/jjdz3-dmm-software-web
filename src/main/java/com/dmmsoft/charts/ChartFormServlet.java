package com.dmmsoft.charts;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.trend.QuotationSeriesCriteria;
import com.dmmsoft.container.IModelContainerService;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.dmmsoft.ConstantsProvider.*;

/**
 * Created by milo on 10.07.17.
 */

@WebServlet(urlPatterns = "/auth/userview/chart")
public class ChartFormServlet extends HttpServlet {

    @Inject
    private IModelContainerService container;


    private static final Logger LOGGER = LoggerFactory.getLogger(ChartFormServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nameA = req.getParameter(INVESTMENT_NAME_A);
            String nameB = req.getParameter(INVESTMENT_NAME_B);
            String startDate = req.getParameter(START_DATE);
            String endDate = req.getParameter(END_DATE);


            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

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
            LOGGER.error("Failed to render chart.{}{}", ex.getMessage(), ex.getStackTrace());
        }
    }
}

