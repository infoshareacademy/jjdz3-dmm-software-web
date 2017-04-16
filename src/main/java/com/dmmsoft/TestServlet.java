package com.dmmsoft; /**
 * Created by milo on 15.04.17.
 */


import com.dmmsoft.app.Analyzer.W01Stats.ItemStats;
import com.dmmsoft.app.Analyzer.W01Stats.ItemStatsResult;
import com.dmmsoft.app.AppConfiguration.AppConfigurationProvider;
import com.dmmsoft.app.DataLoader.MainContainerLoader;
import com.dmmsoft.app.FileIO.Path.FilePath;
import com.dmmsoft.app.POJO.Investment;
import com.dmmsoft.app.POJO.MainContainer;
import com.dmmsoft.app.POJO.Quotation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    private static final String TEST_INV_NAME= "AGI001";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("testpage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        //req.setAttribute("name", name);

        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();

        MainContainer mdc = mainContainerLoader.getMainContainer();
        List<Investment> investments = mdc.getInvestments();

        if(name==null){
            name="AIP001";}

        ItemStatsResult s = new ItemStats().getResult(investments, name);
        // System.out.println(s.toString());

        resp.setContentType(MediaType.TEXT_HTML);
        // resp.getWriter().print(s.toString());

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
