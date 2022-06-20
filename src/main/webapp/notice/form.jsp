<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/notice_form.css">
</head>
<body>
	<%@ include file="../includes/header.jsp" %>	
	<div id="wrapper">
        <h2>공지사항</h2>

		<form action="/web/notice/write" method="POST">
			
			<div id="title_wrapper">
				<label>제목&nbsp;&nbsp;&nbsp;&nbsp;: <input type="text" name="title"></label>
			</div>
			
			<div id="contents_wrapper">
				<label>내용<br><textarea name="contents" cols="100" rows="10"></textarea> </label>
			</div>
			
			<div>
				<label>첨부파일 : <input type="file" name="file"></label>
			</div>

	        <div id="btn_wrapper">
	            <button type="submit">공지사항 작성</button>
	        </div>
        </form>
	</div>
	
	<%@ include file="../includes/footer.jsp" %>
	<script>
	// 수정 페이지라면 
	if(location.search.length > 0){
		let idx = getParameter("idx");
		// 공지사항 페이지가 수정페이지가 되도록 
		
		$("#btn_wrapper button").text("공지사항 수정");
		// POST 방식에 hidden 태그를 활용해 보내는 방법 !!
		$("form").append("<input type=\"hidden\" name=\"idx\" value=\""+idx+"\">");
		$("form").attr("action","/web/notice/update");
		
		// idx 파라미터를 사용해서 수정할 공지사항의 정보를 불러온다
		
		// 수정하기 위해 공지사항 정보를 불러오는 ajax 
		$.ajax({
// 			url:"공지사항의 상세정보를 불러오도록",
			url:"/web/notice/detail",
			type:"GET",
			data:"idx="+idx,
			success:function(noticeInfo){
				$("#title_wrapper input").val(noticeInfo.title);
				$("#contents_wrapper textarea").text(noticeInfo.contents);
			},
			error:function(){}
		});
		// 수정하기 위해 공지사항 정보를 불러오는 ajax -end		
	}
	
	</script>
</body>
</html>