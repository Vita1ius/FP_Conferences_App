/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.servlets;
import com.example.fp_epam_app.DAO.entity.Participant;
import com.example.fp_epam_app.DAO.mapper.ParticipantDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
@WebServlet("/ChangeParticipate")
public class ChangeParticipateServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ChangeParticipateServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String login = req.getParameter("user_login");
        Participant participant = new Participant();
        participant.setEvent_id(id);
        participant.setUser_login(login);
        participant.setStatus("Physically came");
        ParticipantDAO.getInstance().ChangeStatus(participant);
        resp.sendRedirect("event.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
