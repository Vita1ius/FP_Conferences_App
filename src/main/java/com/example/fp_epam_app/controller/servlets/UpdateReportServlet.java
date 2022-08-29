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

@WebServlet("/updateReport")
public class UpdateReportServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(UpdateReportServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String report = req.getParameter("report");
        String speaker = req.getParameter("speaker");
        String status = req.getParameter("status");
        Report reports = new Report();
        reports.setEvent_id(id);
        reports.setReport(report);
        reports.setSpeaker(speaker);
        reports.setStatus(status);
        ReportDAO.getInstance().updateReport(reports);
        resp.sendRedirect("infoEvent.jsp?id="+id);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
