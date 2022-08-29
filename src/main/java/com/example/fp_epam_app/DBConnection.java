/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {
    private static final Logger log = Logger.getLogger(DBConnection.class);
    /**
     * JDBC Database connection pool ( DCP )
     */
    private static DBConnection datasource;
    private DBConnection() {
    }
    public static DBConnection getInstance(){
        if (datasource == null){
            datasource = new DBConnection();
        }
        return datasource;
    }

    public Connection getConnection() {
        Connection connection = null;
        ResourceBundle rb = ResourceBundle.getBundle("WebAppResources.System");
        try {
            Class.forName(rb.getString("driver"));
            String url = rb.getString("url");
            String user = rb.getString("username");
            String password = rb.getString("password");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e);
            System.out.println("Connection ERROR");
        }
        return connection;
    }
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("connection closed");
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e);
            }
        }
    }

}
