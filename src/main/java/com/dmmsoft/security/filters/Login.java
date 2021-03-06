package com.dmmsoft.security.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milo on 02.06.17.
 */
@WebFilter(filterName = "loginAccessPoint")

public class Login implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

    private final String[] urlAcccessPatterns = {
            "/financial-app/login",
            "/financial-app/login.jsp",
            "/financial-app/auth",
            "/financial-app/api",
            "/financial-app/res"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI();

        if (checkIfRequestPathIsValid(urlAcccessPatterns, path)) {
            filterChain.doFilter(servletRequest, servletResponse);
          //  LOGGER.info("login filter request path: {}", path);
            LOGGER.info("Valid request. Access succeed.");
        } else {
            req.getRequestDispatcher("/auth/accessdenied.jsp").forward(req, resp);
          //  LOGGER.info("login filter request path: {}", path);
            LOGGER.warn("Not authenticated request! Access denied!");
        }
    }

    private boolean checkIfRequestPathIsValid(String[] urlAcccessPatterns, String path) {
        boolean hasAccess = false;
        for (String item : urlAcccessPatterns) {
            if (path.startsWith(item)) {
                hasAccess = true;
                break;
            } else {
                hasAccess=false;
            }
        }
        return hasAccess;
    }

    @Override
    public void destroy() {
    }


}
