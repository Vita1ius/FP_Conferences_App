package com.example.fp_epam_app.controller.servlets;

import com.example.fp_epam_app.DAO.mapper.EventDAO;
import com.example.fp_epam_app.DAO.mapper.ParticipantDAO;
import com.example.fp_epam_app.DAO.mapper.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteReportServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("user_login");
        String id = req.getParameter("id");
        String amount = req.getParameter("amount");
        ParticipantDAO.getInstance().DeleteOrder(login,id);
        UserDAO.getInstance().cashBack(login,amount);
        EventDAO.getInstance().updateParticipate(-1,id);
        resp.sendRedirect("event.jsp");
    }
}
