package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import service.InterfaceService;
import service.ProjectService;
import vo.BoardVO;
import vo.CurriculumVO;
import vo.MemberVO;

public class Mypage {

	private InterfaceService projectService;
	private Scanner scan;
	public static MemberVO myAccount;

	public Mypage() {

		projectService = new ProjectService();
		scan = new Scanner(System.in);
		myAccount = LoginPage.myAccount;

	}

	public void display() {

		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[1] 회원정보 수정\t[2] 회원 탈퇴\t[3] 작성 게시물 조회\t[4] D-day\t[0] 뒤로 가기");
		System.out.println("--------------------------------------------------------------------------------");

	}

	public void start() {
		int choice;
		while (true) {
			display();
			System.out.print("입력 : ");

			choice = Integer.parseInt(scan.nextLine());

			switch (choice) {
			case 1:
				modify();
				continue;
			case 2:
				withdraw();
				continue;
			case 3:
				myBoard();
				continue;
			case 4:
				d_day();
				continue;
			case 0:

				return;

			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력해주세요");
			}

		}

	}

	private void d_day() {

		CurriculumVO myCurriculum = projectService.selectOneCurriculum(myAccount.getCurriculumID());

		String endDate = myCurriculum.getEndDate();

		String todayFm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())); // 오늘날짜

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date;
		try {
			date = new Date(dateFormat.parse(endDate).getTime());
			Date today = new Date(dateFormat.parse(todayFm).getTime());
			long calculate = date.getTime() - today.getTime();

			int Ddays = (int) (calculate / (24 * 60 * 60 * 1000));
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("과정명 : " + myCurriculum.getCurriculumName());
			System.out.println("수료까지 남은 날짜 : " + Ddays + "일");
			System.out.println("");
			System.out.println("[0] 뒤로 가기");
			String v = scan.nextLine();
			if (v.equals("0")) {
				return;

			}
		} catch (ParseException e) {
			System.out.println("d-day계산 중 에러 발생");
		}

	}

	private void myBoard() {
		System.out.println();
		while(true) {
			System.out.println("--------------------------------------------------------------------------------");
			BoardVO my = new BoardVO();
			my.setMemberID(myAccount.getMemberID());
			List<BoardVO> myBoardList = projectService.selectAllBoard(my);
			
			for (BoardVO i : myBoardList) {
				System.out.println("게시판이름\t게시글 번호\t제목\t글쓴이\t작성날짜\t공지여부\t조회수");
				System.out.println("--------------------------------------------------------------------------------");
				
				System.out.print(projectService.selectBL(i.getBoardID()).getBoardName()+"\t");
				System.out.print(i.getBoardNo()+"\t");
				System.out.print(i.getTitle()+"\t");
				System.out.print(i.getWriter()+"\t");
				System.out.print(i.getDate()+"\t");
				System.out.print(i.isNotice()+"\t");
				System.out.print(i.getHits()+"\t");
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println(i.getContent());
				
			}
			System.out.println();
			System.out.println();
			System.out.println("[0] 뒤로 가기");
			
			String choice = scan.nextLine();
			
			if(choice.equals("0")) {
				
				return;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
			
		}
	}

	private void withdraw() {
		System.out.println();
		while (true) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("비밀번호 입력 : ");
			String pass = scan.nextLine();

			if (!pass.equals(myAccount.getPassword())) {
				System.out.println("입력하신 비밀번호를 확인해주세요.");
				System.out.println("[0] 뒤로 가기\t[1] 다시 입력하기");
				System.out.print("입력 : ");
				if (scan.nextLine().equals("0")) {
					start();
				}

				continue;
			} else {
				break;
			}
		}
		while (true) {
			System.out.println();
			System.out.println("탈퇴하시겠습니까? ");
			System.out.print("입력 : ");
			String isWithdraw = scan.nextLine();

			if (isWithdraw.equals("네") || isWithdraw.equals("yes") || isWithdraw.equals("y")) {
				myAccount.setActive(isWithdraw);
			} else if (isWithdraw.equals("0")) {
				return;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	private void modify() {
		System.out.println();
		while (true) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("비밀번호 입력 : ");
			String pass = scan.nextLine();
			
			if (!pass.equals(myAccount.getPassword())) {
				System.out.println();
				System.out.println("입력하신 비밀번호를 확인해주세요.");
				System.out.println("[0] 뒤로 가기\t[1] 다시 입력하기");
				System.out.print("입력 : ");
				if (scan.nextLine().equals("0")) {
					start();
				}

				continue;
			} else {
				break;
			}

		}

		int choice;
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("[1] 비밀번호 수정\t[2] 이름 수정\t[3]휴대폰 번호 수정\t[4] 생년월일 수정\t\t[0] 뒤로 가기");

			System.out.print("입력 : ");
			String v = scan.nextLine();

			try {
				choice = Integer.parseInt(v);

				switch (choice) {
				case 1:
					modifyPass();
					break;
				case 2:
					modifyName();
					break;
				case 3:
					modifyPhone();
					break;
				case 4:
					modifyBirth();
					break;
				case 0:
					start();
					break;
				default:
					System.out.println("잘못된 입력입니다.");
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	private void modifyPass() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("새롭게 저장할 비밀번호 : ");
			String newPass = scan.nextLine().replaceAll(" ", "");

			if (!newPass.isEmpty()) {
				try {
					int gar = Integer.parseInt(newPass);
					System.out.println("문자와 숫자를 섞어서 입력해주세요.");
					continue;
				} catch (Exception e) {
					myAccount.setPassword(newPass);
					if (projectService.updateMember(myAccount)) {
						System.out.println("수정이 완료되었습니다.");
						return;
					} else {
						System.out.println("수정이 실패하였습니다.");
						return;
					}
				}

			} else { // 아무것도 입력하지 않을 경우
				System.out.println("잘못된 입력입니다.");
			}
		}

	}

	private void modifyName() {
		while (true) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("현재 저장되어있는 이름 : " + myAccount.getName());
			System.out.print("새롭게 저장할 이름 : ");
			String newName = scan.nextLine();

			if (!newName.isEmpty()) {
				try {
					int gar = Integer.parseInt(newName);
					System.out.println("잘못된 입력입니다.");
					continue;
				} catch (Exception e) {
				}

				myAccount.setName(newName);

				if (projectService.updateMember(myAccount)) {
					System.out.println("수정이 완료되었습니다.");
					return;
				} else {
					System.out.println("수정이 실패하였습니다.");
					return;
				}
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}

	}

	private void modifyPhone() {
		while (true) {

			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("현재 저장되어있는 휴대폰 번호 : " + myAccount.getPhoneNum());
			System.out.print("새롭게 저장할 휴대폰 번호 : ");
			String newPhonNum = scan.nextLine().replaceAll(" ", "");

			newPhonNum = newPhonNum.replaceAll("-", "");
			newPhonNum = newPhonNum.replaceAll("/", "");

			if (newPhonNum.length() == 11) {

				try {
					int test = Integer.parseInt(newPhonNum);
					myAccount.setPhoneNum(newPhonNum);
					if (projectService.updatePhoneNumMember(myAccount)) {
						System.out.println("수정이 완료되었습니다.");
						return;
					} else {
						System.out.println("수정이 실패하였습니다.");
						return;
					}

				} catch (Exception e) {
					System.out.println("올바른 숫자를 입력해주세요");
				}
			} else {
				System.out.println("전화번호 열한자리를 입력해야 합니다. ex)010-0000-0000");
			}
		}

	}

	private void modifyBirth() {
		while (true) {

			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("현재 저장되어있는 생년월일 : " + myAccount.getBirth());
			System.out.print("새롭게 저장할 생년월일 : ");
			String newBirth = scan.nextLine();

			newBirth = newBirth.replaceAll("-", "");

			if (newBirth.length() == 6) {
				myAccount.setBirth(newBirth);
				if (projectService.updateMember(myAccount)) {
					System.out.println("수정이 완료되었습니다.");
					return;
				} else {
					System.out.println("수정이 실패하였습니다.");
					return;
				}

			} else if (newBirth.length() == 8) {
				newBirth.substring(2, 8);

				myAccount.setBirth(newBirth);
				if (projectService.updateMember(myAccount)) {
					System.out.println("수정이 완료되었습니다.");
					return;
				} else {
					System.out.println("수정이 실패하였습니다.");
					return;
				}
			} else {
				System.out.println("다시 입력해주세요 . ex)yyyymmdd or yymmdd");
				continue;
			}

		}
	}

	public static void main(String[] args) {
		
		new Mypage().start();
	}

}
