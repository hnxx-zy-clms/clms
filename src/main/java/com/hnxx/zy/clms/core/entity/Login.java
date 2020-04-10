package com.hnxx.zy.clms.core.entity;

/**
 * @author 南街北巷
 * @data 2020/4/10 18:59
 */
public class Login {
    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_college_id;
    private String user_classes_id;
    private String user_groupid_id;
    private String user_icon;
    private String user_description;
    private long created_time;
    private long update_time;
    private int user_position_id;
    private int user_status;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_college_id() {
        return user_college_id;
    }

    public void setUser_college_id(String user_college_id) {
        this.user_college_id = user_college_id;
    }

    public String getUser_classes_id() {
        return user_classes_id;
    }

    public void setUser_classes_id(String user_classes_id) {
        this.user_classes_id = user_classes_id;
    }

    public String getUser_groupid_id() {
        return user_groupid_id;
    }

    public void setUser_groupid_id(String user_groupid_id) {
        this.user_groupid_id = user_groupid_id;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public int getUser_position_id() {
        return user_position_id;
    }

    public void setUser_position_id(int user_position_id) {
        this.user_position_id = user_position_id;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }
}
