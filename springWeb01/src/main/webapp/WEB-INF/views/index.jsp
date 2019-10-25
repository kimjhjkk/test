<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Parameter 전송</title>
</head>
<body>
	<a href="send5">Model 객체 실습</a><br>
	<c:if test="${data != null && vo != null }">
		test : ${data } <br>
		vo.a : ${vo.a } <br>
		vo.b : ${vo.b } <br>
		requestScope.data : ${requestScope.data } <br>
		requestScope.vo.a : ${requestScope.vo.a } <br>
		requestScope.vo.b : ${requestScope.vo.b } <br>
	</c:if >

	<img src="resources/img/냥냥.jpg" />
	<img src="/web/resources/img/냥냥.jpg" />
	<img src='<c:url value="/resources/img/냥냥.jpg" />' />
	<ul>
		<li>
			<h2><a href="send1?a=테스트&b=1234">a 태그로 전송</a></h2>
		</li>
		<li>
			<h2>form 태그로 전송(get)</h2>
			<form action="send2" method="get">
				<input type="text" name="a"><br>
				<input type="text" name="b"><br>
				<input type="text" name="c"><br>
				<input type="submit" value="전송하기"><br>
			</form>
		</li>
		<li>
			<h2>form 태그로 전송(post)</h2>
			<form action="send2" method="post">
				<input type="text" name="a"><br>
				<input type="text" name="b"><br>
				<input type="text" name="c"><br>
				<input type="submit" value="전송하기"><br>
			</form>
		</li>
		<li>
			<h2><a href="send4?a=테스트&b=1231">a 태그(VO)</a></h2>
		</li>
	</ul>
</body>
</html>