package com.dmmsoft.analyzer.analysis.investmentstatistics;

/**
 * Created by milo on 15.04.17.
 */

import com.dmmsoft.app.analyzer.analyses.stats.ItemStats;
import com.dmmsoft.app.analyzer.analyses.stats.ItemStatsCriteria;
import com.dmmsoft.app.analyzer.analyses.stats.ItemStatsResult;

import com.dmmsoft.app.model.Investment;

import com.dmmsoft.container.IModelContainerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import java.util.List;

import static com.dmmsoft.ConstantsProvider.*;

@WebServlet(urlPatterns = "/investmentstats")
public class InvestmentStatisticsServlet extends HttpServlet {

    @Inject
    IModelContainerService container;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("../userview/investments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nameA = req.getParameter(INVESTMENT_NAME_A);
        String nameB = req.getParameter(INVESTMENT_NAME_B);

        ItemStatsResult resultA = new ItemStats().getResult(container.getInvestments()
                , new ItemStatsCriteria(INVESTMENT_NAME_A));

        ItemStatsResult resultB = new ItemStats().getResult(container.getInvestments()
                , new ItemStatsCriteria(INVESTMENT_NAME_B));


        req.setAttribute(INVESTMENT_STAT_RESULT_A, resultA);
        req.setAttribute(INVESTMENT_STAT_RESULT_B, resultB);

        req.getRequestDispatcher("../userview/investments.jsp").forward(req, resp);
        req.getRequestDispatcher("testpage.jsp").forward(req, resp);

    }
}
