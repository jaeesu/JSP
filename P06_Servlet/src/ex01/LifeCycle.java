package ex01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * # servlet
 * java 언어 기반 웹프 기술
 * java class 안에 html tag 포함 가능
 * servlet-api.jar 추가해야함 -> tomcat
 * servlet-api : tomcat의 라이브러리로 제공되므로 build path 설정 시 사용 가능
 * tomcat - lib - 'servelet-api.jar'
 * 
 * # 실행 순서
 * - 초기화 : init()
 * 	> 서블릿 요청 시 맨 처음 한 번만 호출
 * 
 * 작업 수행 : doGet(), goPost()
 *  > 서블릿 요청 시 매번 호출된다. 실제로 클라이언트가 요청하는 작업을 수행
 *  
 * 종료 : destroy()
 *  > java 코드가 수정이 되었을 때 서블릿의 마무리 작업을 수행
 *  init(), destroy() 메서드는 생략 가능, doGet(), goPost()는 반드시 구현
 * 
 * # 서블릿 생성 과정
 * 1. 사용자 정의 서블릿 클래스 만들기
 * 2. 서블릿 생명주기 메서드 구현
 * 3. 서블릿 맵핑 작업
 * 4. 웹 브라우저에서 서블릿 맵핑 이름으로 요청하기(실행)
 * 
 * # 서블릿 맵핑 작업
 * - 프로젝트의 web.xml에서 설정
 * - <servlet> tag, <servlet-mapping> tag 이용
 * 
 * 
 * 
 */
public class LifeCycle extends HttpServlet {
	//최초 요청 시 한번만 실행
	
	public void init(ServletConfig config) throws SecurityException{
		System.out.println("- init() run -");
	}
	public void destroy() {
		System.out.println("- destroy() run -");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


}
