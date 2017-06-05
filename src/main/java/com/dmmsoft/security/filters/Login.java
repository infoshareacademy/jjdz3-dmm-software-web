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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI();

        LOGGER.info("login filter request path: {}", path);
        if ((path.startsWith("/financial-app/login")|path.startsWith("/financial-app/login.jsp")
                |path.startsWith("/financial-app/auth"))){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            req.getRequestDispatcher("/auth/accessdenied.jsp").forward(req, resp);
            LOGGER.warn("Access denied! Not authenticated user request!");
        }

    }

    @Override
    public void destroy() {

    }

}
