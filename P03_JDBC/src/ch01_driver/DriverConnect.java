package ch01_driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * JDBC (Java Database Connectivity)
 * -java와 db의 연동을 위한 프로그래밍 api
 * 
 * JDBC 드라이버 로딩
 *  - dbms 마다 별도의 드라이버가 필요
 *  - 일반적으로 jar 파일 형태로 dbms마다 기본적으로 제공
 *  - 드라이버 위치
 *   > 11g : c-oraclxe-oracle-product-11.x.x-server-jdbc-lib-ojdbc6.jar
 *   > /u01/app/oracle/product/11.2.0/xe/jdbc/lib
 *   
 *  - 프로젝트의 build path 사용 추가
 *   > external jar 추가
 *  jdbc 드라이버 클래스
 *   > oracle.jdbc.OracleDriver
 * 
 * */

public class DriverConnect {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("로딩 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("로딩 실패");
			e.printStackTrace();
		}
		
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
	}
}
