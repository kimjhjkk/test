<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>

<link rel="stylesheet" type="text/css" href="/web/resources/css/default.css" />

<script>
	function boardWrite() {
		if(confirm("등록하시겠습니까?")) {
			var form = document.getElementById("writeForm");
			form.submit();
		}
	}
</script>

</head>
<body>
<script>
</script>

<h1>[ 글쓰기 ]</h1>
<form action="/web/board/write" id="writeForm" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>작성자</th>
			<td>${sessionScope.id }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>첨부 파일</th>
			<td><input type="file" name="uploadFile"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content"></textarea></td>
		</tr>
		<tr>
			<td class="right" colspan="2">
				<input type="button" value="확인" onclick="boardWrite()">
				<a href="/web/board/boardList"><input type="button" value="취소" ></a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
