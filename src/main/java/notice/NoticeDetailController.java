package notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeTblDao;
import dto.NoticeInfo;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idx = Integer.parseInt(request.getParameter("idx"));
		
		String requestPage = request.getParameter("requestPage");
		
		NoticeTblDao dao = new NoticeTblDao();
		
		NoticeInfo noticeInfo = dao.selectedNoticeInfoByIdx(idx);
		
		request.setAttribute("noticeInfo", noticeInfo);
		
		String forwardingURI = "/notice/detail.jsp";
		if(requestPage != null && requestPage.equals("update")) {
			forwardingURI = "/notice/form.jsp";
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardingURI);
		rd.forward(request, response);
		
		
	}

}
