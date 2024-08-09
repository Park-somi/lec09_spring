<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="<c:url value='/resources/css/member/join.css' />" rel="stylesheet" type="text/css">
</head>
<body>	
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>회원가입</h3>
			</div><br>
			<div class="create_account_form">
				<form id="memberAddFrm">
					<input type="text" name="user_id" placeholder="아이디"> <br>
					<input type="password" name="user_pw" placeholder="비밀번호"> <br>
					<input type="password" name="user_pw_check" placeholder="비밀번호 확인"> <br>
					<input type="text" name="user_name" placeholder="이름"> <br>
					<input type="submit" value="회원가입">
				</form>
			</div>
			<div class="login">
				<a href="<c:url value='/loginPage' />">로그인</a>
			</div>
		</div>
	</section>
	<script>
		const form = document.getElementById('memberAddFrm');
		form.addEventListener('submit',(e)=>{
			e.preventDefault();
			let vali_check = false;
			let vali_text = "";
			if(form.user_id.value == ""){
				vali_text += '아이디를 입력하세요.';
				form.user_id.focus();
			} else if(form.user_pw.value == ""){
				vali_text += '비밀번호를 입력하세요.';
				form.user_pw.focus();
			} else if(form.user_pw_check.value == ""){
				vali_text += '비밀번호 확인을 입력하세요.';
				form.user_pw_check.focus();
			} else if(form.user_name.value == ""){
				vali_text += '이름을 입력하세요.';
				form.user_name.focus();
			} else {
				vali_check = true;
			}
			
			if(vali_check == false){
				alert(vali_text);
			} else {
				// form 데이터는 순수한 객체 데이터가 아니므로 객체 데이터(key,value)로 바꿔줌
				let object = {};
				const payload = new FormData(form);
				payload.forEach(function(value,key){
					object[key] = value;
				});
				// JSON 데이터로 바꿔줌
				const jsonData = JSON.stringify(object);
				
				fetch('<%=request.getContextPath()%>/join',{
					method : 'POST',
					// spring-security 사용 시 쓰는 약속
					headers : {
						"Content-Type" : "application/json;charset=utf-8",
						"Accept" : "application/json",
						"X-CSRF-TOKEN" : "${_csrf.token}"
					},
					body : jsonData
				})
				.then(response => response.json())
				.then(data =>{
					alert(data.res_msg);
					if(data.res_code == '200'){
						location.href="<%=request.getContextPath()%>/loginPage";
					}
				})
			}
		})
	</script>	
</body>
</html>







