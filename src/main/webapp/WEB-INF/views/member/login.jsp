<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="<c:url value='/resources/css/member/login.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>로그인</h3>
			</div>
			<div class="login_form">
				<form action="<c:url value='/login'/>" method="post">
					<input type="text" name="user_id"><br>
					<input type="password" name="user_pw"><br>
					<input type="checkbox" name="remember-me" id="remember-me">
					<label id="remember-me">로그인 유지</label><br>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
	</section>
</body>
</html>