<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<link href="<c:url value='/resources/css/board/create.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 등록</h3>
			</div><br>
			<div class="register_board_form">
				<form id="boardAddFrm">	
					<input type="text" name="board_title" placeholder="게시글 제목을 입력하세요."> <br>
					<input type="text" name="board_content" placeholder="게시글 내용을 입력하세요."> <br>
					<input type="file" name="file"><br>
					<input type="submit" value="등록"> 
				</form>
			</div>
		</div>
	</section>
	<script>
		const form = document.getElementById("boardAddFrm");
		form.addEventListener('submit',(e)=>{
			// 모든 데이터가 입력이 되있을 때만 submit
			e.preventDefault();
			let vali_check = false; // 유효성 검사
			let vali_text = "";
			if(form.board_title.value == ""){
				vali_text += '제목을 입력하세요.';
				form.board_title.focus();
			} else if(form.board_content.value == ""){
				vali_text += '내용을 입력하세요.';
				form.board_content.focus();
			} else if(form.file.value == ""){
				vali_text += '이미지 파일을 선택하세요.';
				form.file.focus();
			} else if(form.file.value){
				const val = form.file.value;
				const idx = val.lastIndexOf('.'); // 확장자 앞의 .의 위치
				const type = val.substring(idx+1,val.length); // 확장자
				if(type == 'jpg' || type == 'jpeg' || type == 'png'){
					vali_check = true;
				} else {
					vali_text += '이미지 파일만 선택할 수 있습니다.';
					form.file.value = ''; // 파일 비워주기
				}
			}
			
			if(vali_check == false){ // 데이터가 비어있으면
				alert(vali_text);
			} else { // 데이터가 비어있지 않으면
				// fetch 작업(fetch API 사용)
				// 폼 데이터 수집, 비동기로 서버에 전송, 파일업로드할 때 사용
				const payload = new FormData(form);
				fetch('<%=request.getContextPath()%>/board',{
					method : 'POST',
					body : payload,
					headers : {
						"X-CSRF-TOKEN" : "${_csrf.token}"
					}
				})
				.then(response => response.json())
				.then(data => {
					alert(data.res_msg);
					if(data.res_code == '200'){
						location.href="<%=request.getContextPath()%>/board";
					}
				})
			}
			
		});
	</script>
</body>
</html>