/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.servlets;
import com.example.fp_epam_app.DAO.entity.User;
import com.example.fp_epam_app.DAO.mapper.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;

import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = UserDAO.getInstance().validate_loginAndPassword(login,password);
            if (user != null){
                HttpSession session = req.getSession(true);
                session.setAttribute("login", user.getLogin());
                session.setAttribute("name", user.getName());
                session.setAttribute("role", user.getRole());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("account", user.getAccount());
                session.setMaxInactiveInterval(60*60);
                Cookie userName = new Cookie("login",login);
                resp.addCookie(userName);
                resp.sendRedirect("index.jsp");
            }else {
                resp.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(e);
        }

    }
}
