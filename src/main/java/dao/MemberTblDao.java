package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dto.MemberInfo;
import util.DatabaseManager;

public class MemberTblDao {
	
	public boolean join( MemberInfo memberInfo) {
		// 아무것도 발생시키지 않기 위해서 null 상태로 만듦 
				Connection conn = null;
				PreparedStatement selectPstmt = null;
				PreparedStatement Insertpstmt = null;
				ResultSet rs = null;
				
				// 회원 정보를 저장한다 ( 회원 정보 테이블에 회원 정보를 INSERT 한다 )
				try {
					
					conn = DatabaseManager.getConnection();
					
					// 가입하려는 아이디가 이미 사용중인지 아닌지 판별하는 부분
					// 가입 하려는 아이디로 회원 정보를 조회 
					
					String sql = "SELECT * FROM membertbl WHERE memberID = ?";
					selectPstmt = DatabaseManager.getPstmt(conn, sql);
					selectPstmt.setString(1,memberInfo.getId());
					
					rs = selectPstmt.executeQuery();
					
					// 조회 결과가 있다면 이미 사용중인 아이디이므로 409 상태 코드를 설정하고 회원가입이 이뤄지면 안됨 ( 흐름이 여기서 끝나야함 ) 
					if(rs.next()){			
						return false;
					}
					// 조회 결과가 없다면 사용중인 아이디가 아니므로 회원가입이 진행되도록 해야함 
					
					// 가입하려는 아이디가 이미 사용중인지 아닌지 판별하는 부분 -end
					
					// 회원 가입을 하는 부분 			
					// 회원 정보 테이블에 회원 정보를 insert 한다
					// ? : 인덱스 파라미터 
					sql = "INSERT INTO membertbl VALUES(?, ?, ?)";

					// 쿼리를 실행하고 결과를 가져올 객체 생성 -> PreparedStatement 객체 / Statement 객체는 sql 앞에서 사용 
					Insertpstmt = DatabaseManager.getPstmt(conn, sql);
					Insertpstmt.setString(1, memberInfo.getId());
					Insertpstmt.setString(2, memberInfo.getPw());
					Insertpstmt.setString(3, memberInfo.getName());
					
					// 쿼리를 실행하고 결과를 가져옴 >> 쿼리로 영향받은 수를 리턴해줌 >> 영향받은 데이터 행은 하나이므로 1 리턴
					// pstmt 는 sql 을 가지고 있기 때문에 stmt 와는 다르게 excuteUpdate() 메서드에서 sql 이 필요없어짐 
					
					int count = Insertpstmt.executeUpdate();
					// 회원 가입을 하는 부분 -end 
					
					return true;

				} catch (SQLException e) {
					e.printStackTrace();
					// 이미 사용중인 아이디로 가입을 시도하면 예외가 발생함 
					return false;
				} finally {
					// DB에 접속하고 SQL을 실행하기 위해 생성한 자원 해제 - 중요!! 
					DatabaseManager.closeResultSet(rs);
					DatabaseManager.closePstmt(Insertpstmt);
					DatabaseManager.closePstmt(selectPstmt);
					DatabaseManager.closeConn(conn);
				}
			}
	
	public MemberInfo selectMemberInfoByIdAndPw (MemberInfo memberInfo){
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				// 조회전 조회정보 상태는 없음 
				MemberInfo selectedMemberInfo = null;
				
				try {
					conn = DatabaseManager.getConnection();

					// 실행할 쿼리를 작성  
					String sql = "SELECT * FROM membertbl WHERE memberID = ? AND memberPW = ?";
					
					// 쿼리를 실행하고 결과를 가져올 PreparedStatement 객체 생성 
					pstmt = DatabaseManager.getPstmt(conn, sql);
					pstmt.setString(1, memberInfo.getId());
					pstmt.setString(2, memberInfo.getPw());
					
					// 쿼리를 실행하고 결과를 가져옴 >> 쿼리로 영향받은 수를 리턴해줌 >> 영향받은 데이터 행은 하나이므로 1 리턴 
					rs = pstmt.executeQuery(); // 결과값이 있으면 열이름과 값까지 같이 리턴해주고 없으면 열 이름들만 리턴해줌 
					
					if(rs.next()) {
						String memberID = rs.getString("memberID");
						String memberPW = rs.getString("memberPW");
						String memberName = rs.getString("memberName");
						
						selectedMemberInfo = new MemberInfo(memberID,memberPW,memberName);
						
					}
					
					
//					rs.getString("memberID");
//					rs.getString("memberPW");
//					rs.getString("memberName");
					
					
				}  catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// DB에 접속하고 SQL을 실행하기 위해 생성한 자원 해제 - 중요!! 
					// 해제하는 순서는 선언의 역순으로 선언해서 해제
					DatabaseManager.closeResultSet(rs);
					DatabaseManager.closePstmt(pstmt);
					DatabaseManager.closeConn(conn);
					
				}
	
				return selectedMemberInfo;
	}

	
}

