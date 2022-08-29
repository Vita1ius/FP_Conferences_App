/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.DAO.mapper;

import com.example.fp_epam_app.DAO.entity.Participant;
import com.example.fp_epam_app.DBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {
    private static final Logger log = Logger.getLogger(UserDAO.class);
    private static ParticipantDAO participantsDAO;

    private ParticipantDAO(){

    }

    public static synchronized ParticipantDAO getInstance(){
        if (participantsDAO == null){
            participantsDAO = new ParticipantDAO();
        }
        return participantsDAO;
    }
    public void registerParticipants(Participant participants){
        Connection connection = null;
        String Insert_Participants_SQL = "INSERT INTO participants"+
                "(user_login, event_id, status) VALUES"+
                "(?,?,?);";
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Participants_SQL);
            preparedStatement.setString(1,participants.getUser_login());
            preparedStatement.setInt(2,participants.getEvent_id());
            preparedStatement.setString(3,participants.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
    }
    public boolean checkMember(String login,int id) {
        Connection connection = null;
        boolean check = false;

        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("SELECT * FROM conferences.participants where user_login = ? and event_id = ?;");
            pstmt.setString(1,login);
            pstmt.setInt(2,id);
            ResultSet rs= pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
        return check;
    }
    public void DeleteOrder(String login,String id) {
        int i = 0;
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM conferences.participants WHERE user_login = ? and event_id = ?");
            stmt.setString(1,login);
            stmt.setInt(2, Integer.parseInt(id));
            i =     stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }
    public void ChangeStatus(Participant participant){
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE participants SET status = ? where event_id = ? and user_login = ?;");
            stmt.setString(1,participant.getStatus());
            stmt.setInt(2,participant.getEvent_id());
            stmt.setString(3, participant.getUser_login());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }
    public List<Participant> getParticipantById(String id) {
        Connection connection = null;
        ArrayList<Participant> list = new ArrayList<>();
        Participant participant = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM participants where event_id = ?;");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                participant = new Participant();
                participant.setUser_login(rs.getString("user_login"));
                participant.setStatus(rs.getString("status"));
                list.add(participant);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        } finally {
            DBConnection.getInstance().closeConnection(connection);
        }
        return list;
    }
}
