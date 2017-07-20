<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: yichengjie
  Date: 2017/7/18
  Time: 上午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="tag.jsp"%>
<html>
<head>
    <title>登陆</title>
    <%
        String validateCode = "1234" ;
        session.setAttribute("validateCode",validateCode);
    %>
</head>
<body>


<form action="${pageContext.request.contextPath}/api/login.action" method="post">
    <input type="text" name="username" placeholder="用户名" /><br>
    <input  type="text" name="password" placeholder="密码"/> <br/>
    <input type="text" name="randomCode" value="${sessionScope.validateCode}"/><br>
    <input type="checkbox" name="rememberMe" value="true" />记住我<br>
    <button type="submit">登陆</button>
</form>

</body>
</html>
