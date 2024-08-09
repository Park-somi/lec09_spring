<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link href="<c:url value='/resources/css/board/create.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 수정</h3>
			</div><br>
			<div class="register_board_form">
				<form id="boardUpdateFrm">	
					<input type="text" name="board_title" value="${vo.board_title }"> <br>
					<input type="text" name="board_content" value="${vo.board_content }"> <br>
					<input type="file" name="file" value="${vo.ori_thumbnail }"><br>
					<span> ※ 이미지 파일을 수정하시려면 새로운 파일을 등록하세요. </span><br>
					<input type="submit" value="수정"> 
				</form>
			</div>
		</div>
	</section>
	<script>
		// 1. id가 boardUpdateFrm인 form태그 submit
		// 2. 기본 이벤트 차단
		// 3. 제목, 내용 입력 여부 확인
		// 4. 파일이 입력됐다면 -> 이미지인지 확인
		// 5. 유효성 검사가 완료되었다면 비동기 통신
		// 7. BoardApiController에 updateBoard 메소드 생성
		// 8. Board와 MultipartFile 출력
		
		const form = document.getElementById("boardUpdateFrm");
		form.addEventListener('submit',(e)=>{
			e.preventDefault();
			let vali_check = false; // 유효성 검사
			let vali_text = "";
			if(form.board_title.value == ""){
				vali_text += '제목을 입력하세요.';
				form.board_title.focus();
			} else if(form.board_content.value == ""){
				vali_text += '내용을 입력하세요.';
				form.board_content.focus();
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
			} else if(form.file.value == ""){
				vali_check = true;
			}
			
			if(vali_check == false){
				alert(vali_text);
			} else {
				const payload = new FormData(form);
				fetch('<%=request.getContextPath()%>/board/${vo.board_no}',{
					method : 'POST',
					body : payload
				})
				.then(response => response.json())
				.then(data => {
					alert(data.res_msg);
					if(data.res_code == '200'){
						location.href="<%=request.getContextPath()%>/board/${vo.board_no}";
					}
				})
			}
		})
	</script>
</body>
</html>