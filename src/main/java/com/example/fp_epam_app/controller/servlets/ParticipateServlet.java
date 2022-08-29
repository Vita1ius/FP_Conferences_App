/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.servlets;
import com.example.fp_epam_app.DAO.entity.Participant;
import com.example.fp_epam_app.DAO.mapper.EventDAO;
import com.example.fp_epam_app.DAO.mapper.ParticipantDAO;
import com.example.fp_epam_app.DAO.mapper.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
@WebServlet(name = "ParticipateServlet", value = "/ParticipateServlet")
public class ParticipateServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ParticipateServlet.class);
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("user_login");
        int id = Integer.parseInt(req.getParameter("id"));
        float amount = Float.parseFloat(req.getParameter("amount"));
        if (ParticipantDAO.getInstance().checkMember(login,id)){
            resp.sendRedirect("event.jsp");
        }else {
            try {
                if (UserDAO.getInstance().getUser(login).getAccount() >= amount){
                    Participant participants = new Participant();
                    participants.setEvent_id(id);
                    participants.setUser_login(login);
                    participants.setStatus("Member");
                    ParticipantDAO.getInstance().registerParticipants(participants);
                    EventDAO.getInstance().updateParticipate(1, String.valueOf(id));
                    UserDAO.getInstance().updateAccount(login,amount);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("event.jsp");
        }
    }


}
