package com.example.fp_epam_app.controller.servlets;

import com.example.fp_epam_app.DAO.entity.Event;
import com.example.fp_epam_app.DAO.mapper.EventDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
@WebServlet("/pastEvent")
public class PastEventsServlet  extends HttpServlet {
    private static final Logger log = Logger.getLogger(PastEventsServlet.class);
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;

        int recordsPerPage = 5;

        if (req.getParameter("page") != null)

            page = Integer.parseInt(

                    req.getParameter("page"));

        ArrayList<Event> Eventlist = (ArrayList<Event>) EventDAO.getInstance().getPastEvent((page - 1) * recordsPerPage, recordsPerPage);

        int noOfRecords = EventDAO.getInstance().getNoOfRecords();

        int noOfPages = (int)Math.ceil(noOfRecords * 1.0

                / recordsPerPage);

        req.setAttribute("eventList", Eventlist);

        req.setAttribute("noOfPages", noOfPages);

        req.setAttribute("currentPage", page);

        RequestDispatcher view

                = req.getRequestDispatcher("pastEvents.jsp");

        view.forward(req, resp);
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
