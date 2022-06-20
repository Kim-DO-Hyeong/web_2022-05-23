package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.MemberTblDao;
import dto.MemberInfo;

public class MemberService {
	
	public int join(MemberInfo newMemberInfo) {
		MemberTblDao dao = new MemberTblDao();
		
		boolean success = dao.join(newMemberInfo);
		// 상태 코드를 리턴 
		if(success) {
			return 200;
		}else {
			return 409;
		}
		
	}

	public MemberInfo selectedMemberInfoByIdAndPw(MemberInfo loginMemberInfo){
		MemberTblDao dao = new MemberTblDao();
		
		MemberInfo memberInfo = dao.selectMemberInfoByIdAndPw(loginMemberInfo);
		
		// rs의 열을 이동할려면 next() 열이름 행에서 값 행으로 next 한거라고 생각하기 // 포인터를 이동 // 반환값은 boolean
		if(memberInfo != null ) {
			// 아이디와 비밀번호를 정확히 입력했다면 
			return memberInfo;
		}else {
			// 정확히 입력하지 않았다면
			return null;
		}
	}

	
	
}
