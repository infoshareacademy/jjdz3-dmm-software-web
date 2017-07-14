package com.dmmsoft.charts;

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
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by milo on 14.07.17.
 */


    @WebServlet(urlPatterns = "/auth/userview/chartA")
    public class ChartAServlet extends HttpServlet {

        @Inject
        private IModelContainerService container;


        private static final Logger LOGGER = LoggerFactory.getLogger(com.dmmsoft.charts.ChartServlet.class);

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            JFreeChart chart = (JFreeChart) req.getSession().getAttribute("chartA");
            OutputStream out = resp.getOutputStream();
            resp.setContentType("image/png");

            ChartUtilities.writeChartAsPNG(out, chart, 800, 300);// write data to output stream
        }

/*    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("../auth/userview/chartB").forward(req, resp);

    }*/
}
