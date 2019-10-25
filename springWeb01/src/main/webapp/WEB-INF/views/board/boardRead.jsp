<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title}</title>
<link rel="stylesheet" type="text/css" href="/web/resources/css/default.css" />


<c:choose>
	<c:when test="${updateResult == true }">
		<script>alert("수정 완료");</script>
	</c:when>
	<c:when test="${updateResult == false }">
		<script>alert("수정 실패");</script>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${deleteReplyResult == true }">
		<script>alert("삭제 완료");</script>
	</c:when>
	<c:when test="${deleteReplyResult == false }">
		<script>alert("삭제 실패");</script>
	</c:when>
</c:choose>

<script>
	function boardDelete() {
		if(confirm("삭제하시겠습니까?")) {
			var form = document.getElementById("deleteForm");
			form.submit();
		}
	}
</script>

<script>
	function replyWrite() {
		var replytext = document.getElementById("replytext");
		if(replytext.value.length == 0) {
			alert("댓글을 입력하세요");
			return;
		}
		document.getElementById("replyWrite").submit();
	}
</script>

<script>
	function replyModify(replyNum, text) {
		document.getElementById("replytext").value = text;
		document.getElementById("replysubmit").value = "댓글 수정";
		
		document.getElementById("replysubmit").onclick = function() {
			if(document.getElementById("replytext").value == 0) {
				alert("댓글을 입력하세요");
				return;
			}
			var updatetext = document.getElementById("replytext").value;
			location.href = "/web/board/replyUpdate?replyNum="+replyNum+"&boardNum="+"${board.boardNum}"
					+"&replyText="+updatetext;
		}
	}
</script>

<script>
	function replyDelete(replyNum) {
		if(confirm("삭제하시겠습니까?")) {
			location.href = "/web/board/replyDelete?replyNum="+replyNum+"&boardNum="+"${board.boardNum}";
		}
	}
</script>

<fmt:parseDate value="${board.inputdate }" var="inputdate" pattern="yyyy-MM-dd HH:mm:ss"/>

</head>
<body>
	<h1>[ 글 읽기 ]</h1>
	<table>
		<tr>
			<td class="right" colspan="2">
			<form action = "/web/board/delete" id="deleteForm" method="post">
				<c:if test="${board.userId == sessionScope.id}">
					<a href="/web/board/boardUpdate?num=${board.boardNum }"><input type="button" value="수정"></a>				
					<input type="hidden" name="num" value="${board.boardNum }">
					<input type="button" value="삭제" onclick="boardDelete()">					
				</c:if>
				<a href="/web/board/boardList"><input type="button" value="목록"></a>
			</form>
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<td>${board.boardNum}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.userId}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>
				<fmt:formatDate value="${inputdate }" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
			</td>
		</tr>
		<tr>
			<th>조회</th>
			<td>${board.hit}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.title}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><a href="/web/board/download?num=${board.boardNum }">${board.originalFilename }</a></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea readonly="readonly">${board.content}</textarea></td>
		</tr>
	</table>
	
	<form action="/web/board/replyWrite" id="replyWrite" method="post">
		<table id="replyinput">
			<tr>
				<td>
					<input type="hidden" name="boardNum" value="${board.boardNum }">
					<input type="text" name="replyText" id="replytext">
					<input type="button" id="replysubmit" value="댓글 입력" onclick="replyWrite()">
				</td>
			</tr>
		</table>
	</form>
	
	<div>
		<table class="reply">
			<c:forEach items="${replyList }" var="reply">
				<tr>
					<td class="replytext">
						${reply.replyText }
					</td>
					<td class="replyid">
						${reply.userId }
					</td>
					<td class="replydate">
						${reply.inputdate }
					</td>
					<td>
						<c:if test="${sessionScope.id == reply.userId }">
							<input type="button" value="삭제" onclick="replyDelete('${reply.replyNum}')">
							<input type="button" value="수정" 
								onclick="replyModify('${reply.replyNum}', '${reply.replyText }')">
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>
