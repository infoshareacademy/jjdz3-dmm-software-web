package com.dmmsoft.charts;

import com.dmmsoft.adminpanel.AppSettingsServlet;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
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

        // application reads chart object from session, saves chart as png, and serves as outputstream

        JFreeChart chart = (JFreeChart) req.getSession().getAttribute("chart");

        OutputStream out = resp.getOutputStream(); // output stream from response object
        resp.setContentType("image/png");

        ChartUtilities.writeChartAsPNG(out, chart, 400, 300);// write data to output stream
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // application reads data from form, evaluates data, saves result as chart to session Object

        try{
        String userTitle = req.getParameter("chartTitle");

        final ChartGenerator chartGenerator = new ChartGenerator("test chart", mainContainer);
        JFreeChart chart = chartGenerator.renderChart(); // generate chart

        req.getSession().setAttribute("chart", chart);

        req.getRequestDispatcher("../userview/chart.jsp").forward(req, resp);
    }
    catch (NoDataForCriteria ex){
        LOGGER.error("Failed to render chart.");
        }
    }
}

