/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.DAO.mapper;

import com.example.fp_epam_app.DAO.entity.Report;
import com.example.fp_epam_app.DBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    private static final Logger log = Logger.getLogger(UserDAO.class);
    private static ReportDAO reportDAO;

    private ReportDAO(){
    }

    public static synchronized ReportDAO getInstance(){
        if (reportDAO == null){
            reportDAO = new ReportDAO();
        }
        return reportDAO;
    }
    public void registerReports(Report reports){
        Connection connection = null;
        String Insert_User_SQL = "INSERT INTO reports"+
                "(event_id,report,status) VALUES"+
                "(?,?,?);";
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_User_SQL);
            preparedStatement.setInt(1,reports.getEvent_id());
            preparedStatement.setString(2,reports.getReport());
            preparedStatement.setString(3,"Пропонується");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
    }
    public void proposedSpeaker(Report reports){
        Connection connection = null;
        String Insert_User_SQL = "INSERT INTO reports"+
                "(event_id,report,status,speaker) VALUES"+
                "(?,?,?,?);";
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_User_SQL);
            preparedStatement.setInt(1,reports.getEvent_id());
            preparedStatement.setString(2,reports.getReport());
            preparedStatement.setString(3,"Пропонує спікер");
            preparedStatement.setString(4,reports.getSpeaker());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
    }
    public List<Report> listReports(String id, String status) {
        ArrayList<Report> list = new ArrayList<>();
        Connection connection = null;
        Report reports;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("Select * from reports where status = ? and event_id = ?;");
            pstmt.setString(1,status);
            pstmt.setInt(2, Integer.parseInt(id));
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()) {
                reports = new Report();
                reports.setEvent_id(rs.getInt("event_id"));
                reports.setReport(rs.getString("report"));
                reports.setSpeaker(rs.getString("speaker"));
                reports.setStatus(rs.getString("status"));
                list.add(reports);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
        return list;
    }
    public void updateReport(Report reports) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE reports SET speaker = ?, status = ? where event_id =? and report = ?;");
            stmt.setString(1,reports.getSpeaker());
            stmt.setString(2, reports.getStatus());
            stmt.setInt(3, reports.getEvent_id());
            stmt.setString(4, reports.getReport());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }
    public void deleteReport(int id,String report) {
        int i = 0;
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM reports WHERE event_id = ? and report = ?");
            stmt.setInt(1,id);
            stmt.setString(2, report);
            i = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }
    public void changeThemeForReport(Report reports, String changeReport) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE reports SET report = ? where event_id =? and report = ?;");
            stmt.setString(1,changeReport);
            stmt.setInt(2, reports.getEvent_id());
            stmt.setString(3, reports.getReport());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }
}
