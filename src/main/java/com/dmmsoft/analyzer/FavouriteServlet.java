package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.InvestmentRevenueServlet;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;
import com.dmmsoft.container.IDataContainerService;
import com.dmmsoft.dbtests.LocalInvestmentRevenueCriteria;
import com.dmmsoft.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by milo on 24.05.17.
 */




@WebServlet(urlPatterns = "/analyzer/favourite")
public class FavouriteServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FavouriteServlet.class);

    @Inject
    IDataContainerService container;

    @Inject
    IFavouriteService favouriteService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("authenticatedUser");

        List<LocalInvestmentRevenueCriteria> list = favouriteService.getAllUserFavoutiteCriteria(user.getId());


        try{

        for(LocalInvestmentRevenueCriteria criteria : list){
         InvestmentRevenueResult ir = (new InvestmentRevenue(container.getMainContainer(), criteria)).getResult();

        // TODO implement JSP page displaying favourite analysis results
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("User Favourite analysis results: </br> ");
        out.println(ir.getCapitalRevenueValue() +"</br>");
        out.println(ir.getCapitalRevenueValue()+"</br>");

        LOGGER.info(ir.getCapitalRevenueDeltaPrecentValue().toString());
        LOGGER.info(ir.getCapitalRevenueValue().toString());

        }
        LOGGER.info(user.getLogin());

        System.out.println("number of items: "+list.size());

      //  req.getRequestDispatcher("favourites.jsp").forward(req,resp);

    }
    catch (NoDataForCriteria ex){

        LOGGER.error("error"+ex.getMessage());
        LOGGER.info(user.getLogin());
        }
    }
}
