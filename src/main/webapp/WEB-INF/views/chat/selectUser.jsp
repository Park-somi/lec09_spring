<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅 상대방 선택</title>
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
				<h3>사용자 목록</h3>
			</div><br>
			<div class="book_list">
				<table>
					<colgroup>
						<col width="10%">
						<col width="45%">
						<col width="45%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>사용자</th>
							<th>1:1채팅</th>
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
									<td colspan="3">사용자가 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${resultList }" var="b" varStatus="status">
									<tr>
										<td><c:out value="${status.count}"/></td>
										<td><c:out value="${b.user_name}"/></td>
										<td>
											<a href="<c:url value='/chattingStart/${b.user_no}'/>">
												1:1채팅하기
											</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<br><br><br>
			</div>
		</div>
	</section>	
</body>
</html>