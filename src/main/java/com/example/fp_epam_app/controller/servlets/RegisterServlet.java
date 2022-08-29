/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.servlets;

import com.example.fp_epam_app.DAO.entity.User;
import com.example.fp_epam_app.DAO.mapper.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RegisterServlet.class);
    private static final long serialVersionUID = 1L;
    @Override
    public void init() throws ServletException {
        UserDAO userdao = UserDAO.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);

        try {
            UserDAO.getInstance().registerUser(user);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e);
        }
        resp.sendRedirect("login.jsp");

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
