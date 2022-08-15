<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
    table,h1{
        text-align: center;
    }
</style>
<script>
function del(id){

var ok=window.confirm('亲。确定要删除吗');
if(ok){
    document.location.href='${pageContext.request.contextPath}/fdel?id='+id;
}
}

</script>

<body>
欢迎用户${sessionScope.username}!
    <h1>家庭成员明细</h1>
    <table border="1px" align="center" width="50%">
        <tr>
            <th>成员编号</th>     
            <th>成员姓名</th>   
            <th>操作</th>    
        </tr>

<%-- 下面是动态展示的 --%>

        <c:forEach items="${fuser}" var="user">


        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>
                <a href="javascript:0" onclick="window.del(${user.id})">删除</a>
                <a href="${pageContext.request.contextPath}/fdetail?f=m&id=${user.id}">修改</a>
                <a href="${pageContext.request.contextPath}/fdetail?f=d&id=${user.id}">详情</a>
            </td>
        </tr>
        </c:forEach>
<%-- 上面是动态展示的 --%>
    </table>
<br><br>
    <a href="${pageContext.request.contextPath}/fadd.jsp">增加家庭成员</a>&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="${pageContext.request.contextPath}/clist">查看账单明细</a>
</body>
</html>