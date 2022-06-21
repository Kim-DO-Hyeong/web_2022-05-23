package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeTblDao;
import dto.NoticeInfo;
import util.URL;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main/")
public class MainController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메인페이지에 필요한 데이터를 불러온다 
		// 1. 최근에 등록된 공지사항 5개 
		// 2. 로그인 상태 정보를 꺼낸다. 

		NoticeTblDao dao = new NoticeTblDao();
		
		List<NoticeInfo> noticeInfoList = dao.getNoticeInfoList(0);
		
		// 불러온 요청정보 영역에 저장한다
		// 불러온 요청정보 영역에 저장할때 noticeInfoList 이름에 담아서 저장하세요
		
		// 등록된 공지사항의 개수를 요청정보 영역에 저장한다
		
		request.setAttribute("noticeAmount", dao.getNoticeAmount());
		request.setAttribute("noticeInfoList", noticeInfoList);
		
		// 메인페이지로 포워딩 한다
		RequestDispatcher rd = request.getRequestDispatcher(URL.MAIN_PAGE);
		rd.forward(request, response);
		
	}

}
