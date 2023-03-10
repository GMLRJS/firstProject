package test;

import java.util.List;
import java.util.Scanner;

import service.InterfaceService;
import service.ProjectService;
import vo.BoardVO;
import vo.CommentVO;
import vo.CurriculumVO;
import vo.MemberVO;

public class ManagerPage {
	private InterfaceService projectService;
	private Scanner scan;
	public MemberVO myAccount = LoginPage.myAccount;

	public ManagerPage() {

		projectService = new ProjectService();
		scan = new Scanner(System.in);

	}

	public void displayMenu() {

		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[1] 학생정보 관리\t[2] 학생상담 관리\t[3] 가입신청 목록\t\t[0]뒤로 가기");
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
				case 1: // 학생정보 관리
					infoManager();
					continue;
				case 2: // 학생상담 관리
					adviceManager();
					continue;
				case 3: // 가입신청 관리
					admissionManager();
				case 0:
					scan.close();
					return;

				default:
					System.out.println("번호를 잘못 입력했습니다. 다시입력해주세요");
				} // switch문 종료
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다.");
				continue;
			}

		} // while문 종료
	} // start메소드 종료

	private void admissionManager() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("가입 신청 목록");
			MemberVO waitPermission = new MemberVO();
			waitPermission.setActive("N");
			List<MemberVO> memList = projectService.selectAllMember(waitPermission);
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("아이디\t\t이름\t성별\t  휴대폰 번호\t생년월일\t  계정 활성화 정보");
			System.out.println("--------------------------------------------------------------------------------");
			for (MemberVO v : memList) {
				System.out.println(v.getMemberID() + "\t" + v.getName() + "\t " + v.getGender() + "\t" + v.getPhoneNum()
						+ "\t" + v.getBirth() + "\t\t" + v.isActive());

			}
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("[1] 가입신청 승인\t[2] 전체 가입신청 승인\t\t[0] 뒤로 가기");
			String choice = scan.nextLine();

			try {
				int chc = Integer.parseInt(choice);

				switch (chc) {
				case 1:
					permission();
					continue;
				case 2:
					permissionAll();
					continue;
				case 0:

					return;

				default:
					System.out.println("번호를 잘못 입력했습니다. 다시입력해주세요");
					continue;
				}
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다.");
			}

		}
	}

	private void permissionAll() {
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("모든 가입신청을 승인하시겠습니까? Y or N");
		System.out.print("입력 : ");
		String input = scan.nextLine();

		if (input.equals("N")) {
			return;
		}
		MemberVO permissionMember = new MemberVO();
		permissionMember.setActive("N");
		List<MemberVO> permissionMembermemList = projectService.selectAllMember(permissionMember);
		int cnt = 0;
		for (MemberVO member : permissionMembermemList) {
			member.setActive("Y");
			if (projectService.updateActiveMember(permissionMember)) {
				cnt++;
				System.out.println(member.getMemberID() + "\t" + member.getName() + "의 가입승인이 정상 처리되었습니다.");
			}
			;
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("가입 승인된 총 학생 수 : " + cnt + "명");
		System.out.println();
		while (true) {
			System.out.println("[0] 뒤로 가기");
			String back = scan.nextLine();

			if (back.equals("0")) {
				return;
			} else {
				System.out.println("다시 입력해주세요.");
			}
		}

	}

	private void permission() {
		while (true) {

			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("가입 승인 할 학생의 ID를 입력해주세요.");
			System.out.println("입력 : ");
			String id = scan.nextLine();

			if (!projectService.checkMember(id)) {
				System.out.println("등록되지 않은 사용자입니다.");
				continue;
			}

			MemberVO choiceMember = projectService.selectMyMember(id);
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println(choiceMember.getMemberID() + "\t" + choiceMember.getName() + "의 가입을 승인하시겠습니까? Y or N");
			System.out.println("입력 : ");
			String answer = scan.nextLine();

			if (answer.equals("Y") || answer.equals("yes") || answer.equals("y") || answer.equals("YES")) {
				choiceMember.setActive("Y");
				System.out.println(choiceMember.getMemberID() + "\t" + choiceMember.getName() + "의 가입이 승인되었습니다.");
				System.out.println("--------------------------------------------------------------------------------");
				return;
			} else if (answer.equals("n") || answer.equals("N") || answer.equals("no") || answer.equals("NO")) {
				System.out.println("가입승인이 취소되었습니다.");
				return;
			} else {
				System.out.println("잘못된 입력입니다.");
				continue;
			}

		}

	}

	private void adviceManager() {

		System.out.println();
		System.out.println("------------------------------상담게시판 현황-----------------------------------");
		BoardVO sangdam = new BoardVO();
		sangdam.setBoardID("CB");
		List<BoardVO> list = projectService.selectAllBoard(sangdam);
		System.out.println(list);

		for (BoardVO board : list) {
			System.out.println("게시글 번호\t제목\tID :  글쓴이\t작성날짜 ");
			System.out.println(board.getBoardNo() + "\t" + board.getTitle() + "\t" + board.getMemberID() + " :  "
					+ board.getWriter() + "\t" + board.getDate());
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println(board.getContent());
			System.out.println("--------------------------------------------------------------------------------");
		}
		while (true) {
			System.out.println();
			System.out.println("[1] 댓글\t[2] 게시글 수정\t[3] 게시글 삭제\t\t[0] 뒤로 가기");
			System.out.print("입력 : ");
			String choice = scan.nextLine();

			switch (choice) {
			case "1":
				writeComment();
				break;
			case "2":
				modifyContent();
				break;
			case "3":
				eraseBoard();
				break;
			case "0":

				return;

			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}

		}
	}

	private void eraseBoard() {
		while (true) {

			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("삭제할 게시물의 번호를 입력해주세요");
			System.out.println("입력 : ");
			String boardNo = scan.nextLine();

			try {
				int no = Integer.parseInt(boardNo);
				BoardVO target = projectService.selectBoard(no);
				System.out.println(target.getTitle() + "을 삭제하시겠습니까? Y or N");
				System.out.print("입력 : ");
				String answer = scan.nextLine();
				if (answer.equals("Y") || answer.equals("yes") || answer.equals("y") || answer.equals("YES")) {
					if (projectService.deleteBoard(no)) {
						System.out.println("게시물이 정상적으로 삭제되었습니다.");
						return;
					} else {
						System.out.println("게시물이 정상적으로 삭제되지 않았습니다.");
						continue;
					}

				} else if (answer.equals("n") || answer.equals("N") || answer.equals("no") || answer.equals("NO")) {
					return;
				} else {
					System.out.println("잘못된 입력입니다.");
					continue;
				}

			} catch (Exception e) {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	private void modifyContent() {

		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("수정할 상담 게시물의 번호를 입력해주세요.");
			System.out.println("입력 : ");
			String boardNo = scan.nextLine();
			try {
				int no = Integer.parseInt(boardNo);
				if (projectService.checkBoard(no)==1) {
					BoardVO vo = projectService.selectBoard(no);

					System.out.println("제목 : " + vo.getTitle());
					System.out.print("제목 : ");
					String title = scan.nextLine();
					System.out.println("내용 : " + vo.getContent());
					System.out.print("내용 : ");
					String content = scan.nextLine();

					BoardVO vo2 = new BoardVO();
					vo2.setBoardNo(vo.getBoardNo());
					vo2.setTitle(title);
					vo2.setContent(content);

					if (projectService.updateBoard_Tit_Con(vo2)) {
						System.out.println("수정이 완료되었습니다.");
						return;
					} else {
						System.out.println("수정에 실패하였습니다.");
						return;
					}
				}

			} catch (Exception e) {
				System.out.println("잘못된 입력입니다.");
				continue;
			}

		}

	}

	private void writeComment() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("댓글을 입력할 글 번호를 입력하세요.");
			System.out.println("입력 : ");
			String boardNo = scan.nextLine();

			try {
				int no = Integer.parseInt(boardNo);
				if (projectService.checkBoard(no)==1) {
					BoardVO vo = projectService.selectBoard(no);
					System.out.println("게시물 : " + vo.getTitle());
					System.out.print("댓글 : ");
					String comment = scan.nextLine();

					if (comment != null || comment != "") {
						CommentVO co = new CommentVO();
						co.setCommentContent(comment);
						co.setMemberID(myAccount.getMemberID());
						co.setBoardNo(no);

						if (projectService.insertComment(co)) {
							System.out.println("댓글이 등록되었습니다.");
							return;
						} else {
							System.out.println("댓글 등록에 실패하였습니다.");
							return;
						}

					} else {
						System.out.println("잘못된 입력입니다.");
						return;
					}

				} else {
					System.out.println("해당 게시물을 찾을 수 없습니다.");
				}
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다.");

			}

		}

	}

	private void infoManager() {
		while (true) {

			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("[1] 전체 학생 조회\t[2] 학생 검색\t\t[0] 뒤로 가기");
			System.out.print("입력 : ");
			String choice = scan.nextLine();

			switch (choice) {
			case "1":
				;
				allStudent();
				break;
			case "2":
				searchStudent();
				break;
			case "0":

				return;
			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}

		}
	}

	private void searchStudent() {
		while (true) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("검색할 학생 정보를 선택해주세요.");
			System.out.println("[1] 이름\t[2] 성별 [3] 과정\t\t[0] 뒤로 가기");
			System.out.print("입력 : ");
			String choice = scan.nextLine();
			switch (choice) {
			case "1":
				selectByName();
				break;
			case "2":
				selectByGender();
				break;
			case "3":
				selectByCurri();
				break;
			case "0":

				return;

			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}

	}

	private void selectByCurri() {
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("과정명 : ");
		String curri = scan.nextLine();

		List<CurriculumVO> list = projectService.selectCurriculum();

		for (CurriculumVO curriculum : list) {
			if (curriculum.getCurriculumName().startsWith(curri)) {
				System.out.println("검색된 과정 : " + curriculum.getCurriculumName());
				MemberVO imsi = new MemberVO();
				imsi.setCurriculumID(curriculum.getCurriculumID());
				List<MemberVO> memlist = projectService.selectAllMember(imsi);
				for (MemberVO member : memlist) {
					System.out.println();
					System.out.println("회원ID\t이름\t성별\t전화번호\t\t생년월일\t과정ID");
					System.out.println(member.getMemberID() + "\t" + member.getName() + "\t" + member.getGender() + "\t"
							+ member.getPhoneNum() + "\t\t" + member.getBirth() + "\t" + member.getCurriculumID());

				}
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("[0] 뒤로 가기");
				System.out.print("입력 : ");
				String input = scan.nextLine();
			}

		}

	}

	private void selectByGender() {
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("성별 : ");
		String gender = scan.nextLine();

		if (gender.equals("남자") || gender.equals("남") || gender.equals("M") || gender.equals("m")) {
			MemberVO man = new MemberVO();
			man.setGender("M");
			List<MemberVO> manList = projectService.selectAllMember(man);

			for (MemberVO manMember : manList) {
				System.out.println();
				System.out.println("회원ID\t이름\t성별\t전화번호\t\t생년월일\t과정ID");
				System.out.println(manMember.getMemberID() + "\t" + manMember.getName() + "\t" + manMember.getGender()
						+ "\t" + manMember.getPhoneNum() + "\t\t" + manMember.getBirth() + "\t"
						+ manMember.getCurriculumID());
			}
			System.out.println("--------------------------------------------------------------------------------");

		} else if (gender.equals("여자") || gender.equals("여") || gender.equals("W") || gender.equals("w")) {

			MemberVO man = new MemberVO();
			man.setGender("W");
			List<MemberVO> manList = projectService.selectAllMember(man);

			for (MemberVO manMember : manList) {
				System.out.println();
				System.out.println("회원ID\t이름\t성별\t전화번호\t\t생년월일\t과정ID");
				System.out.println(manMember.getMemberID() + "\t" + manMember.getName() + "\t" + manMember.getGender()
						+ "\t" + manMember.getPhoneNum() + "\t\t" + manMember.getBirth() + "\t"
						+ manMember.getCurriculumID());
			}
			System.out.println("--------------------------------------------------------------------------------");

		} else {
			System.out.println("잘못된 입력입니다.");

		}

	}

	private void selectByName() {

		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("이름 : ");
		String name = scan.nextLine();

		if (name != null || name.equals(" ")) {
			MemberVO member = new MemberVO();
			member.setName(name);
			List<MemberVO> memInfo = projectService.selectAllMember(member);
			System.out.println("회원ID\t이름\t성별\t전화번호\t\t생년월일\t과정ID");
			for (MemberVO memList : memInfo) {
				System.out.println(memList.getMemberID() + "\t" + memList.getName() + "\t" + memList.getGender() + "\t"
						+ memList.getPhoneNum() + "\t\t" + memList.getBirth() + "\t" + memList.getCurriculumID());
			}
			System.out.println("--------------------------------------------------------------------------------");

		} else {
			System.out.println("잘못된 입력입니다.");
		}
	}

	private void allStudent() {
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("회원ID\t이름\t성별\t전화번호\t\t생년월일\t과정ID");
		List<MemberVO> memberlist = projectService.selectAllMember();
		for (MemberVO vo : memberlist) {
			System.out.println(vo.getMemberID() + "\t" + vo.getName() + "\t" + vo.getGender() + "\t" + vo.getPhoneNum()
					+ "\t\t" + vo.getBirth() + "\t" + vo.getCurriculumID());

		}
		System.out.println("--------------------------------------------------------------------------------");

	}

}
