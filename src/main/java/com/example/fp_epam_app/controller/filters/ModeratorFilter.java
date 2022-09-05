package com.example.fp_epam_app.controller.filters;

import com.example.fp_epam_app.DAO.entity.User;
import com.example.fp_epam_app.DAO.mapper.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = {"/deleteUser" , "/updateEvent", "/changeRole", "/deleteReport", "/InsertEvent" , "/updateReport"})
public class ModeratorFilter implements Filter {

    private static final Logger log = Logger.getLogger(EncodingFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = null;
        try {
            user = UserDAO.getInstance().getUser((String) session.getAttribute("login"));
            log.info("Moderator filter executed");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (user != null && !user.getRole().equals("moderator")) {
            ((HttpServletResponse) servletResponse).sendRedirect("index.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}