/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.servlets;
import com.example.fp_epam_app.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;

import static java.lang.System.out;

@WebServlet("/account")
public class DepositServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DepositServlet.class);
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        Statement statement = null;
        ResultSet resultSet;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            if (connection != null) {
                statement=connection.createStatement();
            }
            assert statement != null;
            resultSet = statement.executeQuery("SELECT * FROM conferences.user where login = '"+login+"';");

            if(resultSet.next()){
                double account = Double.parseDouble(resultSet.getString("account"));
                double top_up_amount;
                if (req.getParameter("top-up_amount").isEmpty()){
                    top_up_amount = 0;
                }else {top_up_amount = Double.parseDouble(req.getParameter("top-up_amount"));}
                if (top_up_amount > 0){
                    account += top_up_amount;
                    if (account > 99999){
                        resp.sendRedirect("profile.jsp");
                    }
                    String sql="Update user set account=? where login='"+login+"'";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setDouble(1,account);
                    int status = preparedStatement.executeUpdate();
                    if(status > 0)
                    {
                        resp.sendRedirect("profile.jsp");
                    }
                    else
                    {
                        out.print("There is a problem in updating Record.");
                    }
                }else {
                    resp.sendRedirect("profile.jsp");
                }
            }
        } catch (SQLException e) {
            req.setAttribute("error", e);
            out.println(e);
            log.error(e);
        }
    }
}
