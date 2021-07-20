package QuizJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import QuizJDBC.*;

/*
create table school(
name varchar2(20) not null, --이름
value varchar2(20),         --학생 : 학번, 교수 : 과목, 행정 : 부서명
code number             -- 선택 code : 1. 학생 2. 교수 3. 행정
);
*/

public class SchoolManager {
	public static void mainMenu() {
		System.out.println("--학사관리--");
		System.out.println("1. 입력 2. 검색 3. 삭제 4. 종료");
		System.out.print("선택  >> ");
	}
	
	public static void insertPrint() {
		System.out.println("---정보 입력---");
		System.out.println("1. 학생 2. 교수 3. 관리자 4. 이전");
		System.out.print("선택 >> ");
	}
	
	public static void selectPrint() {
		//이름으로 검색 or 전체 항목 다 나오도록
		System.out.println("---검 색---");
		System.out.println("1. 이름 2. 전체 3. 이전");
		System.out.print("선택 >> ");
	}
	
	public static void deletePrint() {
		System.out.println("---삭 제---");
		System.out.println("1. 이름 2. 이전");
		System.out.print("선택 >> ");
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		School school = new School();
		boolean codeRun = true; //프로그램 진행 (true 실행, false 종료)
		char sel_1;
		char sel_2;
		
		while(codeRun) {
			mainMenu();
			sel_1 = scanner.next().charAt(0);
			switch(sel_1) {
			case '1': //추가
				insertPrint();
				sel_2 = scanner.next().charAt(0);
				school.insert(sel_2);
				break;
			case '2': //검색
				selectPrint();
				sel_2 = scanner.next().charAt(0);
				school.select(sel_2);
				break;
			case '3': //삭제
				deletePrint();
				sel_2 = scanner.next().charAt(0);
				school.delete(sel_2);
				break;
			case '4': //종료
				codeRun = false;
				break;
			default:
				System.out.println("선택 오류");
			}
		}
		System.out.println("--- Program End ---");
	}
}
