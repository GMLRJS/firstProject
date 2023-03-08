package test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import service.InterfaceService;
import service.ProjectService;
import vo.CurriculumVO;
import vo.MemberVO;
import vo.QuestionVO;

public class LoginPage {

	private InterfaceService projectService;
	private Scanner scan;
	public static MemberVO myAccount;

	public LoginPage() {

		projectService = new ProjectService();
		scan = new Scanner(System.in);

	}

	// 첫 로그인 화면을 출력하는 메소드
	public void displayMenu() {

		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[1] 로그인\t[2] 회원가입\t[3] 계정찾기\t\t[0]프로그램 종료");
		System.out.println("--------------------------------------------------------------------------------");

	}

	public void start() {

		int choice;

		while (true) {

			displayMenu();
			System.out.println();
			System.out.print("입력 : ");
			try {
				choice = Integer.parseInt(scan.nextLine());

				switch (choice) {
				case 1: // 로그인 메소드 호출
					log_in();
					return;
				case 2: // 회원가입 메소드 호출
					registration();
					break;
				case 3: // 계정찾기 메소드 호출
					findAccount();
				case 0:
					scan.close();
					return;

				default:
					System.out.println("번호를 잘못 입력했습니다. 다시입력해주세요");
				} // switch문 종료
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

		} // while문 종료
	} // start메소드 종료

	private void findAccount() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("아이디 :");
			String id = scan.nextLine().replaceAll(" ", "");

			if (!projectService.checkMember(id)) {
				System.out.println("존재하지 않는 아이디입니다.");
				return;
			}
			QuestionVO vo = projectService.selectQuestion(id);

			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");

			System.out.println("질문 :" + vo.getQuestion());
			System.out.print("정답 : ");
			String val = scan.nextLine().trim();

			if (vo.getAnswer().equals(val)) {
				System.out.println("정답입니다.");

				MemberVO member = new MemberVO();

				member.setMemberID(id);
				member.setPassword("0000");

				if (projectService.updatePasswordMember(member)) {
					System.out.println();
					System.out.println("비밀번호가 0000으로 초기화되었습니다.");
					return;
				}
			} else {
				System.out.println("정답이 아닙니다.");
			}

		} // while문 종료

	}

	public void log_in() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("아이디 : ");
			String id = scan.nextLine();

			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("비밀번호 : ");
			String pass = scan.nextLine();

			if (projectService.checkMember(id)) {

				myAccount = projectService.selectMyMember(id);

				if (myAccount.getPassword().equals(pass)) {

					System.out.println("로그인 완료");

					return;
				} else {
					System.out.println("비밀번호를 확인해주세요.");
				}

			} else {
				System.out.println();
				System.out.println("존재하지 않는 계정입니다.");
				break;

			} // if문 종료
		} // while문 종료
	}

	public void registration() {

		String id = inputID();
		String pass = inputPass();
		String name = inputName();
		String gender = inputGender();
		String phoneNum = inputPhoneNum();
		String birth = inputBirth();
		String curri = inputCurri();

		String q = inputQuestion();
		String answer = inputAnswer();

		MemberVO member = new MemberVO();
		QuestionVO question = new QuestionVO();

		member.setMemberID(id);
		member.setPassword(pass);
		member.setName(name);
		member.setGender(gender);
		member.setPhoneNum(phoneNum);
		member.setBirth(birth);
		member.setCurriculumID(curri);
		// 초기값 N
		member.setActive("N");

		// 질문
		question.setMemberID(id);
		question.setQuestion(q);
		question.setAnswer(answer);

		if (projectService.insertMember(member)) {
			if (projectService.insertQuestion(question)) {

				System.out.println("회원가입에 성공했습니다.");
				System.out.println("담당자의 승인 후 로그인 가능합니다.");
				return;
			}
		} else {
			System.out.println("회원가입에 실패했습니다.");
			return;
		}

	}

	private String inputAnswer() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("계정 찾기 질문의 정답을 입력해주세요");
			System.out.println("정답 : ");
			String answer = scan.nextLine();

			if (!answer.equals(" ") && answer != null) {
				return answer;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	private String inputQuestion() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("계정 찾기를 위한 질문을 입력해주세요");
			System.out.println("질문 : ");
			String question = scan.nextLine();

			if (!question.equals(" ") && question != null) {
				return question;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	private String inputBirth() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("생년월일 : ");
			String birth = scan.nextLine().replaceAll(" ", "");
			birth = birth.replaceAll("-", "");

			if (birth.length() == 6) {
				System.out.println("입력한 생년월일 : " + birth);
				return birth;
			} else if (birth.length() == 8) {

				return birth.substring(2, 8);
			} else {
				System.out.println("다시 입력해주세요 . ex)yyyymmdd or yymmdd");
			}
		}

	}

	private String inputCurri() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("과정명 : ");
			String curri = scan.nextLine().trim();

			if (curri.isEmpty())
				continue;

			try {
				int test = Integer.parseInt(curri);
				System.out.println("잘못된 입력입니다.");
				continue;
			} catch (Exception e) {
			}
			List<CurriculumVO> list = projectService.selectCurriculum();

			for (CurriculumVO vo : list) {
				if (vo.getCurriculumName().startsWith(curri)) {
					System.out.println("입력한 과정명 : " + vo.getCurriculumName());
					return vo.getCurriculumID();
				} else {
					System.out.println("입력하신 과정이 검색되지 않습니다.");
				}
			}

		}
	}

	private String inputPhoneNum() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("휴대폰 번호 : ");
			String phone = scan.nextLine().replaceAll(" ", "");
			phone = phone.replaceAll("-", "");
			phone = phone.replaceAll("/", "");

			if (phone.length() == 11) {

				try {
					int test = Integer.parseInt(phone);
					System.out.println("입력한 번호 : " + phone);
					return phone;
				} catch (Exception e) {
					System.out.println("올바른 숫자를 입력해주세요");
				}
			} else {
				System.out.println("전화번호 열한자리를 입력해야 합니다. ex)010-0000-0000");
			}

		}

	}

	private String inputGender() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("성별 : ");
			String gender = scan.nextLine().replaceAll(" ", "");
			if (gender.equals("남") || gender.equals("남자")) {
				return "M";
			} else if (gender.equals("여자") || gender.equals("여")) {
				return "W";
			}
			System.out.println("다시 입력해주세요.");
		}

	}

	private String inputName() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("이름 : ");
			String name = scan.nextLine().replaceAll(" ", "");

			if (!name.isEmpty()) {
				try {
					int gar = Integer.parseInt(name);
					System.out.println("잘못된 입력입니다.");
					continue;
				} catch (Exception e) {
				}
				System.out.println("입력한 이름 : " + name);
				return name;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}

	}

	private String inputPass() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("비밀번호 : ");
			String pass = scan.nextLine().replaceAll(" ", "");

			if (!pass.isEmpty()) {
				try {
					int gar = Integer.parseInt(pass);
					System.out.println("문자와 숫자를 섞어서 입력해주세요.");
					continue;
				} catch (Exception e) {
					// 제대로 입력시 통과
				}
				System.out.println("입력한 비밀번호 : " + pass);
				return pass;
			} else { // 아무것도 입력하지 않을 경우
				System.out.println("잘못된 입력입니다.");
			}

		}
	}

	private String inputID() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("아이디 :");
			String name = scan.nextLine().replaceAll(" ", "");
			if (!name.isEmpty()) {
				try {
					int gar = Integer.parseInt(name);
					System.out.println("잘못된 입력입니다.");
					continue;
				} catch (Exception e) {
				} // 문자열 혹은 문자열과 숫자로 id입력시
				if (!projectService.checkMember(name)) {
					System.out.println("입력한 아이디 : " + name);
					return name;
				} else {
					System.out.println("이미 존재하는 아이디입니다.");
				}

			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}

	}

	public static void main(String[] args) {

		new LoginPage().start();
		//sdsdsd

	}
}
