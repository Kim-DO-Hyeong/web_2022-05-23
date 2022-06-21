package notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeTblDao;
import util.DatabaseManager;

@WebServlet("/notice/delete")
public class NoticeDelete extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx=Integer.parseInt(request.getParameter("idx"));
		
		NoticeTblDao dao = new NoticeTblDao();
	
		dao.deleteByIdx(idx);
		
//		Write.noticeInfoList.remove(idx);
		response.sendRedirect("/web/notice/list?pageNumber=1");
	}
}
