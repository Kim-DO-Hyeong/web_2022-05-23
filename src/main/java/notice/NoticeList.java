package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.NoticeInfo;
import service.NoticeService;
import util.DatabaseManager;

@WebServlet("/notice/list/temp")
public class NoticeList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// DB 에서 불러온 공지사항 페이지 번호
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		NoticeService service = new NoticeService();
		
		// 등록된 공지사항 개수 ( 요청정보에서 가져온 amount ) 
		int amount = service.getNoticeAmount();
		
		
		// DB 에서 불러온 공지사항 정보 목록 -end

		// 1. 공지사항 목록 서비스는 공지사항 목록에 저장된 공지사항이 없을 경우 상태 코드르 204로 설정
		if (amount == 0) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			// 2. 공지사항 목록에 저장된 공지 사항이 있을 경우 적절한 JSON 을 구성해 목록을 전달

			// DB 에서 불러온 공지사항 정보 목록
			JSONArray noticeInfoList = service.getNoticeInfoList(pageNumber); 
			
			response.setContentType("application/json;charset=UTF-8");

			PrintWriter output = response.getWriter();

//			하나의 공지사항 정보 -> {"title":"공지사항 제목","contents":"공지사항 내용"}
//			공지사항 정보 목록 -> [공지사항정보1, 공지사항정보2, --]

			// 자바가 가지고 있는 공지사항 목록 (NoticeWrite 클래스에 noticeInfoList) 을
			// 처음부터 끝까지 하나씩 접근해서
			// 클라이언트에게 전달한 n번째 공지사항의 정보로 구성하고
			// 공지사항 정보 목록에 배열의 형태로 저장한다

			// 시작 넘버 : 페이지 번호 -1 *5
			// 끝 넘버 : 페이지 번호 *5 -1
			
			JSONObject json = new JSONObject();
			request.setAttribute("noticeAmount", amount);
			
			
			json.put("amount", amount);
			json.put("list", noticeInfoList);
			
			output.print(json);
			output.close();
//			response.sendRedirect("/web/noticCe/list.jsp?pageNumber="+pageNumber);

		}
		
	}

}


//페이지 번호에 맞게 한번에 5개씩 공지사항 정보 테이블에서 가져와 저장
		// DB 가 가지고 있는 공지사항 목록 ( noticetbl ) 을
		// 페이지 번호에 맞는 공지사항을 조회해서 JSON 데이터로 구성해 전달하도록 향상된 for 문 수정
		// 페이지번호에 맞는 데이터 부터 시작해서 페이지 번호에 맞는 마지막 데이터 까지 하나씩 접근해서
		// 1 - 1~5
		// 2 - 6~10
		// 3 - 11~15
		// 4 - 16~20
		// 5 - 21~23
