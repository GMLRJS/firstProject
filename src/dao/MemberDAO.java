package dao;

import java.util.List;

import mybatisDAO.MyBatisDAO;
import vo.MemberVO;

public class MemberDAO extends MyBatisDAO{
	
	private static MemberDAO memberDAO;
	
	private MemberDAO() {}
	
	
	public static MemberDAO getInstance() {
		
		if(memberDAO == null) {
			memberDAO = new MemberDAO();
		} return memberDAO;
	}
	
	/**
	 * 계정이 있는지 없는지 체크해주는 메소드
	 * 
	 * @param id 계정의 ID를 매개변수로 넘겨받는다.
	 * @return 계정이 있으면 false, 없으면 true값이 리턴된다.
	 */
	public int check(String id) {
		
		int cnt = selectOne("member.checkMember", id);
		
		return cnt;
	}
	
	/**
	 *  자기자신의 정보를 조회할때 사용할 메소드 
	 *  회원가입시 입력했던 정보가 나온다. 
	 *  (마이페이지에서 사용예정)
	 * @param id 파라미터로 memID를 받는다.
	 * @return 회원정보가 담긴 membervo 객체를 리턴해준다.
	 */
	public MemberVO showMyData(String id) {
		
		MemberVO myVO = selectOne("member.getMember", id);
		
		return myVO;
	}
	
	/**
	 * 회원가입 메소드
	 * 
	 * @param vo  파라미터로 계정 정보가 담긴 MemberVO객체를 받는다.
	 * @return  계정이 성공적으로 가입되면 true 실패하면 false 값이 리턴된다.
	 */
	public int signUp(MemberVO vo) {
		
		int cnt = update("member.insertMember", vo);
		
		return cnt;
	}
	public int modify(MemberVO vo) {
		
		int cnt = update("member.updateMember", vo);
		
		return cnt;
	}
	
	/**
	 * 비밀번호를 수정하는 메소드
	 * @param vo 계정정보가 담긴 MemberVO객체를 파라미터로 받는다.
	 * @return 비밀번호가 성공적으로 초기화가 되면 true 실패하면 false 값이 리턴된다.
	 */
	public int modifyPassword(MemberVO vo) {
		
		int cnt = update("member.passwordUpdateMember",vo);
		
		return cnt;
		
	}
	
	/**
	 * 전화번호 정보를 수정하는 메소드
	 * @param vo
	 * @return
	 */
	public int modifyPhoneNum(MemberVO vo) {
		
		int cnt = update("member.phoneNumUpdateMember",vo);
		
		return cnt;
	}
	
	/**
	 * 계정의 활성화여부를 바꾸는 메소드
	 * 회원가입시 자동으로 0인값의 칼럼을 선생님 수락시 1로 변경해주고
	 * 탈퇴 처리시 0으로 변경해준다. 
	 * @param vo 계정의 활성화 여부를 변경할 계정의 정보를 파라미터로 받는다. (계정 ID)
	 * @return 계정의 isAlive칼럼 정보가 잘 변경되었다면 true 반환, 그렇지 않으면 false환
	 */
	public int accountStatement(MemberVO vo) {
		
		int cnt = update("member.activeUpdateMember",vo);
		
		return cnt;
	}
	/**
	 *  전체 가입자 수를 알 수 있는 메소드 (계정이 활성화되어있는 계정만 체크)
	 * @return 전체 가입자 수(cnt)
	 */
	public int countAllMember() {
		
		int cnt = selectOne("member.countMember");
		
		return cnt;
		
		
	}
	
	/**
	 *  특정 조건의 해당되는 가입자의 수를 알 수 있는 메소드
	 *   ex) 남자인 가입자의 수, 특정 과정에 등록되어있는 가입자 수
	 * @param vo  특정 조건의 정보가 담긴 MemberVO 객체를 파라미터로 받는다.
	 * @return 특정 조건의 해당되는 가입자의 수
	 */
	public int countMember(MemberVO vo) {
		
		int cnt = selectOne("member.countMember",vo);
		
		return cnt;
	}
	
	/**
	 *  모든 가입자와 정보를 검색해주는 메소드
	 *  
	 * @return membervo타입의 객체가 담긴 변수
	 */
	public List<MemberVO> selectAllMember(){
		
		List<MemberVO> list = selectList("member.selectAllMember");
		
		return list;
	}
	
	/**
	 *  특정 조건에 해당하는 가입자와 정보를 검색해주는 메소드
	 * @param vo 조건이 담긴 membervo 객체를 파라미터로 받는다.
	 * @return 특정 조건을 만족하는 가입자의 list
	 */
	
	public List<MemberVO> selectAllMember(MemberVO vo){
		
		List<MemberVO> list = selectList("member.selectAllMember",vo);
		
		return list;
	}
	
	
}
