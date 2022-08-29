/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.DAO.mapper;

import com.example.fp_epam_app.DAO.entity.User;
import com.example.fp_epam_app.DBConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final Logger log = Logger.getLogger(UserDAO.class);
    private static UserDAO userDAO;

    private UserDAO(){

    }

    public static synchronized UserDAO getInstance(){
        if (userDAO == null){
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    public void registerUser(User user){
        Connection connection = null;
        String Insert_User_SQL = "INSERT INTO user"+
                "(name,email,login,password,role) VALUES"+
                "(?,?,?,?,?);";
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_User_SQL);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getLogin());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,"user");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
    }
    public User validate_loginAndPassword(String login, String password) throws ClassNotFoundException {
        Connection connection = null;
        User user = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement("select * from user where login = ? and password = ? ");
            stmt.setString(1,login);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                log.debug("User login "+rs.getString("login"));
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setAccount(rs.getFloat("account"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }


        return user;
    }
    public List<User> list() {
        ArrayList<User> list = new ArrayList<>();
        Connection connection = null;
        User user;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstmt=connection.prepareStatement("Select * from user");
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setAccount(rs.getFloat("account"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
        return list;
    }

    public void updateAccount(String login, float amount) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET account = account - ? where login = ?;");
            stmt.setFloat(1, amount);
            stmt.setString(2, login);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }

    public User getUser(String login) throws ClassNotFoundException {
        Connection connection = null;
        User user = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement("select * from user where login = ?;");
            stmt.setString(1,login);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setAccount(rs.getFloat("account"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(connection);
        }finally {
            DBConnection.getInstance().closeConnection(connection);
        }
        return user;
    }
    public void cashBack(String login, String amount) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET account = account + ? where login = ?;");
            stmt.setFloat(1, Float.parseFloat(amount));
            stmt.setString(2, login);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }
    public User delete(String login) {
        User user = null;
        Connection conn = null;

        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM participants where user_login = ?;");
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM user where login = ?;");
            stmt.setString(1, login);
            stmt1.setString(1, login);
            stmt.executeUpdate();
            stmt1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(conn);
        }
        return user;
    }
    public void UpdateRole(String login,String role) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user SET role = ? where login = ?;");
            stmt.setString(1, role);
            stmt.setString(2, login);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            DBConnection.getInstance().closeConnection(conn);
        }
    }
}
