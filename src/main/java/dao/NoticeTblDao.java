package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.NoticeInfo;
import util.DatabaseManager;

public class NoticeTblDao {
	
	public int getNoticeAmount(){
		Connection conn = null;
		PreparedStatement selectPstmt1 = null;
		ResultSet rs1 = null;
		
		int noticeAmount = 0;
		
		try {
			conn = DatabaseManager.getConnection();
			// 등록된 공지사항의 개수를 공지사항 정보 테이블에서 가져와 저장

			String sql = "SELECT COUNT(*) AS amount FROM noticetbl";

			selectPstmt1 = DatabaseManager.getPstmt(conn, sql);

			rs1 = selectPstmt1.executeQuery();
			
			rs1.next();
			noticeAmount = rs1.getInt("amount");
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DatabaseManager.closeResultSet(rs1);
			DatabaseManager.closePstmt(selectPstmt1);
			DatabaseManager.closeConn(conn);
		}
	
		return noticeAmount;
	}

	public boolean deleteByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 공지사항을 삭제하지 못한 가정
		boolean result =false;
		
		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "DELETE FROM noticetbl WHERE noticeID =?";
			
			pstmt= DatabaseManager.getPstmt(conn, sql);
			pstmt.setInt(1, idx);
		
			pstmt.executeUpdate();
			
			result =true;
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return result;
	}

	public NoticeInfo selectedNoticeInfoByIdx (int idx){
		Connection conn = null;
		PreparedStatement selectPstmt1 = null;
		ResultSet rs1 = null;
		
		NoticeInfo noticeInfo = null;
		
		try {
			conn = DatabaseManager.getConnection();
			// 등록된 공지사항의 개수를 공지사항 정보 테이블에서 가져와 저장
			
			// noticeID 에 맞는 행을 조회하는 코드 
			String sql = "SELECT * FROM noticetbl WHERE noticeID = ?";

			selectPstmt1 = DatabaseManager.getPstmt(conn, sql);
			selectPstmt1.setInt(1, idx);
			
			rs1=selectPstmt1.executeQuery();
			rs1.next();
			
			String title = rs1.getString("title");
			String contents = rs1.getString("contents");
			
			noticeInfo = new NoticeInfo(title, contents);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs1);
			DatabaseManager.closePstmt(selectPstmt1);
			DatabaseManager.closeConn(conn);
		}
		
		return noticeInfo;
	
	}

	public boolean writeNoticeInfo (NoticeInfo newNoticeInfo){
		
		Connection conn = null;
		PreparedStatement Insertpstmt = null;
		ResultSet rs = null;
		
	    boolean result = false;	
		
		// 회원 정보를 저장한다 ( 회원 정보 테이블에 회원 정보를 INSERT 한다 )
		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "INSERT INTO noticetbl(title,contents) VALUES(?,?)";
			
			Insertpstmt = DatabaseManager.getPstmt(conn, sql);
			Insertpstmt.setString(1,newNoticeInfo.getTitle());
			Insertpstmt.setString(2,newNoticeInfo.getContents());
			
			Insertpstmt.executeUpdate();
			
			
			result =true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			// DB에 접속하고 SQL을 실행하기 위해 생성한 자원 해제 - 중요!! 
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(Insertpstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return result;
	}
	
	public List<NoticeInfo> getNoticeInfoList (int start){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<NoticeInfo> noticeInfoList = new ArrayList<>();
 		
		try {
			conn = DatabaseManager.getConnection();
			// 등록된 공지사항의 개수를 공지사항 정보 테이블에서 가져와 저장


			String sql = "SELECT * FROM noticetbl ORDER BY noticeID DESC LIMIT ?,5 ";

			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setInt(1, start);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeInfo nth = new NoticeInfo(rs.getInt("noticeID"), rs.getString("title"),rs.getString("contents"));
				noticeInfoList.add(nth);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		return noticeInfoList;
	}

	public boolean updatedNoticeInfo (NoticeInfo updateNoticeInfo){
		Connection conn = null;
		PreparedStatement selectPstmt1 = null;
		
		boolean result = false;
		
		try {
			conn = DatabaseManager.getConnection();
			// 등록된 공지사항의 개수를 공지사항 정보 테이블에서 가져와 저장
			
			// noticeID 에 맞는 행을 조회하는 코드 
			String sql = "UPDATE noticetbl SET title = ? , contents= ? WHERE noticeID = ?";

			selectPstmt1 = DatabaseManager.getPstmt(conn, sql);
			selectPstmt1.setString(1, updateNoticeInfo.getTitle());
			selectPstmt1.setString(2, updateNoticeInfo.getContents());
			selectPstmt1.setInt(3, updateNoticeInfo.getnoticeId());
			
			selectPstmt1.executeUpdate();
			
			result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closePstmt(selectPstmt1);
			DatabaseManager.closeConn(conn);
		}
		return result;
	}

}
