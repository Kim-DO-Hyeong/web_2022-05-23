/**
 * 
 */
 
 function getParameter(name){
	let parameters = location.search.substr(1);
	// 파라미터를 & 으로 나눔
	parameters= parameters.split("&");


	 for (let i = 0; i < parameters.length; i++) {
		 let parameter = parameters[i];
		 // 파라미터를 = 을 기준으로 나눔 
		 parameter = parameter.split("=");
		 let paramName = parameter[0];
		 let paramValue = parameter[1];
		 
//		 if(매개 변수로 전달한 파라미터의 이름과 n번째 파라미터의 이름이 같다면){
		if(name == paramName){	
			return paramValue;
		}
	 }
		
	return undefined;
}
 

function setHeader(loginUserInfo){
	// 서버로 부터 매개변수를 전달 받았다면
	if(loginUserInfo != undefined){		 
		// 1. 아이디, 비밀번호 입력란을 지운다.
		$("#id").remove();
		$("#pw").remove();
		
		// 2. 아이디, 비밀번호 입력란이 있던 자리에 로그인 한 사용자의 이름을 출력한다 
		$("#login_area form").prepend("<span>"+loginUserInfo.loginUserName+"님 환영합니다</span>");
	
		// 3. 로그인 버튼을 지운다 
		$("#login_btn").remove();
		// 4. 로그아웃 버튼을 넣는다
		$("#login_area form").append("<button type=\"button\" id=\"logout\">로그아웃</button>");
		
		$("#logout").on("click",function(){
			location.href="/web/member/logout"
		});
		
		// 5. 회원가입 버튼을 지운다
		$("#join_btn").remove();
		// 6 관리자로 로그인 했다면 
		if(loginUserInfo.loginUserId == "admin"){
			$("#join_area").append("<button type=\"button\" id=\"notice\">공지사항 작성</button>");
			
			$("#join_area").on("click",function(){
				location.href="/web/notice/form.jsp";
			});
		}
		
		
	}
} 

 