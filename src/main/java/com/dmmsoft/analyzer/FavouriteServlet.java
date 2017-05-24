package com.dmmsoft.analyzer;

import com.dmmsoft.dbtests.LocalInvestmentRevenueCriteria;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by milo on 24.05.17.
 */




@WebServlet(urlPatterns = "/analyzer/favourite")
public class FavouriteServlet extends HttpServlet {

    @Inject
    IFavouriteService favouriteService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //TODO get user Id from autehenticated user object
        // get list<criteria> of current user

        long userId = 1;

        List<LocalInvestmentRevenueCriteria> list = favouriteService.getAllUserFavoutiteCriteria(userId);

        System.out.println("user id: "+ userId);
        System.out.println("number of items: "+list.size());

        req.setAttribute("criteria",favouriteService.getAllUserFavoutiteCriteria(userId));
      //  req.getRequestDispatcher("favourites.jsp").forward(req,resp);

    }
}
