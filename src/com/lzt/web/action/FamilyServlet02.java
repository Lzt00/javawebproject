package com.lzt.web.action;

import com.lzt.web.bean.User;
import com.lzt.web.bean.User1;
import com.lzt.web.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @program:对用户进行增删改查
 * @ClassName:FamilyServlet02
 * @version:1.0
 * @author: lizetao
 * @create: 2022-08-10-17:50
 **/
@WebServlet({"/flist","/fadd","/fdel","/fmodify","/fdetail"})
public class FamilyServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if("/flist".equals(servletPath)){
            doFlist(request,response);
        }else if("/fadd".equals(servletPath)){
            doAdd(request,response);
        }else if("/fdel".equals(servletPath)){
            doDel(request,response);
        }else if("/fmodify".equals(servletPath)){
            doFmodify(request,response);
        }
        else if("/fdetail".equals(servletPath)){
            doDetail(request,response);
        }
    }

    /**
     * 连接数据库查询家庭成员的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doDetail(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        String contextPath = request.getContextPath();
        String id = request.getParameter("id");
        User1 user = new User1();
        int key=0;
        try {
            conn=DBUtils.getConnection();
            String sql ="select * from user1 where id=?";
            ps= conn.prepareStatement(sql);
            ps.setString(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");

                user.setName(name);
                user.setId(Integer.parseInt(id));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,rs);
            String f = request.getParameter("f");
            if("d".equals(f)){
                request.setAttribute("user",user);
                request.getRequestDispatcher("/fdetail.jsp").forward(request,response);
            }else {
                request.setAttribute("user",user);
                request.getRequestDispatcher("/fmodify.jsp").forward(request,response);
            }


        }

    }

    /**
     * 连接数据库对家庭成员表进行修改
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doFmodify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Connection conn=null;
        PreparedStatement ps =null;
        String contextPath = request.getContextPath();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int key=0;
        try {
            conn=DBUtils.getConnection();
            String sql ="update user1 set name = ? where id= ?";
            ps= conn.prepareStatement(sql);
            ps.setString(2,id);
            ps.setString(1,name);
            key=ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,null);
        }
        if(key==1){
            response.sendRedirect(""+contextPath+"/flist");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn=null;
        PreparedStatement ps = null;
        String id=request.getParameter("id");
        String contextPath = request.getContextPath();
        request.setCharacterEncoding("UTF-8");
        int key=0;
        try {
            conn=DBUtils.getConnection();
            String sql="delete from user1 where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            key=ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,null);
        }
        if(key==1){
            response.sendRedirect(""+contextPath+"/flist");
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        Connection conn=null;
        PreparedStatement ps = null;
        String name=request.getParameter("name");
        String contextPath = request.getContextPath();
        request.setCharacterEncoding("UTF-8");
        int key=0;
        try {
            conn=DBUtils.getConnection();
            String sql="insert into user1(name) values(?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            key=ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,null);
        }
        if(key==1){
            response.sendRedirect(""+contextPath+"/flist");
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doFlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<User1> list = new ArrayList<>();
        String contextPath = request.getContextPath();
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs =null;
        int key=0;
        try {
            conn= DBUtils.getConnection();
            String sql = "select * from user1";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                User1 user =new User1();
                user.setId(id);
                user.setName(name);
                list.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,null);
        }

       request.setAttribute("fuser",list);
        request.getRequestDispatcher("/flist.jsp").forward(request,response);

    }
}
