<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/functions.js"></script>
<header>
	<div id="login_area">
		<form action="#" method="POST">
			<%-- 로그인 한 상태라면 로그인한 상태의 헤더를 출력  --%>
			<c:choose>
				<c:when test="${loginUserId ne null }">
					<span>${loginUserName } 님 환영합니다</span>
					<button type="button" id="logout">로그아웃</button>
					<script>
						$("#logout").on("click",function(){
							location.href="/web/member/logout"
						});
						
						$("#join_btn").remove();
					</script>
				</c:when>
				<c:otherwise>
					<%-- 로그인 하지 않은 상태라면 로그인 하지않은 상태의 헤더를 출력  --%>
					<input type="text" name="id" id="id" placeholder="아이디">
					<input type="password" name="pw" id="pw" placeholder="비밀번호">
					<input type="submit" id="login_btn" value="로그인">
				</c:otherwise>
			</c:choose>

		</form>
	</div>
	<div id="join_area">
		<c:if test="${empty loginUserId }">
			<button type="button" id="join_btn">회원가입</button>
		</c:if>
		
		<c:if test="${loginUserId eq 'admin' }">
				<button type="button" id="notice">공지사항 작성</button>
					
				<script>
					$("#join_area").on("click",function(){
						location.href="/web/notice/form";
					});
				</script>
		</c:if>
		
		
	</div>
</header>

<script>
	 // header.jsp 안에 있는 스크립트 코드는 Jquery 가 추가되기 전에 동작하는데
	 // Jquery 가 추가되어 사용할 수 있는 코드들이 있으므로 
	 // 페이지 로드가 완료된 후에 동작하도록 해야함 
	 // 순수 JavaScript 만 사용해서 구현하면 Jquery 를 추가하는 코드를 닫는 body 바로 위에 넣을 수 있음 
	 // 순수 JavaScript 만 사용해서 구현하기 어려우므로 
	 // Jqeury 가 제공하는 페이지 로드가 완료된 후에 동작하도록 하는 코드를 사용 
	 // $(function (){페이지가 완료된 후 동작할 코드 });
	 
// 	$(function (){
// 		// 새로고침을 해도 로그인이 풀리지 않게 하기 위해서	
// 		$.ajax({
// 			url:"/web/login/status",
// 			type:"GET",
// 			data:"",
// 			success:function(loginUserInfo){
// 				// 헤더가 로그인 한 상태로 보여지게 
// 				if(loginUserInfo != undefined){
// 					setHeader(loginUserInfo);
// 				}
// 			},
// 			error:function(){
				
// 			}
			
// 		});

		// 새로고침을 해도 로그인이 풀리지 않게 하기 위해서
		
		
		// 회원가입 버튼을 클릭했을 때 
		// location.href 를 사용해서 
		// 회원가입 페이지로 이동하도록 하세요
		$("#join_btn").on("click",function(){
			$(location).attr("href","/web/member/join"); 
		});
		

		$("#login_btn").on("click",function(e){
			e.preventDefault();
			
			let $id = $("#id").val();
			let $pw = $("#pw").val();
			
			$.ajax({
				url:"/web/member/login",
				type:"POST",
				data:"id="+$id+"&pw="+$pw,
				success:function(loginUserInfo){
					setHeader(loginUserInfo);
				},
				error:function(){
					alert("아이디 또는 비밀번호를 입력하세요");
				}
			});
		});
	
// 	});
	 
	
	
		
</script>