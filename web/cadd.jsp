<%@page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>增加账单</title>
</head>
<body>
    <h1>增加账单</h1>
    <form action="${pageContext.request.contextPath}/cadd" method="post">
        账单日期: <input type="text" name="date"><br>
        <br>
       消费金额: <input type="text" name="money"><br>
        <br>
        消 费 人: <input type="text" name="person"><br>
        <br>
        <input type="submit" value="保存">
    </form>
</body>
</html>