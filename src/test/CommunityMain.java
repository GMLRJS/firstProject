package test;

import java.util.List;
import java.util.Scanner;

import dao.BoardDAO;
import dao.BoardListDAO;
import dao.CommentDAO;
import service.InterfaceService;
import service.ProjectService;
import vo.BoardVO;
import vo.CommentVO;

public class CommunityMain {
	
	private static CommunityMain coummunityMain;
	private InterfaceService projectService;
	private Scanner scan;
	
	private CommunityMain() {
		projectService = new ProjectService();
		scan = new Scanner(System.in);
	}
	
	public static CommunityMain getInstance() {
		if (coummunityMain == null) {
			coummunityMain = new CommunityMain();
		}
		return coummunityMain;
	}
	
	// 게시판 선택 메뉴 출력
	public void displayBoard() {
		
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[1] 자유게시판\t\t[2] 상담게시판");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.print(">> ");
	}
	
	// 게시판 기능 메뉴 출력
	public void displayMenu() {
		
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[1] 전체 게시글 목록   [2] 게시글 열람   [3] 게시글 검색   [4] 게시글 작성   [5] 게시글 수정   [6] 게시글 삭제   [0] 뒤로가기");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.print(">> ");
	}
	
	// 시작 메소드
	public void choiceBoard() {
		
		String choice;
		do {
			displayBoard();
			choice = scan.nextLine();
			switch (choice){
				case "1" :   // 자유게시판
					freeBoard();
					break;
				case "2" :   // 상담게시판
					counselingBoard();
					break;
				default :
					System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (choice != "0");
	}

	// 상담게시판
	private void counselingBoard() {

		String choice;
		do {
			displayMenu();
			choice = scan.nextLine();
			switch (choice){
				case "1" :   // 전체 게시글 목록
					displayAllCB();
					break;
				case "2" :   // 게시글 열람
					readBoardCB();
					break;
				case "3" :   // 게시글 검색
//					searchCBMenu();
					break;
				case "4" :   // 게시글 작성
//					registCB();
					break;
				case "5" :   // 게시글 수정
//					modifyCB();
					break;
				case "6" :   // 게시글 삭제
//					removeCB();
					break;
				case "0" :
//					displayCB();  // 뒤로가기 (게시판 선택 메뉴)
					break;
				default :
					System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (choice != "0");
		
	}
	
	// 상담게시판 게시글 열람    =>   작성자와 선생님만 가능하도록 수정
	private void readBoardCB() {
		
		boolean isExist = false;
		int boardNo = 0;
		
		do {
			System.out.println("열람할 게시글의 번호를 입력하세요.");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				readBoardCB();
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
//		String writer = bv.getWriter();
		int hits = bv.getHits();
		String date = bv.getDate();
		String content = bv.getContent();
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ " + boardNo + " ] " + title);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("작성자 : %s      조회수 : %d      작성일 : %s\n", "비공개", hits, date);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("내  용 : " + content);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		
		// 열람한 게시물의 댓글 출력
		displayComment(no);

		// 댓글 작성,수정,삭제 메뉴 호출
		commentMenu(no);
		
	}

	// 상담게시판 전체 게시글 목록 출력
	private void displayAllCB() {
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ 게시글 목록 ]");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("  번호                   제목                             작성자              조회수                  작성일");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		
		BoardVO bv = new BoardVO();
		bv.setNotice("Y");
		bv.setBoardID("CB");
		List<BoardVO> noticeList = projectService.selectAllBoard(bv);
		

		for (BoardVO bv2 : noticeList) {
			
			int noticeNo = bv2.getBoardNo();
			String noticeTitle = bv2.getTitle();
			String noticeWriter = bv2.getWriter();
			int noticeHits = bv2.getHits();
			String noticeDate = bv2.getDate();
			
			System.out.printf("   %d     [공지] %s\t\t\t%s\t\t%d\t\t%s\n",noticeNo, noticeTitle, noticeWriter, noticeHits, noticeDate);
		}
		
		BoardVO bv3 = new BoardVO();
		bv3.setNotice("N");
		bv3.setBoardID("CB");
		List<BoardVO> boardList = projectService.selectAllBoard(bv3);
		
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {

			for (BoardVO bv4 : boardList) {
				int no = bv4.getBoardNo();
				String title = bv4.getTitle();
//				String writer = bv4.getWriter();
				int hits = bv4.getHits();
				String date = bv4.getDate();
				
				System.out.printf("   %d     %s\t%s              %d               %s\n", no, title, "비공개    ", hits, date);
			}
		}
		
	}

	// 자유게시판
	private void freeBoard() {
		
		String choice;
		do {
			displayMenu();
			choice = scan.nextLine();
			switch (choice){
				case "1" :   // 전체 게시글 목록
					displayAll();
					break;
				case "2" :   // 게시글 열람
					readBoard();
					break;
				case "3" :   // 게시글 검색
					searchBoardMenu();
					break;
				case "4" :   // 게시글 작성
					registBoard();
					break;
				case "5" :   // 게시글 수정
					modifyBoard();
					break;
				case "6" :   // 게시글 삭제
					removeBoard();
					break;
				case "0" :
					displayBoard();  // 뒤로가기 (게시판 선택 메뉴)
					break;
				default :
					System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (choice != "0");
	}

	
	// 자유게시판 게시글 삭제
	private void removeBoard() {
		
		boolean isExist = false;
		int boardNo = 0;
		
		do {
			System.out.println("삭제할 게시글의 번호를 입력하세요.");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				removeBoard();
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
		
		boolean b = projectService.deleteBoard(boardNo);
		
		if (b == true) {
			System.out.println("게시글을 삭제했습니다.");
		} else {
			System.out.println("게시글을 삭제할 수 없습니다.");
		}
	}

	// 자유게시판 게시글 수정
	private void modifyBoard() {
		
		boolean isExist = false;
		int boardNo = 0;
		
		do {
			System.out.println("수정할 게시글의 번호를 입력하세요.");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				modifyBoard();
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
		
		System.out.print("수정할 게시글 제목 >> ");
		String boardTitle = scan.nextLine();
		System.out.print("수정할 게시글 내용 >> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setTitle(boardTitle);
		bv.setContent(boardContent);
		
		boolean b = projectService.updateBoard_Tit_Con(bv);
		
		if (b == true) {
			System.out.println("게시글을 수정했습니다.");
		} else {
			System.out.println("게시글을 수정할 수 없습니다.");
		}
	}

	// 자유게시판 게시글 작성
	private void registBoard() {
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ 게시글 작성 ]");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("제  목 >> ");
		String boardTitle = scan.nextLine();
		System.out.print("작성자 >> ");
		String boardWriter = scan.nextLine();
		System.out.print("내  용 >> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardID("FB");
		bv.setNotice("N");
		bv.setMemberID("LKB93");  // 현재 접속한 사람의 아이디를 가져오도록 변경
		bv.setTitle(boardTitle);
		bv.setWriter(boardWriter);
		bv.setContent(boardContent);
		
		boolean b = projectService.insertBoard(bv);
		
		if (b == true) {
			System.out.println("게시글을 작성했습니다.");
		} else {
			System.out.println("게시글 작성에 실패했습니다.");
		}
	}

	// 자유게시판 전체 게시글 목록 출력
	private void displayAll() {

		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ 게시글 목록 ]");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("  번호                   제목                             작성자              조회수                  작성일");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		
		BoardVO bv = new BoardVO();
		bv.setNotice("Y");
		bv.setBoardID("FB");
		List<BoardVO> noticeList = projectService.selectAllBoard(bv);
		
		for (BoardVO bv2 : noticeList) {
			
			int noticeNo = bv2.getBoardNo();
			String noticeTitle = bv2.getTitle();
			String noticeWriter = bv2.getWriter();
			int noticeHits = bv2.getHits();
			String noticeDate = bv2.getDate();
			
			System.out.printf("   %d     [공지] %s\t\t\t%s\t\t%d\t\t%s\n",noticeNo, noticeTitle, noticeWriter, noticeHits, noticeDate);
		}
		
		BoardVO bv3 = new BoardVO();
		bv3.setNotice("N");
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
			System.out.println("열람할 게시글의 번호를 입력하세요.");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				readBoard();
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
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ " + boardNo + " ] " + title);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("작성자 : %s      조회수 : %d      작성일 : %s\n", writer, hits, date);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("내  용 : " + content);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		
		// 열람한 게시물의 댓글 출력
		displayComment(no);

		// 댓글 작성,수정,삭제 메뉴 호출
		commentMenu(no);
	}
	
	// 자유게시판 댓글 출력 기능
	public void displayComment(int BoardNo) {
		
		List<CommentVO> list = projectService.selectAllComment(BoardNo);
		
		if (list.size() == 0) {
			System.out.println("[ 등록된 댓글이 없습니다. ]");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		} else {
			
			System.out.println("[ 댓글 ]");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			
			for (CommentVO cv : list) {
				int commentNo = cv.getCommentNo();
				String commentWriter = cv.getMemberID();
				String commentContent = cv.getCommentContent();
				String commentDate = cv.getDate();
				
				System.out.printf("[Comment %d]  작성자 : %s 작성일 : %s\n", commentNo, commentWriter, commentDate);
				System.out.println("내 용 : " + commentContent);
				System.out.println("------------------------------------------------------------------------------------------------------------------------------");
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
			
			switch (choice){
				case "1" :   // 댓글 작성
					registComment(boardNo);
					break;
				case "2" :   // 댓글 수정
					modifyComment();
					break;
				case "3" :   // 댓글 삭제
					removeComment();
					break;
				case "0" :
					freeBoard();	
					break;
				default :
					System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (choice != "0");
	}
	
	// 댓글 작성 기능
	public void registComment(int boardNo) {
		
		System.out.print("아이디를 입력하세요.");  // id를 가져오도록 변경
		String memberID = scan.nextLine();
		System.out.println("댓글 내용을 입력하세요.");
		String commentContent = scan.nextLine();
		
		CommentVO cv = new CommentVO();
		cv.setBoardNo(boardNo);
		cv.setMemberID(memberID);
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
			System.out.println("수정할 댓글의 번호를 입력하세요.");
			System.out.print(">> ");
			try {
				commentNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				modifyComment();
			}
			int cnt = projectService.checkComment(commentNo);
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("번호에 해당하는 댓글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);
		
		System.out.print("수정할 댓글 내용 : ");
		String commentContent = scan.nextLine();
		CommentVO cv = new CommentVO();
		cv.setCommentNo(commentNo);
		cv.setCommentContent(commentContent);
		
		boolean b = projectService.updateComment(cv);
		
		if (b == true) {
			System.out.println("댓글을 수정했습니다.");
		} else {
			System.out.println("댓글을 수정할 수 없습니다.");
		}
	}
	
	// 댓글 삭제 기능
	public void removeComment() {
		
		boolean isExist = false;
		int commentNo = 0;
		
		do {
			System.out.println("삭제할 댓글의 번호를 입력하세요.");
			System.out.print(">> ");
			try {
				commentNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				removeComment();
			}
			int cnt = projectService.checkComment(commentNo);
			if (cnt == 1) {
				isExist = true;
			}
			if (!isExist) {
				System.out.println("번호에 해당하는 댓글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);
		
		boolean b = projectService.deleteComment(commentNo);
		
		if (b == true) {
			System.out.println("댓글을 삭제했습니다.");
		} else {
			System.out.println("댓글을 삭제할 수 없습니다.");
		}
	}
	
	
	// 자유게시판 게시글 검색 메뉴
	private void searchBoardMenu() {
		String choice;
		do {
			System.out.println("[1] 게시글 번호로 검색   [2] 제목으로 검색   [3] 내용으로 검색   [4] 작성자로 검색   [5] 여러가지 조건으로 검색   [0] 뒤로가기");
			System.out.print(">> ");
			choice = scan.nextLine();
			
			switch (choice){
				case "1" :   // 게시글 번호로 검색
					searchByBoardNo();
					break;
				case "2" :   // 제목으로 검색
					searchByTitle();
					break;
				case "3" :   // 내용으로 검색
					searchByContent();
					break;
				case "4" :   // 작성자로 검색
					searchByWriter();
					break;
				case "5" :   // 여러가지 조건으로 검색
					searchBoard();
					break;
				case "0" :   // 뒤로가기
					freeBoard();	
					break;
				default :
					System.out.println("잘못 입력했습니다. 다시 입력하세요");
			}
		} while (choice != "0");
	}
	
	// 게시글 번호로 검색
	private void searchByBoardNo() {
		
		boolean isExist = false;
		int boardNo = 0;
		
		do {
			System.out.println("검색할 게시글 번호를 입력하세요.");
			System.out.print(">> ");
			try {
				boardNo = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
				searchByBoardNo();
			}
			int cnt = projectService.checkBoard(boardNo);
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
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ " + boardNo + " ] " + title);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("작성자 : %s      조회수 : %d      작성일 : %s\n", writer, hits, date);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("내  용 : " + content);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		
		// 열람한 게시물의 댓글 출력
		displayComment(no);
		
		searchBoardMenu();
	}

	// 제목으로 검색
	private void searchByTitle() {
		
		System.out.println("검색할 게시글 제목을 입력하세요.");
		System.out.print(">> ");
		String boardTitle = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setTitle(boardTitle);
		
		List<BoardVO> boardList = projectService.selectAllBoard(bv);
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[ 게시글 목록 ]");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("번호        제목             작성자             조회수             작성일");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			
			for (BoardVO bv2 : boardList) {
				int no = bv2.getBoardNo();
				String title = bv2.getTitle();
				String writer = bv2.getWriter();
				int hits = bv2.getHits();
				String date = bv2.getDate();
				
				System.out.printf("%d      %s      %s      %d      %s\n", no, title, writer, hits, date);
			}
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		}
		readBoard();
	}

	// 내용으로 검색
	private void searchByContent() {
		
		System.out.println("검색할 게시글 내용을 입력하세요.");
		System.out.print(">> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setTitle(boardContent);
		
		List<BoardVO> boardList = projectService.selectAllBoard(bv);
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[ 게시글 목록 ]");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("번호        제목             작성자             조회수             작성일");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			
			for (BoardVO bv2 : boardList) {
				int no = bv2.getBoardNo();
				String title = bv2.getTitle();
				String writer = bv2.getWriter();
				int hits = bv2.getHits();
				String date = bv2.getDate();
				
				System.out.printf("%d      %s      %s      %d      %s\n", no, title, writer, hits, date);
			}
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		}
		readBoard();
	}

	// 작성자로 검색
	private void searchByWriter() {
		
		System.out.println("검색할 게시글 작성자를 입력하세요.");
		System.out.print(">> ");
		String boardWriter = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setWriter(boardWriter);
		
		List<BoardVO> boardList = projectService.selectAllBoard(bv);
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[ 게시글 목록 ]");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("번호        제목             작성자             조회수             작성일");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			
			for (BoardVO bv2 : boardList) {
				int no = bv2.getBoardNo();
				String title = bv2.getTitle();
				String writer = bv2.getWriter();
				int hits = bv2.getHits();
				String date = bv2.getDate();
				
				System.out.printf("%d      %s      %s      %d      %s\n", no, title, writer, hits, date);
			}
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		}
		readBoard();
	}

	// 여러가지 조건으로 검색
	private void searchBoard() {
		
		System.out.println("검색할 조건을 입력하세요. (검색하지 않을 조건은 엔터로 스킵)");
		
		System.out.print("게시글 제목 >> ");
		String boardTitle = scan.nextLine().trim();
		
		System.out.print("게시글 내용 >> ");
		String boardContent = scan.nextLine().trim();
		
		System.out.print("작성자 >> ");
		String boardWriter = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setTitle(boardTitle);
		bv.setContent(boardContent);
		bv.setWriter(boardWriter);
		
		List<BoardVO> boardList = projectService.selectAllBoard(bv);
		if (boardList.size() == 0) {
			System.out.println("게시글이 없습니다.");
		} else {
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("[ 게시글 목록 ]");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("번호        제목             작성자             조회수             작성일");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			
			for (BoardVO bv2 : boardList) {
				int no = bv2.getBoardNo();
				String title = bv2.getTitle();
				String writer = bv2.getWriter();
				int hits = bv2.getHits();
				String date = bv2.getDate();
				
				System.out.printf("%d      %s      %s      %d      %s\n", no, title, writer, hits, date);
			}
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		}
		readBoard();
	}
	public static void main(String[] args) {
		CommunityMain.getInstance().choiceBoard();
	}
}



























