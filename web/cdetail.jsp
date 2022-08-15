<%@page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>详情</h1>

    <form action="${pageContext.request.contextPath}/clist">
        账单日期: <input type="text" value=" ${user.date}"><br>
        <br>

        消费金额: <input type="text" value="${user.money}" ><br>
        <br>
        消 费 人: <input type="text" value="${user.person}"><br>
        <br>
        <input type="submit" value="返回">
    </form>
</body>
</html>