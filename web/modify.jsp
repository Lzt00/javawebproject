<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modify</title>
</head>
<script>

</script>
<body>
<%--  接收到的数据，做页面展示 ${user}--%>
<form action="${pageContext.request.contextPath}/cmodify" method="post">
             账单编号：<input type="text" readonly name="id" value="${user.id}">
    <br>
            账单日期：<input type="text" name="date" value="${user.date}">
    <br>

    消费金额：<input type="text" name="money" value="${user.money}">    <br>

    消费人：<input type="text" name="person" value="${user.person}">
    <br>


    <input type="submit" value="保存" >
</form>
</body>
</html>
