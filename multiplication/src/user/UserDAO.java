package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	public Connection conn;
	public PreparedStatement pstmt; //쿼리문 대기 및 설정
	public ResultSet rs; // 결과값 받아오기
	public int su;
	
	public UserDAO() {
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
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("success");
		}catch(SQLException e) {
			System.out.println("fail");
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String sql = "select userPassword from dbtest where userID = ?";
		try {
			pstmt = conn.prepareStatement(sql); //sql을 데이터베이스에 삽입 - 인젝션 방지 
			pstmt.setString(1, userID); //가장 중요한 부분!! - 물음표로 해둔 것에 userID를 넣는 것
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				}else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // id 없음
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -2;//db error
	} //하나의 계정에 대한 로그인 시도를 하는 함수
	
	public int signup(String userID, String userPassword, String userName, String userGender, String userEmail) {
		
		String sql = "insert into dbtest values (?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, userName);
			pstmt.setString(4, userGender);
			pstmt.setString(5, userEmail);
			
			su = pstmt.executeUpdate();
			
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -2;//db error
	} //하나의 계정에 대한 로그인 시도를 하는 함수

	public static void main(String[] args) {
		System.out.println("start----------");
		UserDAO user = new UserDAO();
		int result = user.signup("user3", "5678", "user3", "male", "user3@naver.com");
		System.out.println(result);
		System.out.println("success!!!");
	}

}

