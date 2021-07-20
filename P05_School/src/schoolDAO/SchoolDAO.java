package schoolDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbtestDTO.DBtestDTO;
import schoolDTO.SchoolDTO;

public class SchoolDAO {
	public SchoolDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//System.out.println("로딩 성공");
		}catch(ClassNotFoundException e) {
			//System.out.println("로딩 실패");
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
			//System.out.println("success");
		}catch(SQLException e) {
			//System.out.println("fail");
			e.printStackTrace();
		}
		
		return con;
	} //getConnection() end

	public int insert(SchoolDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int su = 0;
		
		String sql = "insert into school values (?, ?, ?)";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getValue());
			pstmt.setInt(3, dto.getCode());
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
		return su;
	}
	
	public int update(SchoolDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int su = 0;
		
		String sql = "update school set value=?, code=? where name = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getValue());
			pstmt.setInt(2, dto.getCode());
			pstmt.setString(3, dto.getName());
			
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
		return su;
	}
	
	public int delete(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int su = 0;
		
		String sql = "delete school where name = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			su = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return su;
	}
	
	public void list() {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			String sql = "select * from school";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				String name = res.getString("name");
				String value = res.getString("value");
				int code = res.getInt("code");
				
				System.out.println("이름 : " + name + "\t" + value + "\t코드 : " + code);
			}
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

	
}
