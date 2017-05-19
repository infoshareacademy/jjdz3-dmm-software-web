package com.dmmsoft.userview;

/**
 * Created by milo on 15.04.17.
 */

import com.dmmsoft.app.analyzer.analyses.stats.ItemStats;
import com.dmmsoft.app.analyzer.analyses.stats.ItemStatsCriteria;
import com.dmmsoft.app.analyzer.analyses.stats.ItemStatsResult;

import com.dmmsoft.app.model.Investment;

import com.dmmsoft.container.IDataContainerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import java.util.List;

@WebServlet(urlPatterns = "/investmentstats")
public class InvestmentStatisticsServlet extends HttpServlet {

    @Inject
    IDataContainerService container;

    private static final String TEST_INV_NAME= "AGI001";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("testpage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        List<Investment> investments = container.getInvestments();

        if(name==null){
            name="AIP001";}

        ItemStatsCriteria criteria = new ItemStatsCriteria(name);
        ItemStatsResult s = new ItemStats().getResult(investments, criteria);

        resp.setContentType(MediaType.TEXT_HTML);

        req.setAttribute("investmentName", s.getName());
        req.setAttribute("firstQuotationDate",s.getFirstQuotation().getDate());
        req.setAttribute("firstQuotationValue",s.getFirstQuotation().getClose());

        req.setAttribute("lastQuotationDate", s.getLastQuotation().getDate());
        req.setAttribute("lastQuotationValue", s.getLastQuotation().getClose());

        req.setAttribute("maxDeltaPlusDate", s.getMaxDeltaPlus().getDate());
        req.setAttribute("maxDeltaPlusValue", s.getMaxDeltaPlus().getClose());
        req.setAttribute("maxDeltaPlusDelta", s.getMaxDeltaPlus().getDeltaClose());

        req.setAttribute("maxDeltaMinusDate",s.getMaxDeltaMinus().getDate());
        req.setAttribute("maxDeltaMinusValue",s.getMaxDeltaMinus().getClose());
        req.setAttribute("maxDeltaMinusDelta",s.getMaxDeltaMinus().getDeltaClose());

        req.setAttribute("maxValueQuotationDate", s.getMaxValueQuotation().getDate());
        req.setAttribute("maxValueQuotationValue", s.getMaxValueQuotation().getClose());
        req.setAttribute("maxValueQuotationDelta", s.getMaxValueQuotation().getDeltaClose());

        req.setAttribute("minValueQuotationDate", s.getMinValueQuotation().getDate());
        req.setAttribute("minValueQuotationValue", s.getMinValueQuotation().getClose());
        req.setAttribute("minValueQuotationDelta", s.getMinValueQuotation().getDeltaClose());

        //  req.setAttribute("actualValue", s.getActualValue().getClose());

        req.getRequestDispatcher("testpage.jsp").forward(req, resp);

    }
}
