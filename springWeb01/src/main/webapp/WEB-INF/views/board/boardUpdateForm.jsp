<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/default.css" />" />

<script>
	function boardUpdate() {
		if(confirm("수정하시겠습니까?")) {
			var form = document.getElementById("updateForm");
			form.submit();
		}
	}
</script>

</head>
<body>
<script></script>
<h1>[ 글 수정 ]</h1>
<form action="/web/board/update" id="updateForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="boardNum" value="${vo.boardNum }">
	<input type="hidden" name="userId" value="${vo.userId }">
	<input type="hidden" name="inputdate" value="${vo.inputdate }">
	<input type="hidden" name="hit" value="${vo.hit }">
	<input type="hidden" name="savedFilename" value="${vo.savedFilename }">
	<input type="hidden" name="originalFilename" value="${vo.originalFilename }">
	<table>
		<tr>
			<th>번호</th>
			<td>${vo.boardNum }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${vo.userId }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${vo.inputdate }</td>
		</tr>
		<tr>
			<th>조회</th>
			<td>${vo.hit }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${vo.title }"></td>
		</tr>
		<tr>
			<th>첨부 파일</th>
			<td>
				<a href="/web/board/download?num=${board.boardNum }">${vo.originalFilename }</a>
				<input type="file" name="uploadFile">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content">${vo.content }</textarea></td>
		</tr>
		<tr>
			<td class="right" colspan="2">
				<input type="button" value="수정" onclick="boardUpdate()">
				<a href="/web/board/boardRead?num=${vo.boardNum }"><input type="button" value="취소"></a>
			</td>
		</tr>
	</table>
	
</form>
</body>
</html>
