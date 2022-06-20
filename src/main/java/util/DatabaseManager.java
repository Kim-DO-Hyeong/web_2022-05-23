package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	public static Connection getConnection(){
		Connection conn = null;
		
		try {
			// JDBC 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// DB 접속
			conn= DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb3?user=root&password=1234");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Statement getStatement(Connection conn) {
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stmt;
	}
	
	public static PreparedStatement getPstmt(Connection conn, String sql) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs !=null) {
			rs.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void closeStmt(Statement stmt) {
		try {
			if(stmt != null) {
			stmt.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConn(Connection conn) {
		try {
			if(conn != null) {
				conn.close();	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closePstmt(PreparedStatement pstmt) {
			try {
				if(pstmt != null) {
				pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
