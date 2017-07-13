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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JFreeChart chart = (JFreeChart) req.getSession().getAttribute("chart");
        OutputStream out = resp.getOutputStream();
        resp.setContentType("image/png");

        ChartUtilities.writeChartAsPNG(out, chart, 800, 300);// write data to output stream
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

            //String userTitle = req.getParameter("chartTitle");
            // TODO criteria Form

            String name = "CHF";

            DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
            LocalDate startDATE = LocalDate.parse("20141201", formatter);
            LocalDate endDATE = LocalDate.now();

            QuotationSeriesCriteria criteria = new QuotationSeriesCriteria(name, startDATE, endDATE);

            ChartGenerator chartGenerator = new ChartGenerator(container, criteria);
            JFreeChart chart = chartGenerator.renderChart();
            req.getSession().setAttribute("chart", chart);

            req.getRequestDispatcher("../userview/chartResult.jsp").forward(req, resp);
        } catch (NoDataForCriteria ex) {
            LOGGER.error("Failed to render chart.");
        }
    }
}

