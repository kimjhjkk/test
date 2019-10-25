<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="/web/resources/css/default.css" />
<style type="text/css">
#ttrc {
	color : gray;
}
</style>
<script>
	function pageProc(currentPage, searchItem, searchKeyword) {
		location.href="/web/board/boardList?currentPage=" + currentPage + 
						"&searchItem=" + searchItem + "&searchKeyword=" + searchKeyword;
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
	<table>
		<tr>
			<td class="please" colspan="2" align="left" valign="bottom">
				<h1>[ 게시판 ]</h1>
			</td>
			<td class="please" colspan="2" valign="bottom">
				<p id="ttrc"> 검색된 게시물 ${navi.totalRecordsCount }개</p>
			</td>
			<td class="please">
				<br><h2><a href="/web/member/returnLogin">나가기</a></h2>
			</td>
		</tr>
		<tr>			
			<td class="please" colspan="4">
				<form action="/web/board/boardList" method="get">
					<select name="searchItem">
						<option value="title" <c:if test="${searchItem == 'title' }">selected="selected"</c:if>>제목</option>
						<option value="userid" <c:if test="${searchItem == 'userid' }">selected="selected"</c:if>>작성자</option>
						<option value="content" <c:if test="${searchItem == 'content' }">selected="selected"</c:if>>내용</option>
					</select>
					<input type="text" name="searchKeyword" value="${searchKeyword }">
					<input type="submit" value="검색">			
				</form>
			</td>
			<td class="right">
				<a href="/web/board/boardWrite"><img src="/web/resources/img/write_64.png"></a>
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회</th>
			<th>날짜</th>
		</tr>
		<c:forEach items="${list }" var="board">
			<fmt:parseDate value="${board.inputdate }" var="inputdate" pattern="yyyy-MM-dd HH:mm:ss"/>
			<tr>
				<td class="center">${board.boardNum }</td>
				<td class="center">${board.userId }</td>
				<td id="title">
					<a href="/web/board/boardRead?num=${board.boardNum }"> ${board.title } </a>
				</td>
				<td class="center">${board.hit }</td>
				<td id="inputdate">
					<fmt:formatDate value="${inputdate }" pattern="yyyy년 MM월 dd일"/>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td id="navigator" colspan="5">
				<a href="javascript:pageProc(${navi.currentPage - navi.pagePerGroup}, '${searchItem}', '${searchKeyword}')">
				◁◁ </a> &nbsp;&nbsp;
				<a href="javascript:pageProc(${navi.currentPage - 1}, '${searchItem}', '${searchKeyword}')">
				◀</a> &nbsp;&nbsp;
				<c:forEach var="counter" begin="${navi.startPageGroup}" end="${navi.endPageGroup}"> 
					<c:if test="${counter == navi.currentPage}"><b></c:if>
						<a href="javascript:pageProc(${counter}, '${searchItem}', '${searchKeyword}')">${counter}</a>&nbsp;
					<c:if test="${counter == navi.currentPage}"></b></c:if>
				</c:forEach>
				&nbsp;&nbsp;
				<a href="javascript:pageProc(${navi.currentPage + 1}, '${searchItem}', '${searchKeyword}')">
				▶</a> &nbsp;&nbsp;
				<a href="javascript:pageProc(${navi.currentPage + navi.pagePerGroup}, '${searchItem}', '${searchKeyword}')">
				▷▷</a>
			</td>
		</tr>
		
	</table>
</body>
</html>