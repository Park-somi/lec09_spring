<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link href="<c:url value='/resources/css/board/list.css'/>" rel="stylesheet"
type="text/css"/>
<link href='<c:url value="/resources/css/include/paging.css"/>' rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 목록</h3>
			</div><br>
			<div class="book_list">
				<table>
					<colgroup>
						<col width="10%">
						<col width="40%">
						<col width="20%">
						<col width="20%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>내용</th>
							<th>작성일자</th>
							<th>상세</th>
						</tr>
					</thead>
					<tbody>
						<!-- 
							resultList가 비어있다면 -> "게시글이 없습니다."
							비어있지 X -> "아래와 같이 출력"
							1 || 게시글 제목 || 게시글 내용 || 작성일자(yy-MM-dd) || a태그
							a태그 경로 : /board/게시글pk
						-->
						<c:choose>
							<c:when test="${empty resultList }">
								<tr>
									<td colspan="5">게시글이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${resultList }" var="b" varStatus="status">
									<tr>
										<td><c:out value="${paging.numPerPage*(paging.nowPage-1)+status.count}"/></td>
										<td><c:out value="${b.board_title }"/></td>
										<td><c:out value="${b.board_content }"/></td>
										<td><fmt:formatDate pattern="yy-MM-dd" value="${b.reg_date }"/></td>
										<td>
											<a href="<c:url value='/board/${b.board_no }'/>">
												상세
											</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<br><br><br>
				<c:if test="${not empty paging}">
					<div class="center">
					  <div class="pagination">
					  	<c:if test="${paging.prev}">
					  		<a href="<c:url value='/board?nowPage=${paging.pageBarStart-1}&search_text=${paging.search_text }&search_type=${paging.search_type }'/>">&laquo;</a>
					  	</c:if>
					  	<c:forEach begin="${paging.pageBarStart}" end="${paging.pageBarEnd}" var="idx">
					  		<a href="<c:url value='/board?nowPage=${idx}&search_text=${paging.search_text }&search_type=${paging.search_type }'/>" <c:out value="${paging.nowPage == idx ? 'class=active' : '' }"/>>${idx}</a>
					  	</c:forEach>
						<c:if test="${paging.next}">
						  <a href="<c:url value='/board?nowPage=${paging.pageBarEnd+1}&search_text=${paging.search_text }&search_type=${paging.search_type }'/>">&raquo;</a>
						</c:if>
					  </div>
					</div>
				</c:if>
				<input type="button" value="등록" style="float:right;"
				onclick="location.href='<c:url value="/board/create"/>'">
			</div>
		</div>
	</section>	
</body>
</html>