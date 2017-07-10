package com.dmmsoft.charts;

import com.dmmsoft.adminpanel.AppSettingsServlet;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by milo on 10.07.17.
 */

@WebServlet(urlPatterns = "/auth/userview/chart")
public class ChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final ChartGenerator chartGenerator = new ChartGenerator();
        JFreeChart chart = chartGenerator.renderChart(); // generate chart
        OutputStream out = resp.getOutputStream(); // output stream from response object
        resp.setContentType("image/png");
        ChartUtilities.writeChartAsPNG(out, chart, 400, 300);// write data to output stream
    }


}
