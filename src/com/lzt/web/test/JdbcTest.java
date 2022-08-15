package com.lzt.web.test;

import com.lzt.web.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement ps = null;

        int key=0;
        String date = "2021-10-09 11:22:34";
        String money = "23";
        String person = "张三";
        try{
            conn=DBUtils.getConnection();
            String sql ="insert into account(ct,cm,cp) values(?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,date);
            ps.setString(2,money);
            ps.setString(3,person);

            key = ps.executeUpdate();

    } catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,null);
        }
    }
}
