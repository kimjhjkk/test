<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.id } 환영합니다</title>

</head>
	<link rel="shortcut icon" type="image⁄x-icon" href="/web/resources/img/냥냥.jpg">
<body>

	<c:if test="${sessionScope.id ==null }">
		<h1><a href="/web/test/testSession1">세션 등록</a></h1>
		<h1><a href="/web/test/testSession2">세션 삭제</a></h1>
		<h1><a href="/web/test/testInsert?a=가나다라&b=1234">TestInsert Test</a></h1>
		<h1><a href="/web/member/signupForm">회원가입</a></h1>
		<h1><a href="/web/member/login">로그인</a></h1>
		sessionScope : ${sessionScope.test }<br>
	</c:if>
	
	<c:if test="${sessionScope.id !=null }">
		<h1>${sessionScope.id }님 환영합니다!</h1>
		<h1><a href="/web/guestbook/guestbookList">방명록</a></h1>
		<h1><a href="/web/board/boardList">게시판</a></h1>
		<h1><a href="/web">로그아웃</a></h1>
		<br>
		<h1><a href="/web/resources/download/Detris_1.0.1.zip">Detris Download!</a></h1>
		<details>
			<summary style="font-size: 16pt; font-weight: bold;" 
				onmouseover="this.style.color='red'", onmouseout="this.style.color='black'">Detris 키 조작법</summary>
			<p style="font-weight: bold;">
				← → : 블럭 이동<br>
				↓ : 블럭 빠르게 내리기<br>
				space : 블럭 한번에 내리기<br>
				Q R : 블럭 좌우로 회전시키기<br>
				ESC : 강제종료
			</p>
		</details>
	</c:if>
	
	<br><br>
	<h1><a href="http://localhost:8888/web">너네 페이지로 돌아갑니다</a></h1>
	
	
	<c:if test="${ r == 1 }">
		<h3>가입 성공</h3>
		
	</c:if>
	
	<!-- 
	<c:if test="${ vo != null}">
		userid : ${vo.userid }<br>
		userpwd : ${vo.userpwd }<br>
		username : ${vo.username }<br>
		phone : ${vo.phone }<br>
		address : ${vo.address }<br>
		hobby : ${vo.hobby }<br>
		marital : ${vo.marital }<br>
	</c:if>
	 -->
	
</body>
</html>