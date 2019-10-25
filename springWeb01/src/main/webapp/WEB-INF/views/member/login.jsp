<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<fieldset style="width: 300px; margin-left: auto; margin-right: auto; text-align: left">
		<legend><h2>[ 로그인 ]</h2></legend>
			<form action="/web/member/loginSuccess" method="post">
			ID : <input type="text" name="id"><br>
			Password : <input type="password" name="pwd"><br>
			<input type="submit" value="로그인">
		</form>
		<c:if test="${id != null}">
			ID 또는 Password가 일치하지 않습니다
		</c:if>
	</fieldset>
</body>
</html>