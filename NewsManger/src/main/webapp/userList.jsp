<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
</head>
<body>
<table width="50%" border="1px">
	<c:forEach var="user" items="${userList}">
		<tr>
			<td>${user.id}</td>
			<td>${user.username}</td>
			<td>${user.password}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>