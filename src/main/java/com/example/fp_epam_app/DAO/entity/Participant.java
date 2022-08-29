/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.DAO.entity;

public class Participant {
    private static final long serialVersionUID = 1L;
    private String user_login;
    private int event_id;
    private String status;

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
