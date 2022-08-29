/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.servlets;
import com.example.fp_epam_app.DAO.mapper.ReportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
@WebServlet("/deleteReport")
public class DeleteReportServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteReportServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String report = req.getParameter("report");
        ReportDAO.getInstance().deleteReport(id,report);
        resp.sendRedirect("eventSettings.jsp?id="+id);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
