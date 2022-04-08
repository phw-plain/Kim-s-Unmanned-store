package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql_main {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // jdbc 드라이버 주소 
	static final String DB_URL = "jdbc:mysql://parklee.c5drxfrqhy4y.ap-northeast-2.rds.amazonaws.com:3306/parklee?useSSL=false"; // DB 접속 주소 
	static final String USERNAME = "root"; // DB ID 
	static final String PASSWORD = "happythe8"; // DB Password 
	
		Connection conn = null; 
		Statement stmt = null; 
		ResultSet rs = null; 
		
	
	public mysql_main(){ // DB 접속 
		System.out.print("DatabaseName 데이터베이스 접속 : "); 
		try { 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			if (conn != null){
				System.out.println("성공");
			} else{System.out.println("실패");} 
		} catch (ClassNotFoundException e) { 
			System.out.println("Class Not Found Exection"); 
			e.printStackTrace(); 
			} catch (SQLException e) { 
				System.out.println("SQL Exception"); 
				e.printStackTrace(); 
				} 
		}//UserDAO
}
