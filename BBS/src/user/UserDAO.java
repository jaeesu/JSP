package user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

//ctrl_shift_o

public class UserDAO {
	public Connection conn;
	public PreparedStatement pstmt; //쿼리문 대기 및 설정
	public ResultSet rs; // 결과값 받아오기
	
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
				if(rs.getString("userPassword").equals(userPassword)) {
					return 1; // 로그인 성공
				}else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // id 없음
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;//db error
	} //하나의 계정에 대한 로그인 시도를 하는 함수
	
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login("test", "1234");
		System.out.println(result);
	}
}
