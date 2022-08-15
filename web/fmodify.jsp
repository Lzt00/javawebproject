<%--
  Created by IntelliJ IDEA.
  User: lizetao
  Date: 2022/8/12
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/fmodify">
    id<input type="text" name="id" value="${user.id}" readonly><br><br>
    姓名<input type="text" name="name" value="${user.name}"><br><br>
<input type="submit"  value="保存">
</form>
</body>
</html>
