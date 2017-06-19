package com.dmmsoft.analyzer.analysis;

import com.dmmsoft.container.IModelContainerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dmmsoft.ConstantsProvider.ALL_INVESTMENTS;
import static com.dmmsoft.ConstantsProvider.CURRENCY_COUNT;
import static com.dmmsoft.ConstantsProvider.FUND_COUNT;

/**
 * Created by milo on 09.06.17.
 */

@WebServlet(urlPatterns = "/auth/userview/investments")
public class InvestmentsServlet extends HttpServlet {

    @Inject
    IModelContainerService container;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(CURRENCY_COUNT, container.getMainContainer().getCurrenciesCount());
        req.setAttribute(FUND_COUNT, container.getMainContainer().getFundsCount());
        req.setAttribute(ALL_INVESTMENTS, container.getMainContainer().getInvestments());

        req.getRequestDispatcher("../userview/investments.jsp").forward(req, resp);

    }
}
