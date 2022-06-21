<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/member_join.css">
</head>
<body>
	<div id="wrapper">
        <h3>회원가입</h3>
		
		<form action="/web/member/join" method="POST">
			<div>
				<label>아이디&nbsp;&nbsp;&nbsp;&nbsp;: <input type="text" name="id" id="id"></label>
			</div>
			
			<div>
				<label>비밀번호 : <input type="password" name="pw" id="pw"></label>
			</div>
			
			<div>
				<label>이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <input type="text" name="name" id="name"></label>
			</div>
			
			<input type="submit" value="회원가입" id="join_btn">
		</form>
	</div>
	
	<%@ include file="../includes/footer.jsp" %>

	<script src="../js/jquery-3.6.0.min.js"></script>
	<script>
	// 회원가입 버튼을 클릭했을 때 
	$("#join_btn").on("click",function(e){
		e.preventDefault();
		// 1. 사용자가 입력한 아이디, 비밀번호, 이름을 가져온다 
		let $id = $("#id").val();
		let $pw = $("#pw").val();
		let $name = $("#name").val();
		
		// 아이디 규칙을 정하고 규칙에 맞는 아이디 인지 검증하는 코드 
		$id=$id.trim();
		if($id.length == 0){
			alert("아이디는 공백문자가 들어갈 수 없습니다 ");
			return false;
		}
		// 비밀 번호 규칙을 정하고 규칙에 맞는 비밀번호 인지 검증하는 코드 
		
		// 이름 규칙을 정하고 규칙에 맞는 이름인지 검증하는 코드 
		
		// 2. ajax 를 사용해서ㅏ 회원가입 서비스를 요청한다
		// 	 	 이때, 가져온 사용자가 입력한 아이디, 비밀번호, 이름을 회원가입 서비스로 보낸다 
		$.ajax({
			url:"/web/member/join/temp",
			type:"POST",
			data:"id="+$id+"&pw="+$pw+"&name="+$name,
			// 3. 회원가입 서비스가 성공을 의미하는 상태코드로 응답하면 회원가입 완료 페이지로 이동
			success:function(){
				location.href="/join/success";
			},
			// 4. 회원 가입 서비스가 실패를 의미하는 상태코드로 응답하면 alert 로 "이미 사용중인 아이디입니다 입력"
			error:function(){
				alert("이미 사용중인 아이디 입니다");
			}	
		});
	});	
		
	</script>
</body>
</html>