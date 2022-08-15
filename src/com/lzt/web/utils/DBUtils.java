package com.lzt.web.utils;

import java.sql.*;
import java.util.ResourceBundle;

public  class DBUtils {
    private static  ResourceBundle bundle =ResourceBundle.getBundle("resources.jdbc");
    private static String user=bundle.getString("use");
    private static String pwd=bundle.getString("pwd");
    private static String url=bundle.getString("url");
    private static String driver=bundle.getString("driver");

    public static void main(String[] args) {
        System.out.println(user);
    }

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url,user,pwd);
        return conn;
    }


    public static void close(Connection conn, Statement ps,ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        }}

