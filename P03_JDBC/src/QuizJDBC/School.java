package QuizJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class School {
	private Scanner scanner = new Scanner(System.in);
	
	public School() {
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
	
	//추가 1.학생 -> 학번, 2.교수 -> 과목, 3.행정 -> 부서
	public void insert(char sel) {
		String name = null;
		String value = null;
		int code = 0;

		if(sel == '1') {
			System.out.print("이름 입력 > "); name = scanner.next();
			System.out.println("학번 입력 > "); value = scanner.next();
			code = 1;
		} else if (sel == '2'){
			System.out.print("이름 입력 > "); name = scanner.next();
			System.out.println("과목 입력 > "); value = scanner.next();
			code = 2;
			
		} else if (sel == '3'){
			System.out.print("이름 입력 > "); name = scanner.next();
			System.out.println("부서 입력 > "); value = scanner.next();
			code = 3;
			
		} else {
			return;
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int su = 0;
		try {
			con = getConnection();
			String sql = "insert into school values (?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, value);
			pstmt.setInt(3, code);
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
	
	public void select(char sel) {
		String search = null;
		if(sel == '1') {
			System.out.print("이름 입력 > "); search = scanner.next();
		} else if(sel == '2') {
			search = "";
		} else {
			return;
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			con = getConnection();
			if(search.length() > 0) {
				String sql = "select * from school where name = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, search);
			}else {
				String sql = "select * from school";
				pstmt=con.prepareStatement(sql);
			}
			res = pstmt.executeQuery();
			
			while(res.next()) {
				String name = res.getString("name");
				String value = res.getString("value");
				int code = res.getInt("code");
				
				String valueTitle;
				if(code == 1) {
					valueTitle = "학번";
				} else if(code == 2) {
					valueTitle = "과목";
				} else if(code == 3) {
					valueTitle = "부서";
				}
				System.out.println(name + "\t" + value + "\t" + code);
			}
			// 강사님 솔루션 : sql if,esle 로 따로 만들고, 나중에 객체 다 생성하고 sel=='1'일때만 setstring
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(res != null) res.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void delete(char sel) {
		String del = null;
		if(sel == '1') {
			System.out.print("이름 입력 > "); del = scanner.next();
		} else {
			return;
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		int su = 0;
		
		try {
			con = getConnection();
			String sql = "delete school where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, del);
			su= pstmt.executeUpdate();
			
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
		System.out.println(su + "개의 행이 삭제 되었습니다.");

	}
}
