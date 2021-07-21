import java.util.List;
import java.util.Scanner;

import schoolDAO.SchoolDAO;
import schoolDTO.SchoolDTO;

public class SchoolManager {
	private final Scanner scanner = new Scanner(System.in);
	
	public SchoolManager() {
		menu();
	}
	
	public void menu() {
		while(true) {
			System.out.println("-- 관 리 --");
			System.out.println("1.추가 2. 검색 3. 삭제 4. 목록 0. 종료");
			System.out.print("선택 > ");
			int num = scanner.nextInt();
			switch (num) {
			case 1: //추가
				insert();
				break;
			case 2: //수정
				search();				
				break;
			case 3: //삭제
				delete();
				break;
			case 4: //목록
				list();
				break;
			case 0: //종료
				exit();
				break;
			default:
				System.out.println("선택 오류");
			}
			System.out.println();
		}//while end
	}
	
	public int codeInput() {
		System.out.println("--- code 선택 ---");
		System.out.println("1. student 2. professor 3. manager");
		System.out.print("select >> ");
		int code = scanner.nextInt();
		return code;
	}
	
	public String valueInput(int code) {
		if(code == 1) {
			System.out.print("student num enter >> ");
		} else if (code == 2) {
			System.out.print("subject enter >> ");
		} else if (code == 3) {
			System.out.print("departure enter >> ");
		}
		String value = scanner.next();
		return value;
	}
	
	public void insert() {
		System.out.println("--- 데이터 추가 ---");
		int code = codeInput();
		if(code <1 || code >3) {
			System.out.println("이전 메뉴로 이동");
			return;
		}
		System.out.print("이름 입력 >> ");
		String name = scanner.next();
		String value = valueInput(code);
		
		SchoolDTO dto = new SchoolDTO(name, value, code);
		SchoolDAO dao = new SchoolDAO();
		boolean check = dao.insert(dto);
		if (check) System.out.println(name + "(이)가 추가되었습니다.");
		else System.out.println("추가 실패");

	}
	
	public void search() {
		System.out.println("--- 테이터 검색 ---");
		System.out.println("1. name 2. code 3. all");
		System.out.println("선택 >> ");
		int num = scanner.nextInt();
		
		if(num < 1 || num > 3) return;
		SchoolDTO dto = null;
		
		if(num == 1) {
			dto = new SchoolDTO();
			System.out.println("이름 입력 >> ");
			dto.setName(scanner.next());
		}else if (num == 2) {
			int code = codeInput();
			if(code < 1 || code > 3) {
				System.out.println("code error");
				return;
			}
			dto = new SchoolDTO();
			dto.setCode(code);
		}
		SchoolDAO dao = new SchoolDAO();
		dao.select(dto);
	}
	
	public void delete() {
		System.out.println("--- 데이터 삭제 ---");
		System.out.print("이름 입력 >> ");
		String name = scanner.next();
		
		SchoolDAO dao = new SchoolDAO();
		boolean check = dao.delete(name);
		
		if(check) System.out.println(name + "(이)가 삭제되었습니다.");
		else System.out.println("삭제 실패");
	}
	
	public void list() {
		SchoolDAO dao = new SchoolDAO();
		List schoolList = dao.getList();
		String title = String.format("%-10s %-10s %-10s", "NAME", "VALUE", "CODE");
		System.out.println(title);
		
		for(int i=0; i<schoolList.size(); i++) {
			SchoolDTO man = (SchoolDTO)schoolList.get(i);
			String data = String.format("%-10s %-10s %-10d", man.getName(), man.getValue(), man.getCode());
			System.out.println(data);
		}
	}

	public void exit() {
		System.out.println("-- program end --");
		System.exit(0); //강제종료
	}
	
}
