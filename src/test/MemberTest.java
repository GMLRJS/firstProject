package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import service.ProjectService;
import vo.BoardVO;
import vo.CurriculumVO;
import vo.MemberVO;

public class MemberTest {
	public static void main(String[] args) {
//		MemberVO m1 = new MemberVO();
//		
		ProjectService pjs = new ProjectService();
//		
		
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		BoardVO sangdam = new BoardVO();
		sangdam.setBoardID("CB");
		List<BoardVO> list = pjs.selectAllBoard(sangdam);
		System.out.println(list);

//		List<MemberVO> list = pjs.selectAllMember();
//		
//		MemberVO waitPermission = new MemberVO();
//		waitPermission.setActive("N");
//		List<MemberVO> memList = pjs.selectAllMember(waitPermission);
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("아이디\t\t이름\t성별\t  휴대폰 번호\t생년월일\t  계정 활성화 정보");
//		System.out.println("--------------------------------------------------------------------------------");
//		for (MemberVO v : memList) {
//			System.out.println(v.getMemberID()+"\t"+v.getName()+"\t "+v.getGender()+"\t"+v.getPhoneNum()+"\t"+v.getBirth()+"\t\t"+v.isActive());
//			
//		}
//		System.out.println("--------------------------------------------------------------------------------");
		
		
		
//		
//		CurriculumVO myCurriculum = pjs.selectOneCurriculum("AI01 ");
//		
//		String endDate = myCurriculum.getEndDate();
//
//		String todayFm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())); // 오늘날짜
//
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//		Date date;
//		try {
//			date = new Date(dateFormat.parse(endDate).getTime());
//			Date today = new Date(dateFormat.parse(todayFm).getTime());
//			long calculate = date.getTime() - today.getTime();
//
//			int Ddays = (int) (calculate / (24 * 60 * 60 * 1000));
//			System.out.println("--------------------------------------------------------------------------------");
//			System.out.println("과정명 : " + myCurriculum.getCurriculumName());
//			System.out.println("시작날짜 : "+ myCurriculum.getStartDate().substring(0,10));
//			System.out.println("수료날짜 : "+ myCurriculum.getEndDate().substring(0,10));
//			System.out.println("수료까지 남은 날짜 : " + Ddays + "일");
//	
//		
//		} catch (ParseException e) {
//			System.out.println("d-day계산 중 에러 발생");
//		}
//
//		
//		
		
//		MemberVO m4 = new MemberVO();
//		
//		
//		
//		MemberVO m5 = new MemberVO();
//		
//		
//		MemberVO m6 = new MemberVO();
	
	}
}
