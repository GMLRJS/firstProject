package test;

import java.util.List;
import java.util.Scanner;

import service.InterfaceService;
import service.ProjectService;
import vo.BoardVO;
import vo.CommentVO;
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
			String choice = scan.nextLine();

			
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
				if (projectService.checkBoard(no) > 0) {
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
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println();

	}

}
