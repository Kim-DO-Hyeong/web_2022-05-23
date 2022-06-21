package notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeTblDao;
import dto.NoticeInfo;

/**
 * Servlet implementation class Write
 */
@WebServlet("/notice/write")
public class NoticeWrite extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String title = (String) request.getParameter("title");
		String contents = (String) request.getParameter("contents");
		
		NoticeInfo newNoticeInfo = new NoticeInfo(title,contents);
		
		// 3. 공지사항 정보 목록에 공지사항 정보를 저장한다 
		NoticeTblDao dao =new NoticeTblDao();
		dao.writeNoticeInfo(newNoticeInfo);
		
		response.sendRedirect("/web/notice/list?pageNumber=1");
	}

}
