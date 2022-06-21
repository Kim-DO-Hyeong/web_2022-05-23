package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.URL;

@WebServlet("/member/logout")
public class Logout extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 상태정보를 삭제한다 
		HttpSession session = request.getSession();
		
		//session.removeAttribute("loginUserName");
		session.invalidate();
		
		response.sendRedirect(URL.MAIN_PAGE);
	
	
	}

}
