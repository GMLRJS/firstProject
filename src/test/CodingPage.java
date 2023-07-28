package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import service.ProjectService;
import vo.CodingTestVO;
import vo.MemberVO;

public class CodingPage {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private MemberVO myAccount = LoginPage.myAccount;

	private Scanner scan = new Scanner(System.in);
	private ProjectService testcoding;
	SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public CodingPage() {

		testcoding = new ProjectService();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[1] 코딩 자료 입력   [2] 전체 출력   [3] 코딩 테스트 문제 출력   [4] 코딩 테스트 문제 삭제   [5] 코딩 문제 검색   [0] 뒤로가기");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.print(">> ");
	}

	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = Integer.parseInt(scan.nextLine()); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 코딩 자료 입력
				insertCoding();
				break;
			case 2: // 코딩 자료 전체 출력
				selectAllCoding();
				break;
			case 3: // 코딩 테스트 문제 출력
				selectAllCodingTest();
				break;
			case 4: // 코딩 테스트 문제 삭제.
				deleteCoding();
				break;
			case 5: // 코딩 문제 검색
				searchCoding();
				break;
			case 0: // 뒤로가기 추가
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (choice != 0);
	}

	private void searchCoding() {

		System.out.println("검색할 제목을 입력하세요 \t\t[0]뒤로가기");
		System.out.print("제목 : ");
		String title = scan.nextLine().trim();
		if (title.equals("0")) {
		} else {
			System.out.print("레벨 : ");
			String levell = scan.nextLine().trim();
			if (levell.equals("0")) {
			} else {
				System.out.print("키워드 : ");
				String content = scan.nextLine().trim();
				if (content.equals("0")) {
				} else {
					System.out.print("과목 코드 : ");
					String subId = scan.nextLine().trim();
					if (subId.equals("0")) {
					} else {
						CodingTestVO vo = new CodingTestVO();
						vo.setTitle(title);
						vo.setLevell(levell);
						vo.setCodingContent(content);
						vo.setSubjectID(subId);

						List<CodingTestVO> codingTestList = testcoding.searchCoding(vo);
						if (codingTestList.size() == 0) {
							System.out.println("검색된 데이터가 없습니다.");
						} else {
							for (CodingTestVO pv1 : codingTestList) {

								int no = pv1.getProblemNo();
								String ctTitle = pv1.getTitle();
								String level = pv1.getLevell();
								String date = dbDate.format(pv1.getDate());
								String ctContent = pv1.getCodingContent();

								System.out.println(
										"------------------------------------------------------------------------------------------------------------------------------");
								System.out.println("[ " + no + " ] " + ctTitle);
								System.out.println(
										"------------------------------------------------------------------------------------------------------------------------------");
								System.out.printf("레벨 : %s      작성일 : %s\n", level, date);
								System.out.println(
										"------------------------------------------------------------------------------------------------------------------------------");
								System.out.println("내  용 : " + ctContent);
								System.out.println(
										"------------------------------------------------------------------------------------------------------------------------------");
							}
						}
					}
				}

			}
		}
	}

	private void deleteCoding() {
		if (myAccount.getMemberID().startsWith("MASTER")) {
			System.out.println();
			System.out.println("삭제할 게시글번호를 입력하세요. \t\t[0]뒤로가기");
			System.out.print(">> ");
			int problemNo = Integer.parseInt(scan.nextLine());
			if (problemNo == 0) {

			} else {
				boolean isExist = testcoding.deleteCodingTest(problemNo);

				if (isExist == true) {
					System.out.println("코딩테스트 게시글을 삭제했습니다.");
				} else {
					System.out.println("코딩테스트 게시글을 삭제할 수 없습니다.");
				}
			}
		} else {
			System.out.println("권한이 없는 사용자입니다.");
		}
	}

	private void selectAllCodingTest() {
		String title = "";
		while (true) {

			System.out.println("풀고싶은 언어를 선택해주세요");
			System.out.println("[1] 자바\t[2] 자바 스크립트\t[3] 데이터베이스\t\t[0]뒤로가기");

			System.out.print(">> ");
			String choice1 = scan.nextLine();
			switch (choice1) {
			case "1":
				System.out.println("[1] ArrayList\t[2] HashMap\t[3] HashSet\t\t[0]뒤로가기");
				System.out.println(">> ");
				String choice2 = scan.nextLine();

				switch (choice2) {
				case "1":
					title = "자바/ArrayList";
					break;
				case "2":
					title = "자바/HashMap";
					break;
				case "3":
					title = "자바/HashSet";
					break;
				case "0":
					return;
				default:
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시 입력해주세요.");
					continue;
				}
				break;
			case "2":
				System.out.println("[1] 초급\t[2] 중급\t[3] 고급\t\t[0]뒤로가기");
				System.out.println(">> ");
				choice2 = scan.nextLine();
				switch (choice2) {
				case "1":
					title = "자바스크립트/초급";
					break;
				case "2":
					title = "자바스크립트/중급";
					break;
				case "3":
					title = "자바스크립트/고급";
					break;
				case "0":
					return;
				default:
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시 입력해주세요.");
					continue;
				}
				break;
			case "3":

				System.out.println("[1] 초급\t[2] 중급\t[3] 고급\t\t[0]뒤로가기");
				System.out.println(">> ");
				choice2 = scan.nextLine();
				switch (choice2) {
				case "1":
					title = "데이터베이스/초급";
					break;
				case "2":
					title = "데이터베이스/중급";
					break;
				case "3":
					title = "데이터베이스/고급";
					break;
				case "0":
					return;
				default:
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시 입력해주세요.");
					continue;

				}
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
				continue;
			}

			CodingTestVO pv = new CodingTestVO();
			pv.setTitle(title);

			List<CodingTestVO> codingTestList = testcoding.selectCodingTest(pv);

			System.out.println();
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(" 코딩 제목 / 코딩 레벨 ");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");

			if (codingTestList.size() == 0) {
				System.out.println("검색된 게시글이 없습니다.");
				System.out.println("다시 입력해주세요.");
				continue;
			} else {
				for (CodingTestVO pv1 : codingTestList) {
					System.out.println(pv1.getTitle() + "      " + pv1.getLevell());
					System.out.println(
							"------------------------------------------------------------------------------------------------------------------------------");
				}
			}

			System.out.println();
			System.out.println("도전 하고싶은 레벨를 선택주세요\t\t[0]뒤로가기");
			System.out.print(">>");
			String levell = scan.nextLine();
			if (levell.equals("0")) {
			} else {
				pv.setLevell(levell);

				List<CodingTestVO> codingTestList1 = testcoding.searchLevel(pv);
				System.out.println("문제입니다.");

				if (codingTestList1.size() == 0) {
					System.out.println("조회된 레벨가 없습니다.");
					System.out.println("다시 입력해주세요.");
					continue;
				} else {
					for (CodingTestVO pv2 : codingTestList1) {
						System.out.println(pv2.getCodingContent());
						System.out.println(
								"------------------------------------------------------------------------------------------------------------------------------");
					}
				}

				System.out.println("정답을 확인하시겠습니까? \t\t[1] 예 \t [2] 아니오 ");
				System.out.print(">>");
				int choice = Integer.parseInt(scan.nextLine());
				List<CodingTestVO> codingTestList2 = testcoding.choiceAnswer(pv);

				if (choice == 1) {
					for (CodingTestVO pv3 : codingTestList2) {
						System.out.println("레벨 : " + pv.getLevell());
						System.out.println("제목 : " + pv.getTitle());
						System.out.println(pv3.getAnswer());
					}
				}
			}
		}

	}

	private void selectAllCoding() {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		List<CodingTestVO> codingList = testcoding.selectAllCodingTest();

		if (codingList.size() == 0) {
			System.out.println("검색된 게시글이 없습니다.");
			System.out.println("다시 입력해주세요.");
		} else {
			for (CodingTestVO pv1 : codingList) {

				int no = pv1.getProblemNo();
				String title = pv1.getTitle();
				String level = pv1.getLevell();
				String date = dbDate.format(pv1.getDate());
				String content = pv1.getCodingContent();

				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("[ " + no + " ] " + title);
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("레벨 : %s      작성일 : %s\n", level, date);
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("내  용 : " + content);
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
			}
		}
	}

	private void insertCoding() {
		if (myAccount.getMemberID().startsWith("MASTER")) {
			System.out.println();
			System.out.println("새롭게 등록할 코딩테스트 입력하세요. \t\t[0]뒤로가기");
			System.out.print("코딩 제목 : ");
			String title = scan.nextLine();
			if (title.equals("0")) {
			} else {
				System.out.print("코딩 레벨 : ");
				String levell = scan.nextLine();
				if (levell.equals("0")) {
				} else {
					System.out.print("코딩 내용 : ");
					String codingContent = scan.nextLine();
					if (codingContent.equals("0")) {
					} else {
						System.out.print("과목 코드 : ");
						String subjectID = scan.nextLine();
						if (subjectID.equals("0")) {
						} else {
							System.out.println("답변 : ");
							String answer = scan.nextLine();
							if (answer.equals("0")) {
							} else {
								CodingTestVO pv = new CodingTestVO();

								pv.setTitle(title);
								pv.setLevell(levell);
								pv.setCodingContent(codingContent);
								pv.setSubjectID(subjectID);
								pv.setAnswer(answer);

								boolean cnt = testcoding.insertCodingTest(pv);

								if (cnt = true) {
									System.out.println("코딩 테스트 문제를 추가했습니다.");
								} else {
									System.out.println("코딩 테스트 문제를 추가할 수 없습니다.");
								}
							}

						}

					}

				}
			}
		} else {
			System.out.println("권한이 없는 사용자입니다.");
		}
	}

}
