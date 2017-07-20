<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="tag.jsp"%>
<html>
<head>
    <title>首页</title>
</head>
<body>

我的菜单：
<ul>
    <c:forEach var="menu" items="${activeUser.menus}">
        <li>${menu}</li>
    </c:forEach>
</ul>
我的权限：
<ul>
    <c:forEach var="permission" items="${activeUser.permissions}">
        <li>${permission}</li>
    </c:forEach>
</ul>

<h2>Hello World !</h2>
<%@include file="common.inc"%>
<br>
<a href="${baseurl}/welcome.jsp">欢迎页面(已登陆，或则登录后记住我)</a><br>
<a href="${baseurl}/userEdit.jsp">用户编辑页面[user:edit]</a><br>
<a href="${baseurl}/api/userDelete.action">用户删除action[user:delete]</a>
</body>
</html>
