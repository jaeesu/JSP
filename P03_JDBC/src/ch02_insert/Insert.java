package ch02_insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * 질의 보낼 때 사용하는 객체 : preparedStatement
 * 쿼리 실행을 위한 객체
 * java.slq.PreparedStatement
 * 
 * 쿼리 실행 메서드
 * int executeUpdate()
 *  > 테이블에 변경이 발생할 때 사용, 추가, 삭제, 변경 - insert, delete, update
 * 
 * ResultSet executeQuery()
 *  > select 문에서 실행 결과를 받을 때 사용하는 메서드 
 *  
 * JDBC 프로그램 실행 단계
 * -1. JDBC 드라이버 인스턴스 생성
 *  2. Connection 객체 생성
 *  3. Statement 생성
 *   4. 쿼리 실행
 *  5. ResultSet 종료
 *  6. Statement 종료
 *  7. Connection 종료
 * 
 * */


class InsertTest {
	
	public InsertTest() {
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
	
	public void insertArticle() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 입력 > ");
		String name = scanner.next();
		System.out.print("나이 입력 > ");
		int age = scanner.nextInt();
		System.out.print("키 입력 > ");
		double height = scanner.nextDouble();
		
		Connection con = getConnection();  //db connection
		PreparedStatement pstmt = null;  //send sql
		int su = 0;
		try {
			String sql = "insert into dbtest values(?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);
			su = pstmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(su + "개의 행이 추가되었습니다.");
		
	}
}

public class Insert {
	public static void main(String[] args) {
		
		InsertTest it = new InsertTest();
		it.insertArticle();
	}
}
