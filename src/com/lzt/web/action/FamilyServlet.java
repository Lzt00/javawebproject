package com.lzt.web.action;

import com.lzt.web.bean.User;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @program:对账单进行增删改查
 * @ClassName:FamilyServlet02
 * @version:1.0
 * @author: lizetao
 * @create: 2022-08-10-17:50
 **/

@WebServlet({"/clist","/cadd","/cmodify","/cdetail","/cdel"})
public class FamilyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("user");
        if(session!=null&&username!=null) {
            String servletPath = request.getServletPath();
            if ("/clist".equals(servletPath)) {
                doList(request, response);
            } else if ("/cadd".equals(servletPath)) {
                doCadd(request, response);
            } else if ("/cmodify".equals(servletPath)) {
                doModify(request, response);
            } else if ("/cdetail".equals(servletPath)) {
                doCdetail(request, response);
            } else if ("/cdel".equals(servletPath)) {
                doDele(request, response);
            }
        }else {
            response.sendRedirect(request.getContextPath());
        }
    }



    /**
     * 连接数据库，将数据删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doDele(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int i=0;
        String contextPath = request.getContextPath();
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs =null;
        String id = request.getParameter("id");
        try {
            conn=DBUtils.getConnection();
            String sql = "delete from account where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
             i= ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,rs);
        }
        if(i==1){
            response.sendRedirect(""+contextPath+"/clist");
        }else {
            //跳到错误页面
        }

    }

    /**
     * 连接数据库展示详情页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doCdetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contextPath = request.getContextPath();
        request.setCharacterEncoding("UTF-8");
            User user = new User();
            int id =Integer.parseInt(request.getParameter("id"));
            Connection conn = null;
            PreparedStatement ps =null;
            ResultSet rs = null;
        try {
            conn=DBUtils.getConnection();
            String sql ="select * from account where id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
             if(rs.next()){
                String time = rs.getString("ct");
                Double money = rs.getDouble("cm");
                String person = rs.getString("cp");
                user.setId(id);
                user.setDate(time);
                user.setMoney(money);
                user.setPerson(person);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,rs);
        }

        String f = request.getParameter("f");
        if("d".equals(f)){
            request.setAttribute("user", user);
            request.getRequestDispatcher("/cdetail.jsp").forward(request, response);

        }else {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/modify.jsp").forward(request, response);
        }
        }

    /**
     * 连接数据库，对表account中数据进行修改
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    private void doModify(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String contextPath = request.getContextPath();
        int id = Integer.parseInt(request.getParameter("id"));
        String person = request.getParameter("person");
        String date = request.getParameter("date");
        String money = request.getParameter("money");
        Connection conn=null;
        PreparedStatement ps = null;
        int key=0;
        try {
            conn = DBUtils.getConnection();
            String sql ="update account set ct=?,cm=?,cp=?  where id=? ";
            ps= conn.prepareStatement(sql);
            ps.setInt(4,id);
            ps.setString(1,date);
            ps.setString(2,money);
            ps.setString(3,person);
           key = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,null);
        }
        if(key==1){
            response.sendRedirect(""+contextPath+"/clist");
        }else {
            //跳转到失败页面
        }
    }

    /**
     *连接数据库，添加数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doCadd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //解决数据请求的乱码问题
        request.setCharacterEncoding("UTF-8");
        String contextPath = request.getContextPath();
        String date = request.getParameter("date");
        String money = request.getParameter("money");
        String person = request.getParameter("person");
        Connection conn=null;
        PreparedStatement ps = null;
        int key=0;
        try {
            conn=DBUtils.getConnection();
            String sql ="insert into account(ct,cm,cp) values(?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,date);
            ps.setString(2,money);
            ps.setString(3,person);

            key = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,null);
        }
        if(key==1){
            response.sendRedirect(""+contextPath+"/clist");
        }else {
            response.sendRedirect(""+contextPath+"/erro.jsp");
        }
    }


    /**
     * 连接数据库查询account表中的账单信息，用于做页面展示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //list集合的特点：有序可重复
        List<User> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs= null;
        try {
            conn = DBUtils.getConnection();
            String sql ="select id,ct,cm,cp from account";
            ps= conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String  date= rs.getString("ct");
                Double money = rs.getDouble("cm");
                String person = rs.getString("cp");
                //将查到的数据封装到User对象中，然后将User对象放入集合中
                User user =new User();
                user.setId(id);
                user.setDate(date);
                user.setMoney(money);
                user.setPerson(person);
                list.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn,ps,rs);
        }
        //将List集合存入request请求域中，进行转发
        request.setAttribute("list",list);
        request.getRequestDispatcher("/clist.jsp").forward(request,response);

    }
}
