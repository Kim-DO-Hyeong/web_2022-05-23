package notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.NoticeTblDao;
import service.NoticeService;

@WebServlet("/notice/amount")
public class NoticeAmount extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 등록된 공지사항의 개수를 클라이언트에게 전달
		
		NoticeTblDao dao = new NoticeTblDao();
		
		int noticeAmount = dao.getNoticeAmount();
		
			response.setContentType("applcation/json;charset=UTF-8");;
			PrintWriter output = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("amount", noticeAmount);
			output.print(json);
			output.close();
	}
}
