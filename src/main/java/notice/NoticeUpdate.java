package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.NoticeTblDao;
import dto.NoticeInfo;
import util.DatabaseManager;

@WebServlet("/notice/update")
public class NoticeUpdate extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 공지사항 정보 목록에서 idx 에 맞는 공지사항 정보를 
		// 클라이언트가 전달한 새로운 공지사항 정보로 바꾼다.
		
		NoticeInfo updateNoticeInfo = new NoticeInfo(idx,title, contents);
		
		NoticeTblDao dao = new NoticeTblDao();
		
		dao.updatedNoticeInfo(updateNoticeInfo);
		
		response.sendRedirect("/web/notice/detail?idx="+idx);
	}
}
