package com.dmmsoft.adminpanel;

import com.dmmsoft.user.IUserService;
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
import java.util.Optional;

import static com.dmmsoft.utils.ConstantsProvider.ALL_USERS;
import static com.dmmsoft.utils.ConstantsProvider.CONTENT_WRAPPER;

/**
 * Created by milo on 27.07.17.
 */

@WebServlet("/auth/adminview/userdetails")
public class UserDetails extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetails.class);
    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String editId = req.getParameter("id");
        long Id = Long.parseLong(editId);
        User user = userService.get(Id);

        req.setAttribute(CONTENT_WRAPPER, user);
        req.getRequestDispatcher("../adminview/userDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isAdmin = req.getParameter("isAdmin") != null;

        Optional<Long> id = Optional.ofNullable(req.getParameter("id"))
                .map(String::trim)
                .filter(idString -> !idString.isEmpty())
                .map(Long::parseLong);

        User user = id.map(User::new)
                .orElseGet(User::new);

        user.setAdmin(isAdmin);

        if (id.isPresent()) {
            userService.update(user);
        } else {
            LOGGER.error("Failed to update user. User doesn't exists. {}", user.getId());
            throw new IllegalStateException();
        }
        req.setAttribute(ALL_USERS, userService.getAllUsers());
        req.getRequestDispatcher("../adminview/userManagement.jsp").forward(req, resp);
    }
}
