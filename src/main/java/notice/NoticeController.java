package notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeTblDao;
import dto.NoticeInfo;

@WebServlet("/notice/list")
public class NoticeController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 등록된 공지사항 개수를 DB 에서 가져와 요청 정보에 저장 
		NoticeTblDao dao = new NoticeTblDao();
		
		int noticeAmount = dao.getNoticeAmount();
		
		request.setAttribute("noticeAmount", noticeAmount);
		
		// 2. 페이지 번호에 맞는 공지사항 목록을 DB 에서 가져와 요청정보에 저장 
		if(noticeAmount != 0) {
			
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	
			pageNumber =( pageNumber-1)*5;
			
			List<NoticeInfo> noticeInfoList = dao.getNoticeInfoList(pageNumber);
			
			request.setAttribute("noticeInfoList", noticeInfoList);
			
 
				
		}	
		
		RequestDispatcher rd = request.getRequestDispatcher("/notice/list.jsp");
		rd.forward(request, response);
					
		
	}
}
