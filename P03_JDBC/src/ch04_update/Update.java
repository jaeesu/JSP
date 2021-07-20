package ch04_update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class UpdateTest{
	public UpdateTest() {
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
	
	public void updateArticle() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("수정 이름 입력 > ");
		String name = scanner.next();
		
		Connection con = null; // 레퍼런스만 만들고 예외 처리 안에서 진행할 수 있다.
		PreparedStatement pstmt = null; //수정 레코드 수 반환
		int su = 0;
		
		try {
			con = getConnection();
			String sql = "update dbtest set age=age+1 where name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
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
		System.out.println(su + "개의 행이 수정되었습니다.");
	}
}

public class Update {
	public static void main(String[] args) {
		UpdateTest ut = new UpdateTest();
		ut.updateArticle();
	}
}
