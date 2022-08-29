package com.example.fp_epam_app.controller.servlets;

import com.example.fp_epam_app.DAO.entity.Event;
import com.example.fp_epam_app.DAO.mapper.EventDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/updateEvent")
public class UpdateEventServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(UpdateEventServlet.class);
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Event event = new Event();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = df.parse(req.getParameter("date")+" "+req.getParameter("time")+":00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date1.getTime();

        Timestamp ts = new Timestamp(time);
        event.setId(Integer.parseInt(req.getParameter("id")));
        event.setTimestamp(ts);
        String name = req.getParameter("name");
        String place = req.getParameter("place");
        float amount = Float.parseFloat(req.getParameter("amount"));
        event.setName(name);
        event.setPlace(place);
        event.setAmount(amount);

        try {
            EventDAO.getInstance().UpdateEvent(event);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e);
        }
        resp.sendRedirect("event.jsp");

    }
}
