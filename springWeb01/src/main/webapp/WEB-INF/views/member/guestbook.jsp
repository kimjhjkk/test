<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function guestbookWrite() {
		if(confirm("등록하시겠습니까?")) {
			var form = document.getElementById("writeForm");
			form.submit();
		}
	}
</script>

<script>
	function guestbookDelete() {
		return confirm("삭제하시겠습니까?");
	}
</script>

<c:choose>
	<c:when test="${writeResult == true }">
		<script>alert("등록 완료");</script>
	</c:when>
	<c:when test="${writeResult == false }">
		<script>alert("등록 실패");</script>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${deleteResult == true }">
		<script>alert("삭제 완료");</script>
	</c:when>
	<c:when test="${deleteResult == false }">
		<script>alert("삭제 실패");</script>
	</c:when>
</c:choose>

</head>
<body>
	<h1>[ 방명록 ]</h1>
	<form action="/web/guestbook/write" id="writeForm" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend><input type="button" value="글쓰기" onclick="guestbookWrite()"> </legend>
			<p>작성자 : <input type="text" name="name"> </p>
			첨부파일 <input type="file" name="uploadFile"><br>
			내용<br> <textarea rows="3" name="content"></textarea><br>
			비밀번호 : <input type="password" name="pwd"><br>
		</fieldset>		
	</form>
	
	
	<form action="/web/guestbook/guestbookList" method="get">
		<input type="hidden" name="searchItem" value="name">
		작성자<input type="text" name="searchKeyword">
		<input type="submit" value="검색">
	</form>

	<c:forEach items="${list }" var="guestbook">
		<fieldset>
			<legend>${guestbook.seq }</legend>
			<p>작성자 : ${guestbook.name }</p>
			<p>
				작성일 : 
				<fmt:parseDate value="${guestbook.regdate }" var="parsedRegDate" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate value="${parsedRegDate }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				첨부파일 <a href="/web/guestbook/download?seq=${guestbook.seq }">${guestbook.originalFilename }</a>
			</p>
			<pre>${guestbook.content }</pre>
			<form action="/web/guestbook/delete" method="post">
				비밀번호 : <input type="password" name="pwd"><br>
				<input type="hidden" name="seq" value="${guestbook.seq }">
				<input type="submit" value="글 삭제" onclick="return guestbookDelete()"><br><br>
			</form>
		</fieldset>
	</c:forEach>
	
	<br><h3><a href="/web/member/returnLogin">나가기</a></h3>
</body>
</html>