package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import dao.SubjectDAO;
import service.ProjectService;
import vo.MemberVO;
import vo.SubjectVO;

public class SubjectPage {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private MemberVO myAccount = LoginPage.myAccount;

	private Scanner scan = new Scanner(System.in);

	private ProjectService subj;

	public SubjectPage() {
		subj = new ProjectService();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[1] 과목 코드 추가\t[2] 과목 코드 수정\t[3] 과목 코드 삭제\t\t[0] 뒤로 가기");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.print(">> ");
	}

	public void start() {
		String choice;
		while(true) {
			
			displayMenu();

			choice =scan.nextLine();
			switch (choice) {
			case "1": 
				insertSubject();
				continue;
			case "2": 
				updateSubject();
				continue;
			case "3":
				deleteSubject();
				continue;
			case "0":
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
				continue;
			}
		} 
	}

	private void deleteSubject() {
		while(true) {
			
		System.out.println();
		System.out.println("삭제할 과목코드를 입력하세요.\t\t[0]뒤로가기");
		System.out.print(">> "); // 키 값
		String SubjectID = scan.nextLine();
		if(SubjectID.equals("0")) {
			return;
		}else {
			int cnt = subj.deleteSubject(SubjectID);
			
			if (cnt > 0) {
				System.out.println(SubjectID + "과목 코드 삭제 성공");
				return;
			} else {
				System.out.println(SubjectID + "과목 코드 삭제 실패");
				continue;
			}
		}
	}
	
}

	private void updateSubject() {
		while(true) {
			
		SubjectVO sv = new SubjectVO();
		
		System.out.println("수정할 과목코드를 입력하세요 \t\t[0]뒤로가기");
		System.out.print(">> ");
		String subjectId = scan.nextLine();
		if (subjectId.equals("0")) {
			return;
		} else {
			boolean isExist = subj.checkSubject(subjectId);

			if (!isExist) {
				System.out.println("과목코드 " + subjectId + " 이(가) 없습니다.");
				continue;
			} else {
				System.out.println("수정할 과목명을 입력하세요");
				System.out.print(">> ");
				String name = scan.nextLine();
				if (name.equals("0")) {
					return;
				} else {
					sv.setSubjectName(name);
					sv.setSubjectID(subjectId);
					int cnt = subj.updateSubject(sv);

					if (cnt > 0) {
						System.out.println("과목 코드 수정 성공");
						return;
					} else {
						System.out.println("과목 코드 수정 실패");
						continue;
					}
				}

			}
		}
	}

	}

	private void insertSubject() {
		while(true) {
			
		System.out.println();
		System.out.println("새롭게 등록할 과목 코드를 입력하세요 \t\t[0]뒤로가기");
		System.out.print(">> ");
		System.out.print("과목 코드 입력 : ");
		String subjectID = scan.nextLine();
		if (subjectID.equals("0")) {
			return;
		} else {
			System.out.print("과목 이름 입력 : ");
			String subjectName = scan.nextLine();
			if (subjectName.equals("0")) {
				return;
			} else {
				SubjectVO sv = new SubjectVO();

				sv.setSubjectID(subjectID);
				sv.setSubjectName(subjectName);

				int cnt = subj.insertSubject(sv);

				if (cnt > 0) {
					System.out.println("과목 코드 등록 성공");
					return;
				} else {
					System.out.println("과목 코드 등록 실패");
					continue;
				}
			}
		}
	}
}


}


