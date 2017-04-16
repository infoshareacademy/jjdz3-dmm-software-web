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

       AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
       MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);
       mainContainerLoader.loadFunds();
       mainContainerLoader.loadCurrencies();

       MainContainer mdc = mainContainerLoader.getMainContainer();
       List<Investment> investments = mdc.getInvestments();

       ItemStatsResult s = new ItemStats().getResult(investments, TEST_INV_NAME);
       // System.out.println(s.toString());

        resp.setContentType(MediaType.TEXT_HTML);
       // resp.getWriter().print(s.toString());

        req.setAttribute("investmentName", s.getName());
        req.setAttribute("firstQuotationDate",s.getFirstQuotation().getDate());
        req.setAttribute("firstQuotationValue",s.getFirstQuotation().getClose());

        req.setAttribute("lastQuotationDate", s.getLastQuotation().getDate());
        req.setAttribute("lastQuotationValue", s.getLastQuotation().getClose());

        req.setAttribute("maxDeltaPlusDate", s.getMaxDeltaPlus().getDate());
        req.setAttribute("maxDeltaPlusValue", s.getMaxDeltaPlus().getDeltaClose());

        req.setAttribute("maxDeltaMinusDate",s.getMaxDeltaMinus().getDate());
        req.setAttribute("maxDeltaMinusValue",s.getMaxDeltaMinus().getDeltaClose());

        req.setAttribute("maxValueQuotationDate", s.getMaxValueQuotation().getDate());
        req.setAttribute("maxValueQuotationValue", s.getMaxValueQuotation().getClose());
        req.setAttribute("maxValueQuotationDelta", s.getMaxValueQuotation().getDeltaClose());

        req.setAttribute("minValueQuotationDate", s.getMinValueQuotation().getDate());
        req.setAttribute("minValueQuotationValue", s.getMinValueQuotation().getClose());
        req.setAttribute("minValueQuotationDelta", s.getMinValueQuotation().getDeltaClose());

      //  req.setAttribute("actualValue", s.getActualValue().getClose());


        req.getRequestDispatcher("testpage.jsp").forward(req, resp);


    }

    /*

    private void doTest(){

      AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
      MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);

      mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();

    MainContainer mdc = mainContainerLoader.getMainContainer();
    List<Investment> investments = mdc.getInvestments();

    List<Quotation> filteredQuotations = new ArrayList<>();

    // to test choose investment id:

    Investment f = (Investment) investments.get(5);
    List<Quotation> quots = f.getQuotations();
    Quotation q = quots.get(0);

        for (FilePath item : appCon.getFundFilePaths()) {
        System.out.println(item.getFilePath());
    }

        for (FilePath item : appCon.getCurrencyFilePaths()) {
        System.out.println(item.getFilePath());
    }

        System.out.println("1. number of Investments loaded: " + investments.size());
        System.out.println("2. name of first Investment: " + f.getName());
        System.out.println("3. number of Quotations of first Investment: " + quots.size());
        System.out.println("4. name of Investment extracted form Quotation: " + q.getName());

        investments.forEach((Investment investment) -> {
        List<Quotation> quotationsPerInvestment = investment.getQuotations().stream()
                .filter(x -> x.getName().equals("AGI001"))
                .collect(Collectors.toList());
        filteredQuotations.addAll(quotationsPerInvestment);
        Collections.sort(filteredQuotations);
    });

        System.out.println(filteredQuotations);


    // Example of Analyzer usage (ItemStats)

    ItemStatsResult s = new ItemStats().getResult(investments, "AIP001");
        System.out.println(s.toString());

}

*/


}
