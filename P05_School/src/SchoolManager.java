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
			System.out.println("-- 관리 --");
			System.out.println("1.추가 2. 검색 3. 삭제 4. 목록 0. 종료");
			System.out.print("선택 > ");
			int num = scanner.nextInt();
			switch (num) {
			case 1: //추가
				insert();
				break;
			case 2: //수정
				update();
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
	
	public void exit() {
		System.out.println("-- program end --");
		System.exit(0); //강제종료
	}
	
	
}
