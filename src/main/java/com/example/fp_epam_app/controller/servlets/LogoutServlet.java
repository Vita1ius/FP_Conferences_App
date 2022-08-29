/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;

import java.io.IOException;
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LogoutServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //invalidate the session if exists
        HttpSession session = req.getSession(false);
        //System.out.println("User="+session.getAttribute("uname"));
        if(session != null){
            session.invalidate();
        }
        resp.sendRedirect("login.jsp");
    }
}
