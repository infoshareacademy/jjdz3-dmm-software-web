package com.dmmsoft;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;
import com.dmmsoft.app.analyzer.analyses.stats.ItemStats;
import com.dmmsoft.app.analyzer.analyses.stats.ItemStatsCriteria;
import com.dmmsoft.app.analyzer.analyses.stats.ItemStatsResult;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.dataloader.MainContainerLoader;
import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.container.IDataContainerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by milo on 12.05.17.
 */

@WebServlet(urlPatterns = "/revenue")
public class TestServletRevenue extends HttpServlet{
    @Inject
    IDataContainerService container;

   // private static final String TEST_INV_NAME= "AGI001";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException  {

            req.getRequestDispatcher("testpageRevenue.jsp").forward(req, resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {

        try {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

       // LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
       // LocalDate SELL_DATE = LocalDate.parse("20170402", formatter);
       // String InvestmentName = "CHF";

        String InvestmentName = req.getParameter("investmenName");
        String sCapital = req.getParameter("capital");
        String SBUY_DATE = req.getParameter("buyDate");
        String SSELL_DATE = req.getParameter("sellDate");

            if (SBUY_DATE==null || SBUY_DATE.toString().isEmpty())
                SBUY_DATE = "2009-09-10";

            if(SSELL_DATE==null || SSELL_DATE.toString().isEmpty())
                SSELL_DATE = "2017-04-02";

            if(sCapital==null|| sCapital.toString().isEmpty())
                sCapital = "120000";

            if(InvestmentName==null|| InvestmentName.isEmpty())
                InvestmentName="CHF";


        Double capital = Double.valueOf(sCapital);
        LocalDate BUY_DATE = LocalDate.parse(SBUY_DATE , formatter);
        LocalDate SELL_DATE = LocalDate.parse(SSELL_DATE, formatter);



            InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, Boolean.valueOf(false));
            InvestmentRevenueResult ir = (new InvestmentRevenue(container.getMainContainer(), input)).getResult();

            resp.setContentType(MediaType.TEXT_HTML);

            req.setAttribute("i_investmentName", input.getInvestmentName());
            req.setAttribute("i_investedCapital", input.getInvestedCapital());
            req.setAttribute("i_buyDate", input.getBuyDate());
            req.setAttribute("i_sellDate", input.getSellDate());

            InvestmentRevenueCriteria finallyEvaluatedInput = (InvestmentRevenueCriteria) ir.getFinallyEvaluatedInput();
            req.setAttribute("f_buyDate", finallyEvaluatedInput.getBuyDate());
            req.setAttribute("f_sellDate", finallyEvaluatedInput.getSellDate());


            req.setAttribute("r_capitalRevenue", ir.getCapitalRevenueValue());
            req.setAttribute("r_capitalRevenueDelta", ir.getCapitalRevenueDeltaPrecentValue());

            req.getRequestDispatcher("testpageRevenue.jsp").forward(req, resp);
        }
        catch (NoDataForCriteria ex){

             System.out.print(ex.getMessage());

        }

    }

}
