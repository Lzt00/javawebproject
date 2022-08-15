<%@ page import="java.util.List" %>
<%@ page import="com.lzt.web.bean.User" %>
<%@ page import="java.util.Iterator" %>
<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>账单明细</title>
    <style>
        table,h1{
            text-align: center;
    

        }
        
    </style>
<script type="text/javascript">

    function del(id){

        var ok=window.confirm('亲。确定要删除吗');
        if(ok){
            document.location.href="${pageContext.request.contextPath}/cdel?id="+id;
        }
    }
</script>

</head>
<body>
欢迎用户${sessionScope.user}!
    <h1>账单明细</h1>
    <table border="1px" align="center" width="50%" >
        <tr>
            <th>账单编号</th>
            <th>账单日期</th>
<%--            <th>消费类型</th>--%>
            <th>消费金额</th>
            <th>消费人</th>
            <th>操作</th>
        </tr>
<%--下面是动态展示的--%>

        <c:forEach items="${list}" var="s">


            <tr>
                <td>${s.id}</td>
                <td>${s.date}</td>
                <td>${s.money}</td>

                <td>${s.person}</td>
                <td>
                    <a href="javascript:0" onclick="window.del(${s.id})">删除</a>
                    <a href="${pageContext.request.contextPath}/cdetail?id=${s.id}&f=m">修改</a>
                    <a href="${pageContext.request.contextPath}/cdetail?id=${s.id}&f=d">详情</a>
                </td>
            </tr>

        </c:forEach>





 <%--上面是动态展示的--%>
    </table>
    <br><br>
<a href="cadd.jsp">增加账单</a>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/flist">查看家庭成员</a>
</body>
</html>