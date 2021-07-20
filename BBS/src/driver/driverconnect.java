package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class driverconnect {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("로딩 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("로딩 실패");
			e.printStackTrace();
		}
		
		//String url = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "dbtest2";
		String pwd = "1234";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("success");
		}catch(SQLException e) {
			System.out.println("fail");
			e.printStackTrace();
		}
	}
	

}
