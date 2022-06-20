<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/main_index.css">
</head>
<body>
	<%@ include file="../includes/header.jsp" %>

	<main>
		<div id="notice_title">
			<h2>공지사항</h2>
			<a href="/web/notice/list?pageNumber=1"> [ 더보기 ] </a>
		</div>
		
		<c:choose>
			<c:when test="${noticeAmount eq 0 }">
				<div id="notice_list">공지사항이 없습니다.</div>
			</c:when>
			<c:otherwise>
				<div id="notice_list">
					<c:forEach var="noticeInfo" items="${noticeInfoList }"  >
						<div class="contents">
							<a href="/web/notice/detail.jsp?idx=${noticeInfo.noticeId }">
								<span class="t	itle">${noticeInfo.title}</span>
							</a>
						</div>
					</c:forEach>
				</div>	
			</c:otherwise>
		</c:choose>
		
		
	</main>
	
	<%@ include file="../includes/footer.jsp" %>
	
</body>
</html>