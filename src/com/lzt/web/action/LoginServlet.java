package com.lzt.web.action;

import com.lzt.web.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program:
 * @ClassName:LoginServlet
 * @version:1.0
 * @author: lizetao
 * @create: 2022-08-15-13:26
 **/
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection conn= null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        boolean success=false;
        try {
            conn= DBUtils.getConnection();
            String sql = "select * from user2 where username=? and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);

            rs=ps.executeQuery();
            if(rs.next()){
                success=true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,rs);
        }
        if(success){
            HttpSession session = request.getSession();
            session.setAttribute("user",username);
            request.getRequestDispatcher("/clist").forward(request,response);
        }else {

        }

    }
}
