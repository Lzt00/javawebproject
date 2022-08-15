<%@page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录界面</title>
<style>
    h1{
        text-align: center;
    }
</style>
</head>
<body>
    <h1>家庭消费记录系统</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名: <input type="text" name="username"><br><br>
        密&nbsp;&nbsp;&nbsp;码: <input type="password" name="password">
        <br><br>
        <input type="submit" value="登录">
    </form>

</body>
</html>