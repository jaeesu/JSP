package schoolDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import schoolDTO.SchoolDTO;

public class SchoolDAO {
	public SchoolDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//System.out.println("로딩 성공");
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
			//System.out.println("success");
		}catch(SQLException e) {
			System.out.println("fail");
			e.printStackTrace();
		}
		
		return con;
	} //getConnection() end

	public boolean insert(SchoolDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int su = 0;
		boolean check = false;
		
		String sql = "insert into school values (?, ?, ?)";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getValue());
			pstmt.setInt(3, dto.getCode());
			
			su = pstmt.executeUpdate();
			
			if(su > 0) check = true;
			
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
		
		return check;
	}
	
	public void select(SchoolDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		String sql = null;
		
		if(dto == null) {
			sql = "select * from school";
		}else if (dto.getName() != null) {
			sql = "select * from school where name like ?";
		}else {
			sql = "select * from school where code = ?";
		}
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			if(dto != null) {
				if(dto.getName() != null) {
					pstmt.setString(1, "%"+dto.getName()+"%");
				} else {
					pstmt.setInt(1, dto.getCode());
				}
			}
			res = pstmt.executeQuery();
			while(res.next()) {
				String name = res.getString("name");
				String value = res.getString("value");
				int code = res.getInt("code");
				
				String valueTitle = "";
				if(code == 1) {
					valueTitle = "학번";
				} else if(code == 2) {
					valueTitle = "과목";
				} else if(code == 3) {
					valueTitle = "부서";
				}
				System.out.println(String.format("%-5s %-10s %-5s %-10s %-5s %-10d", "NAME", name, valueTitle , value,  "CODE", code));

			}
			
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

	public boolean delete(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean check = false;
		
		String sql = "delete school where name = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			int su = pstmt.executeUpdate();
			if(su != 0) check = true;
			
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
		return check;
	}
	
	public List<SchoolDTO> getList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<SchoolDTO> list = new ArrayList<SchoolDTO>();

		String sql = "select * from school";
		
		try {	
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				SchoolDTO dto = new SchoolDTO(res.getString("name"), res.getString("value"), res.getInt("code"));
				list.add(dto);
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
		if(list.isEmpty()) list = null;
		return list;
	}//getList() end
}
