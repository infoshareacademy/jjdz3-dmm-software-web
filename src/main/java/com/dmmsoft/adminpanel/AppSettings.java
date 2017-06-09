package com.dmmsoft.adminpanel;

import com.dmmsoft.container.IModelContainerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dmmsoft.ConstantsProvider.FUND_COUNT;
import static com.dmmsoft.ConstantsProvider.CURRENCY_COUNT;


/**
 * Created by milo on 08.06.17.
 */

@WebServlet(urlPatterns = "/auth/adminview/appsettings")
public class AppSettings extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettings.class);

    @Inject
    IModelContainerService container;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(FUND_COUNT, container.getMainContainer().getCurrenciesCount());
        req.setAttribute(CURRENCY_COUNT, container.getMainContainer().getFundsCount());
        req.getRequestDispatcher("../adminview/appSettings.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            LOGGER.info("Administrator Main Container manual reloading initialized...");

            container.reload();
            req.setAttribute(CURRENCY_COUNT, container.getMainContainer().getCurrenciesCount());
            req.setAttribute(FUND_COUNT, container.getMainContainer().getFundsCount());

            LOGGER.info("Main Container reloaded! Actual CSV available!");
            LOGGER.info("Currencies items:{} Funds items:{}",
                    container.getMainContainer().getCurrenciesCount(),
                    container.getMainContainer().getFundsCount());

        } catch (RuntimeException ex) {

            LOGGER.error("FATAL ERROR: Failed to manually reload data model CSV files! {}", ex.getMessage());
        }

        req.getRequestDispatcher("../adminview/appSettings.jsp").forward(req, resp);
    }
}
