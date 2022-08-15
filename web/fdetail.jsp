
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成员详情</title>
</head>
<body>
    id<input type="text" value="${user.id}" readonly><br><br>
    姓名<input type="text" value="${user.name}"><br><br>
    <input type="button" onclick="window.history.back()" value="返回">
</body>
</html>
