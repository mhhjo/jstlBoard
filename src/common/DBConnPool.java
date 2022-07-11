package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	public DBConnPool() {
		try {
			Context initCtx=new InitialContext();
			Context ctx=(Context)initCtx.lookup("java:comp/env");
			DataSource source=(DataSource)ctx.lookup("dbcp_myoracle");
			con=source.getConnection();
			System.out.println("DB커넥션 풀 연결성공");
		}catch(Exception e) {
			System.out.println("DB커넥션 풀 연결 실패");
		}
	}
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
			System.out.println("DB커넥션풀 자원 반납");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
