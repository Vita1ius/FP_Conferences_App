/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.servlets;

import com.example.fp_epam_app.DAO.entity.Report;
import com.example.fp_epam_app.DAO.mapper.ReportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ReportServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String report = req.getParameter("report");
        int id = Integer.parseInt(req.getParameter("id"));
        String role = req.getParameter("role");
        String name = req.getParameter("name");
        Report reports = new Report();
        reports.setEvent_id(id);
        reports.setReport(report);
        if (role.equalsIgnoreCase("moderator")){
            ReportDAO.getInstance().registerReports(reports);
        }else if (role.equalsIgnoreCase("speaker")){
            reports.setSpeaker(name);
            ReportDAO.getInstance().proposedSpeaker(reports);
        }
        resp.sendRedirect("event.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        ReportDAO reportDAO = ReportDAO.getInstance();
    }
}
