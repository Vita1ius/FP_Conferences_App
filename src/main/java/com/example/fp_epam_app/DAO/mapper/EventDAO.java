/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.DAO.mapper;

import com.example.fp_epam_app.DAO.entity.Event;
import com.example.fp_epam_app.DBConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private static final Logger log = Logger.getLogger(UserDAO.class);
    private static EventDAO eventDAO;
    private int noOfRecords;

    private EventDAO(){

    }

    public static synchronized EventDAO getInstance(){
        if (eventDAO == null){
            eventDAO = new EventDAO();
        }
        return eventDAO;
    }
    public List<Event> list() {
        ArrayList<Event> list = new ArrayList<>();
        Connection connection = null;
        Event event;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("SELECT * FROM conferences.event where datetime >= curdate();");
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()) {
                event = new Event();
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("name"));
                event.setTimestamp(rs.getTimestamp("datetime"));
                event.setNumber_of_participants(rs.getInt("number_of_participants"));
                event.setPlace(rs.getString("place"));
                event.setAmount(rs.getFloat("amount"));
                list.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
        return list;
    }
    public void registerEvent(Event event){
        Connection connection = null;
        String Insert_Event_SQL = "INSERT INTO event"+
                "(name,datetime,place,amount,number_of_participants) VALUES"+
                "(?,?,?,?,?);";
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Event_SQL);
            preparedStatement.setString(1,event.getName());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(event.getTimestamp().getTime()));
            preparedStatement.setString(3,event.getPlace());
            preparedStatement.setFloat(4,event.getAmount());
            preparedStatement.setInt(5,0);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
    }
    public Event getEvent(String id) {
        Connection connection = null;
        Event event = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("SELECT * FROM conferences.event where id ="+id+";");
            ResultSet rs= pstmt.executeQuery();
            if (rs.next()) {
                event = new Event();
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("name"));
                event.setTimestamp(rs.getTimestamp("datetime"));
                event.setNumber_of_participants(rs.getInt("number_of_participants"));
                event.setPlace(rs.getString("place"));
                event.setAmount(rs.getFloat("amount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
        return event;
    }


    public List<Event> getPastEvent(int offset, int noOfRecords)

    {

        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM conferences.event where datetime < curdate() limit " + offset + ", " + noOfRecords;

        List<Event> list = new ArrayList<>();

        Event event;
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                event = new Event();
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("name"));
                event.setTimestamp(rs.getTimestamp("datetime"));
                event.setNumber_of_participants(rs.getInt("number_of_participants"));
                event.setPlace(rs.getString("place"));
                event.setAmount(rs.getFloat("amount"));
                list.add(event);
            }
            rs.close();
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next())

                this.noOfRecords = rs.getInt(1);

        }

        catch (SQLException e) {

            e.printStackTrace();

        } finally

        {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int getNoOfRecords() { return noOfRecords; }


    public void updateParticipate(int number,String id) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE event SET number_of_participants = event.number_of_participants + ? where id = ?;");
            stmt.setInt(1, number);
            stmt.setInt(2, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }

    public List<Event> getEventsByLogin(String login) {
        Connection connection = null;
        ArrayList<Event> list = new ArrayList<>();
        Event event = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT event.* FROM event,participants where id = participants.event_id and participants.user_login = ?;");
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                event = new Event();
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("name"));
                event.setTimestamp(rs.getTimestamp("datetime"));
                event.setNumber_of_participants(rs.getInt("number_of_participants"));
                event.setPlace(rs.getString("place"));
                event.setAmount(rs.getFloat("amount"));
                list.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        } finally {
            DBConnection.getInstance().closeConnection(connection);
        }
        return list;
    }
    public Event delete(String id) {
        Event event = null;
        Connection conn = null;

        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE from event where id=?");
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }
        return event;
    }
    public void UpdateEvent(Event event) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE event SET id=?,name=?,place=?,amount=?,datetime=? where id = ?;");
            stmt.setInt(1,event.getId());
            stmt.setString(2,event.getName());
            stmt.setString(3,event.getPlace());
            stmt.setFloat(4,event.getAmount());
            stmt.setTimestamp(5,event.getTimestamp());
            stmt.setInt(6,event.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }
}
