<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/notice_list.css">
</head>
<body>
	<%@ include file="../includes/header.jsp" %>
	<div id="wrapper">
        <h2>공지사항</h2>

        <div id="notice_wrapper">
            <div id="title_info_wrapper">
                <span class="order">번호</span>
                <span class="title">제목</span>
            </div>
            <div id="list">
                <c:forEach items="${noticeInfoList }" var="nthNoticeInfo" varStatus="status">
                	<c:set var="order" value="${noticeAmount}"/>
                	
                	<div class="contents">
                		<span class="order">${order-status.index}</span>
                		<a href="/web/notice/detail.jsp?idx=">
                			<span class="title">${nthNoticeInfo.title}</span>
                			</a>
          			</div>
                </c:forEach>
            </div>
            <div class="pagination">
				<%-- 1. 요청 정보에 들어있는 등록된 공지사항 개수를 사용해서 페이지 네이션 출력 --%>
				<c:set var="lastPageNumber" value="${noticeAmount / 5 }" scope="request"/>
					<%
						double pageNum = (double) request.getAttribute("lastPageNumber");
						
						int pageCount = (int) Math.ceil(pageNum);
					
						request.setAttribute("pageCount", pageCount);
					%>
					<%-- end에는 값이 들어가야하는 자리 --%>
					<c:forEach var="pageNumber" begin="1" end="${pageCount }" step="1">
						<span>${pageNumber }</span>
					</c:forEach>
					
					<script>
						$(".pagination span").on("click",function(){
							// 현재 클릭한 페이지 번호를 가져옴
							let clickedPageNumber = $(this).text();
							console.log(clickedPageNumber);
							
							location.href="/web/notice/list?pageNumber="+clickedPageNumber;
						});
					</script>	
            </div>
        </div>
	</div>
	
	<%@ include file="../includes/footer.jsp" %>
	<script>
// 		$.ajax({
// 			url:"/web/notice/amount",
// 			type:"GET",
// 			data:"",
// 			success:function(noticeAmount){
// 				// 1번째 ajax 등록된 공지사항 개수를 불러와 페이지네이션을 출력하는 ajax
				
// 				// 한페이지에 5개씩 공지사항을 보여줄 예정
// 				let lastPageNumber = noticeAmount.amount / 5 ;
				
// 				// 공지사항이 나머지가 0으로 안나눠질때 페이지 하나씩 추가 
// 				let lastpage = noticeAmount.amount % 5;
// 				lastPageNumber = lastpage !=0 ? (lastPageNumber+1): lastPageNumber; 
// 				// 공지사항이 나머지가 0으로 안나눠질때 페이지 하나씩 추가 -end 
				
// 				// pagination 에 페이지 번호 생성 코드 
// 				for(let pageNumber =1 ; pageNumber <= lastPageNumber; pageNumber++){
// 					$(".pagination").append("<span>"+pageNumber+"</span>");
// 				}
// 				// pagination 에 페이지 번호 생성 코드 -end 
				
				
// 				// pagination 안에 페이지 번호를 클릭했을 때 
// // 				$(".pagination span").on("click",function(){
// // 					// 현재 클릭한 페이지 번호를 가져옴
// // 					let clickedPageNumber = $(this).text();
// // 					console.log(clickedPageNumber);
					
// // 					location.href="/web/notice/list.jsp?pageNumber="+clickedPageNumber;
// // 				});
// 				// pagination 안에 페이지 번호를 클릭했을 때 -end
// 				// 1번째 ajax -end
// 			},
			
// 			error:function(){
// 				console.log("에러가 발생함");
// 			}
// 		});
	
		// GET 파라미터를 꺼낸다
		let pageNumber=1;
		
		// get 파라미터가 있다면 if조건문 
		if( location.search.length > 0){
			let parameter = location.search.substr(1);
			parameter = parameter.split("=");
			
			pageNumber = parameter[1];
		}
		// GET 파라미터를 꺼낸다 -end 
				
		
		$.ajax({
			url:"/web/notice/list	",
			type:"GET",
			data:"pageNumber="+pageNumber,
			
			success:function(noticeInfo){
				// 2번째 ajax 페이지 번호에 맞는 공지사항 정보 목록을 불러와 출려하는 ajax
				console.log(noticeInfo);
				if(noticeInfo == undefined){
					// 작성된 공지사항이 없습니다 
				
					let tag = "<div class=\"contents\"><span class=\"order\"></span><a href=\"#\"><span class=\"title\">작성된 공지사항이 없습니다</span></a></div>";
					
					$("#list").append(tag);
				}else{
					// JSON 에 담겨있는 공지사항 정보들을 샘플에 맞게 출력 
					
					// 전체 공지사항의 개수 
					
					// 현재 페이지 
// 					let startNumber =전체 공지사항의 개수 -( pageNumber-1 )*5;	
					let startNumber = noticeInfo.amount -(pageNumber-1)*5;
					
					let t_tag ="";
					
					for(let i=0; i<noticeInfo.list.length;i++){
						let title = noticeInfo.list[i].title;
						let contents = noticeInfo.list[i].contents;
						let idx = noticeInfo.list[i].noticeID;
						
						let order = startNumber-i;
						// 해당 인데스를 구하는 코드 ( 상세페이지에서 보여줘야할 공지사항의 인덱스 )						
// 						let idx = noticeInfo.amount - startNumber+i;
						
						let tag = "<div class=\"contents\"><span class=\"order\">"+order+"</span><a href=\"/web/notice/detail.jsp?idx="+idx+"\"><span class=\"title\">"+title+"</span></a></div>";
						t_tag = t_tag +tag;
// 						$("#list").append(tag);
					}
					
					$("#list").html(t_tag);
				}
				
			},
			error:function(){
				console.log("에러가 발생하였음");
			}
		});
	</script>

</body>
</html>