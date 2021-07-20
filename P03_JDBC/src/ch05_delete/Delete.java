package ch05_delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class DeleteTest{
	public DeleteTest() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("로딩 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("로딩 실패");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "dbtest";
		String pwd = "1234";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("success");
		}catch(SQLException e) {
			System.out.println("fail");
			e.printStackTrace();
		}
		
		return con;
	} //getConnection() end
	
	public void deleteArticle() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 입력 > ");
		String name = scanner.next();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int su = 0;
		
		try {
			con = getConnection();
			String sql = "delete dbtest where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Delete {
	public static void main(String[] args) {
		DeleteTest dt = new DeleteTest();
		dt.deleteArticle();
	}

}
