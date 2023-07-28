package test;

import java.util.List;
import java.util.Scanner;

import service.InterfaceService;
import service.ProjectService;
import vo.BoardVO;
import vo.CommentVO;
import vo.MemberVO;

public class CommunityPage {

	private static CommunityPage coummunityMain;
	private InterfaceService projectService;
	private MemberVO myAccount = LoginPage.myAccount;
	private Scanner scan;

	private CommunityPage() {
		projectService = new ProjectService();
		scan = new Scanner(System.in);
	}

	public static CommunityPage getInstance() {
		if (coummunityMain == null) {
			coummunityMain = new CommunityPage();
		}
		return coummunityMain;
	}

	// 게시판 선택 메뉴 출력
	public void displayBoard() {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[1] 자유게시판\t\t[2] 상담게시판\t\t[0] 뒤로 가기");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.print(">> ");
	}

	// 게시판 기능 메뉴 출력
	public void displayMenu() {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[1] 전체 게시글 목록   [2] 게시글 열람   [3] 게시글 검색   [4] 게시글 작성   [5] 게시글 수정   [6] 게시글 삭제   [0] 뒤로가기");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.print(">> ");
	}

	// 상담게시판 기능 메뉴 출력
	public void displayCBMenu() {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[1] 전체 게시글 목록   [2] 게시글 열람   [3] 게시글 작성   [4] 게시글 수정   [5] 게시글 삭제   [0] 뒤로가기");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.print(">> ");
	}

	// 시작 메소드
	public void choiceBoard() {

		String choice;
		while (true) {

			displayBoard();
			choice = scan.nextLine();
			switch (choice) {
			case "1": // 자유게시판
				freeBoard();
				break;
			case "2": // 상담게시판
				counselingBoard();
				break;
			case "0": // 뒤로가기
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		}
	}

	// ============================================= 자유게시판
	// ============================================

	// 자유게시판 메뉴
	private void freeBoard() {

		String choice;
		do {
			displayMenu();
			choice = scan.nextLine();
			switch (choice) {
			case "1": // 전체 게시글 목록
				displayAll();
				break;
			case "2": // 게시글 열람
				readBoard();
				break;
			case "3": // 게시글 검색
				searchBoardMenu();
				break;
			case "4": // 게시글 작성
				registBoard();
				break;
			case "5": // 게시글 수정
				modifyBoard();
				break;
			case "6": // 게시글 삭제
				removeBoard();
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (!choice.equals("0"));
	}

	// 게시글 삭제
	private void removeBoard() {

		boolean isExist = false;
		int boardNo = 0;

		do {
			System.out.println("삭제할 게시글의 번호를 입력하세요.\t\t[0] 뒤로가기");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			if (boardNo == 0) {
				return;
			}
			int cnt = projectService.checkBoard(boardNo);
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("번호에 해당하는 게시글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

		String memberID = myAccount.getMemberID();

		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setMemberID(memberID);

		List<BoardVO> list = projectService.selectAllBoard(bv);

		if (list.size() != 0 || myAccount.getMemberID().startsWith("MASTER")) { // 게시글번호와 ID가 일치하는 게시글이 존재할 경우 + 관리자

			boolean b = projectService.deleteBoard(boardNo);

			if (b == true) {
				System.out.println("게시글을 삭제했습니다.");
			} else {
				System.out.println("게시글을 삭제할 수 없습니다.");
			}
		} else {
			System.out.println("본인이 작성한 게시물만 삭제할 수 있습니다.");
		}
	}

	// 게시글 수정
	private void modifyBoard() {

		boolean isExist = false;
		int boardNo = 0;

		do {
			System.out.println("수정할 게시글의 번호를 입력하세요.\t\t[0] 뒤로가기");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			if (boardNo == 0) {
				return;
			}
			int cnt = projectService.checkBoard(boardNo);
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("번호에 해당하는 게시글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

		String memberID = myAccount.getMemberID();

		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setMemberID(memberID);

		List<BoardVO> list = projectService.selectAllBoard(bv);

		if (list.size() != 0 || myAccount.getMemberID().startsWith("MASTER")) { // 게시글번호와 ID가 일치하는 게시글이 존재할 경우

			System.out.print("수정할 게시글 제목 >> ");
			String boardTitle = scan.nextLine();
			if (boardTitle.equals("0")) {
				return;
			}
			System.out.print("수정할 게시글 내용 >> ");
			String boardContent = scan.nextLine();
			if (boardContent.equals("0")) {
				return;
			}
			
			BoardVO bv2 = new BoardVO();
			bv2.setBoardNo(boardNo);
			bv2.setTitle(boardTitle);
			bv2.setContent(boardContent);

			boolean b = projectService.updateBoard_Tit_Con(bv2);

			if (b == true) {
				System.out.println("게시글을 수정했습니다.");
			} else {
				System.out.println("게시글을 수정할 수 없습니다.");
			}
		} else {
			System.out.println("본인이 작성한 게시물만 수정할 수 있습니다.");
		}
	}

	// 자유게시판 게시글 작성
	private void registBoard() {

		String memberID = myAccount.getMemberID();

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ 게시글 작성 ]\t\t[0] 뒤로가기");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("제  목 >> ");
		String boardTitle = scan.nextLine();
		System.out.print("작성자 >> ");
		String boardWriter = scan.nextLine();
		System.out.print("내  용 >> ");
		String boardContent = scan.nextLine();
		if (boardTitle.equals("0") || boardWriter.equals("0") || boardContent.equals("0")) {
			return;
		}
		BoardVO bv = new BoardVO();
		bv.setBoardID("FB");
		bv.setIsNotice("N");
		bv.setMemberID(memberID); // 현재 접속한 사람의 아이디로 memberID 세팅
		bv.setTitle(boardTitle);
		bv.setWriter(boardWriter);
		bv.setContent(boardContent);

		boolean b = projectService.insertBoard(bv);

		if (b == true) {
			System.out.println("게시글을 작성했습니다.");
		} else {
			System.out.println("게시글을 작성할 수 없습니다.");
		}
	}

	// 자유게시판 전체 게시글 목록 출력
	private void displayAll() {

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ 게시글 목록 ]");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"  번호                   제목                             작성자              조회수                  작성일");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		BoardVO bv = new BoardVO();
		bv.setIsNotice("Y");
		bv.setBoardID("FB");
		List<BoardVO> noticeList = projectService.selectAllBoard(bv);

		for (BoardVO bv2 : noticeList) {

			int noticeNo = bv2.getBoardNo();
			String noticeTitle = bv2.getTitle();
			String noticeWriter = bv2.getWriter();
			int noticeHits = bv2.getHits();
			String noticeDate = bv2.getDate();

			System.out.printf("   %d     [공지] %s\t\t\t%s\t\t%d\t\t%s\n", noticeNo, noticeTitle, noticeWriter,
					noticeHits, noticeDate);
		}

		BoardVO bv3 = new BoardVO();
		bv3.setIsNotice("N");
		bv3.setBoardID("FB");
		List<BoardVO> boardList = projectService.selectAllBoard(bv3);

		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {

			for (BoardVO bv4 : boardList) {
				int no = bv4.getBoardNo();
				String title = bv4.getTitle();
				String writer = bv4.getWriter();
				int hits = bv4.getHits();
				String date = bv4.getDate();

				System.out.printf("   %d     %s\t%s              %d               %s\n", no, title, writer, hits, date);
			}
		}
	}

	// 자유게시판 게시글 열람
	private void readBoard() {

		boolean isExist = false;
		int boardNo = 0;

		do {
			System.out.println("열람할 게시글의 번호를 입력하세요.\t\t[0] 뒤로가기");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			int cnt = projectService.checkBoard(boardNo);
			if (boardNo == 0) {
				return;
			}
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("번호에 해당하는 게시글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

		BoardVO bv = projectService.selectBoard(boardNo);
		int no = bv.getBoardNo();
		String title = bv.getTitle();
		String writer = bv.getWriter();
		int hits = bv.getHits();
		String date = bv.getDate();
		String content = bv.getContent();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ " + boardNo + " ] " + title);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("작성자 : %s      조회수 : %d      작성일 : %s\n", writer, hits, date);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("내  용 : " + content);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		// 열람한 게시물의 댓글 출력
		displayComment(no);

		// 댓글 작성,수정,삭제 메뉴 호출
		commentMenu(no);
	}

	// =============================================== 댓글
	// ==============================================

	// 댓글 출력 기능
	public void displayComment(int BoardNo) {

		List<CommentVO> list = projectService.selectAllComment(BoardNo);

		if (list.size() == 0) {
			System.out.println("[ 등록된 댓글이 없습니다. ]");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
		} else {

			System.out.println("[ 댓글 ]");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");

			for (CommentVO cv : list) {
				int commentNo = cv.getCommentNo();
				String commentWriter = cv.getMemberID();
				String commentContent = cv.getCommentContent();
				String commentDate = cv.getDate();

				System.out.printf("[Comment %d]  작성자 : %s 작성일 : %s\n", commentNo, commentWriter, commentDate);
				System.out.println("내 용 : " + commentContent);
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
			}
		}
	}

	// 댓글 관련 메뉴
	public void commentMenu(int boardNo) {

		String choice;
		do {
			System.out.println("[1] 댓글 작성    [2] 댓글 수정    [3] 댓글 삭제    [0] 뒤로가기");
			System.out.print(">> ");
			choice = scan.nextLine();

			switch (choice) {
			case "1": // 댓글 작성
				registComment(boardNo);
				break;
			case "2": // 댓글 수정
				modifyComment();
				break;
			case "3": // 댓글 삭제
				removeComment();
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (!choice.equals("0"));
	}

	// 댓글 작성 기능
	public void registComment(int boardNo) {

		String memberID = myAccount.getMemberID();
		System.out.println("댓글 내용을 입력하세요.\t\t[0] 뒤로가기");
		String commentContent = scan.nextLine();
		if (commentContent.equals("0")) {
			return;
		}
		CommentVO cv = new CommentVO();
		cv.setBoardNo(boardNo);
		cv.setMemberID(memberID); // 댓글 작성자 ID 세팅
		cv.setCommentContent(commentContent);

		boolean b = projectService.insertComment(cv);

		if (b == true) {
			System.out.println("댓글을 작성했습니다.");
		} else {
			System.out.println("댓글을 작성할 수 없습니다.");
		}
	}

	// 댓글 수정 기능
	public void modifyComment() {

		boolean isExist = false;
		int commentNo = 0;

		do {
			System.out.println("수정할 댓글의 번호를 입력하세요.\t\t[0] 뒤로가기");
			System.out.print(">> ");
			try {
				commentNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			int cnt = projectService.checkComment(commentNo);
			if (commentNo == 0) {
				return;
			}
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("번호에 해당하는 댓글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

		String memberID = myAccount.getMemberID();

		CommentVO cv = new CommentVO();
		cv.setCommentNo(commentNo);
		cv.setMemberID(memberID);

		CommentVO cv2 = projectService.selectComment(cv);
		
		if (cv2.getMemberID().equals(memberID) || myAccount.getMemberID().startsWith("MASTER")) { // 댓글번호와 ID가 일치하는 댓글이 존재할 경우 + 관리자

			System.out.print("수정할 댓글 내용 : ");
			String commentContent = scan.nextLine();
			if (commentContent.equals("0")) {
				return;
			}
			CommentVO cv3 = new CommentVO();
			cv3.setCommentNo(commentNo);
			cv3.setCommentContent(commentContent);

			boolean b = projectService.updateComment(cv3);

			if (b == true) {
				System.out.println("댓글을 수정했습니다.");
			} else {
				System.out.println("댓글을 수정할 수 없습니다.");
			}
		} else {
			System.out.println("본인이 작성한 댓글만 수정할 수 있습니다.");
		}
	}

	// 댓글 삭제 기능
	public void removeComment() {

		boolean isExist = false;
		int commentNo = 0;

		do {
			System.out.println("삭제할 댓글의 번호를 입력하세요.\t\t[0] 뒤로가기");
			System.out.print(">> ");
			try {
				commentNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			int cnt = projectService.checkComment(commentNo);
			if (commentNo == 0) {
				return;
			}
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("번호에 해당하는 댓글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

		String memberID = myAccount.getMemberID();

		CommentVO cv = new CommentVO();
		cv.setCommentNo(commentNo);
		cv.setMemberID(memberID);

		CommentVO cv2 = projectService.selectComment(cv);

		if (cv2.getMemberID().equals(memberID) || myAccount.getMemberID().startsWith("MASTER")) { // 댓글번호와 ID가 일치하는 댓글이 존재할 경우 + 관리자

			boolean b = projectService.deleteComment(commentNo);

			if (b == true) {
				System.out.println("댓글을 삭제했습니다.");
			} else {
				System.out.println("댓글을 삭제할 수 없습니다.");
			}
		} else {
			System.out.println("본인이 작성한 댓글만 삭제할 수 있습니다.");
		}
	}

	// 자유게시판 게시글 검색 메뉴
	private void searchBoardMenu() {
		String choice;
		do {
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out
					.println("[1] 게시글 번호로 검색   [2] 제목으로 검색   [3] 내용으로 검색   [4] 작성자로 검색   [5] 여러가지 조건으로 검색   [0] 뒤로가기");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.print(">> ");
			choice = scan.nextLine();

			switch (choice) {
			case "1": // 게시글 번호로 검색
				searchByBoardNo();
				break;
			case "2": // 제목으로 검색
				searchByTitle();
				break;
			case "3": // 내용으로 검색
				searchByContent();
				break;
			case "4": // 작성자로 검색
				searchByWriter();
				break;
			case "5": // 여러가지 조건으로 검색
				searchBoard();
				break;
			case "0": // 뒤로가기
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (!choice.equals("0"));
	}

	// 게시글 번호로 검색
	private void searchByBoardNo() {

		boolean isExist = false;
		int boardNo = 0;

		do {
			System.out.println("검색할 게시글 번호를 입력하세요.\t\t[0] 뒤로가기");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			int cnt = projectService.checkBoard(boardNo);
			if (boardNo == 0) {
				return;
			}
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("검색된 게시글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

		BoardVO bv = projectService.selectBoard(boardNo);

		int no = bv.getBoardNo();
		String title = bv.getTitle();
		String writer = bv.getWriter();
		int hits = bv.getHits();
		String date = bv.getDate();
		String content = bv.getContent();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ " + boardNo + " ] " + title);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("작성자 : %s      조회수 : %d      작성일 : %s\n", writer, hits, date);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("내  용 : " + content);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		// 열람한 게시물의 댓글 출력
		displayComment(no);

		searchBoardMenu();
	}

	// 제목으로 검색
	private void searchByTitle() {

		System.out.println("검색할 게시글 제목을 입력하세요.\t\t[0] 뒤로가기");
		System.out.print(">> ");
		String boardTitle = scan.nextLine();
		if (boardTitle.equals("0")) {
			return;
		}

		BoardVO bv = new BoardVO();
		bv.setBoardID("FB");
		bv.setTitle(boardTitle);

		List<BoardVO> boardList = projectService.selectAllBoard(bv);
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[ 게시글 목록 ]");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("번호        제목             작성자             조회수             작성일");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");

			for (BoardVO bv2 : boardList) {
				int no = bv2.getBoardNo();
				String title = bv2.getTitle();
				String writer = bv2.getWriter();
				int hits = bv2.getHits();
				String date = bv2.getDate();

				System.out.printf("%d      %s      %s      %d      %s\n", no, title, writer, hits, date);
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
		}
		readBoard();
	}

	// 내용으로 검색
	private void searchByContent() {

		System.out.println("검색할 게시글 내용을 입력하세요.\t\t[0] 뒤로가기");
		System.out.print(">> ");
		String boardContent = scan.nextLine();
		if (boardContent.equals("0")) {
			return;
		}

		BoardVO bv = new BoardVO();
		bv.setBoardID("FB");
		bv.setTitle(boardContent);

		List<BoardVO> boardList = projectService.selectAllBoard(bv);
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[ 게시글 목록 ]");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("번호        제목             작성자             조회수             작성일");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");

			for (BoardVO bv2 : boardList) {
				int no = bv2.getBoardNo();
				String title = bv2.getTitle();
				String writer = bv2.getWriter();
				int hits = bv2.getHits();
				String date = bv2.getDate();

				System.out.printf("%d      %s      %s      %d      %s\n", no, title, writer, hits, date);
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
		}
		readBoard();
	}

	// 작성자로 검색
	private void searchByWriter() {

		System.out.println("검색할 게시글 작성자를 입력하세요.\t\t[0] 뒤로가기");
		System.out.print(">> ");
		String boardWriter = scan.nextLine();
		if (boardWriter.equals("0")) {
			return;
		}
		BoardVO bv = new BoardVO();
		bv.setBoardID("FB");
		bv.setWriter(boardWriter);

		List<BoardVO> boardList = projectService.selectAllBoard(bv);
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[ 게시글 목록 ]");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("번호        제목             작성자             조회수             작성일");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");

			for (BoardVO bv2 : boardList) {
				int no = bv2.getBoardNo();
				String title = bv2.getTitle();
				String writer = bv2.getWriter();
				int hits = bv2.getHits();
				String date = bv2.getDate();

				System.out.printf("%d      %s      %s      %d      %s\n", no, title, writer, hits, date);
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
		}
		readBoard();
	}

	// 여러가지 조건으로 검색
	private void searchBoard() {

		System.out.println("검색할 조건을 입력하세요. (검색하지 않을 조건은 엔터로 스킵)\t\t[0] 뒤로가기");

		System.out.print("게시글 제목 >> ");
		String boardTitle = scan.nextLine().trim();
		if (boardTitle.equals("0")) {
			return;
		}

		System.out.print("게시글 내용 >> ");
		String boardContent = scan.nextLine().trim();
		if (boardContent.equals("0")) {
			return;
		}

		System.out.print("작성자 >> ");
		String boardWriter = scan.nextLine().trim();
		if (boardWriter.equals("0")) {
			return;
		}

		BoardVO bv = new BoardVO();
		bv.setBoardID("FB");
		bv.setTitle(boardTitle);
		bv.setContent(boardContent);
		bv.setWriter(boardWriter);

		List<BoardVO> boardList = projectService.selectAllBoard(bv);
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[ 게시글 목록 ]");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("번호        제목             작성자             조회수             작성일");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");

			for (BoardVO bv2 : boardList) {
				int no = bv2.getBoardNo();
				String title = bv2.getTitle();
				String writer = bv2.getWriter();
				int hits = bv2.getHits();
				String date = bv2.getDate();

				System.out.printf("%d      %s      %s      %d      %s\n", no, title, writer, hits, date);
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
		}
		readBoard();
	}

	// ============================================= 상담게시판
	// ============================================

	// 상담게시판 메뉴
	private void counselingBoard() {

		String choice;
		while (true) {

			displayCBMenu();
			choice = scan.nextLine();
			switch (choice) {
			case "1": // 전체 게시글 목록
				displayAllCB();
				continue;
			case "2": // 게시글 열람
				readBoardCB();
				continue;
			case "3": // 게시글 작성
				registCB();
				continue;
			case "4": // 게시글 수정
				modifyBoard();
				continue;
			case "5": // 게시글 삭제
				removeBoard();
				continue;
			case "0":
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		}
	}

	// 상담게시판 게시글 작성
	private void registCB() {

		String memberID = myAccount.getMemberID();

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ 게시글 작성 ]\t\t[0] 뒤로가기");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("** 상담게시판은 작성자와 선생님만 열람할 수 있습니다. **");
		System.out.print("제  목 >> ");
		String boardTitle = scan.nextLine();
		if (boardTitle.equals("0")) {
			return;
		}
		System.out.print("내  용 >> ");
		String boardContent = scan.nextLine();
		if (boardContent.equals("0")) {
			return;
		}
		if (boardTitle == null || boardTitle.equals(" ") && boardContent == null || boardContent.equals(" ")) {
			System.out.println("다시 입력해주세요.");
			registCB();
		} else {
			BoardVO bv = new BoardVO();
			bv.setBoardID("CB");
			bv.setIsNotice("N");
			bv.setMemberID(memberID); // 현재 접속한 사람의 아이디로 memberID 세팅
			bv.setTitle(boardTitle);
			bv.setWriter(memberID);
			bv.setContent(boardContent);

			boolean b = projectService.insertBoard(bv);

			if (b == true) {
				System.out.println("게시글을 작성했습니다.");
			} else {
				System.out.println("게시글을 작성할 수 없습니다.");
			}
		}
	}

	// 상담게시판 게시글 열람
	private void readBoardCB() {

		boolean isExist = false;
		int boardNo = 0;

		do {
			System.out.println("열람할 게시글의 번호를 입력하세요.\t\t[0] 뒤로 가기");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
				if (boardNo == 0) {
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			int cnt = projectService.checkBoard(boardNo);
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("번호에 해당하는 게시글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);

		BoardVO bv = projectService.selectBoard(boardNo);
		int no = bv.getBoardNo();
		String title = bv.getTitle();
		String writer = bv.getWriter();
		int hits = bv.getHits();
		String date = bv.getDate();
		String content = bv.getContent();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ " + boardNo + " ] " + title);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("작성자 : %s      조회수 : %d      작성일 : %s\n", writer, hits, date);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("내  용 : " + content);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		// 열람한 게시물의 댓글 출력
		displayComment(no);

		// 댓글 작성,수정,삭제 메뉴 호출
		commentMenu(no);
	}

	// 상담게시판 전체 게시글 목록 출력
	private void displayAllCB() {

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ 게시글 목록 ]");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"  번호                   제목                             작성자              조회수                  작성일");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		String memberID = myAccount.getMemberID();

		BoardVO bv3 = new BoardVO();
		bv3.setIsNotice("N");
		bv3.setBoardID("CB");
		bv3.setMemberID(memberID);
		List<BoardVO> boardList = projectService.selectAllBoard(bv3); // ID로 상담게시판에 작성한 글이 있는지 검색

		if (boardList.size() == 0) { // 작성한 게시글이 없는 경우
			System.out.println("작성한 게시글이 없습니다.");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");

		} else { // 작성한 게시글이 있는 경우

			for (BoardVO bv4 : boardList) {
				int no = bv4.getBoardNo();
				String title = bv4.getTitle();
//				String writer = bv4.getWriter();
				int hits = bv4.getHits();
				String date = bv4.getDate();

				System.out.printf("   %d     %s\t%s              %d               %s\n", no, title, "비공개    ", hits,
						date);
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			readBoardCB(); // 상담게시판에 작성한 게시물이 있을 때만 읽기 기능 메소드 호출
		}
	}
}
