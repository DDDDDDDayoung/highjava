package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;



// DAO-> Data Access Object
/**
 * 실제 DB와 연결해서 SQL문을 수행한 후 결과를 작성하여 서비스에
 * 전달하는 서비스의 Interface
 * @author PC-18
 *
 */
public interface IMemberService {
	
	/**
	 * 회원정보 등록을 위한 메서드
	 * @param mv 등록할 데이터가 담겨진 MemberVO객체
	 * @return 회원등록이 성공하면 1 이상의 값이 반환되고, 실패하면 0이 반횐됨.
	 */
	public int registMember(MemberVO mv);
	
	/**
	 * 회원정보 수정 위한 메서드
	 * @param mv 변경할 데이터가 담겨진 MemberVO객체
	 * @return 변경이 성공하면 1이 반환되고, 실패하면 0이 반횐됨.
	 */
	public int modifyMember(MemberVO mv);
	
	/**
	 * 해당 ID에 해당하는 회원정보를 삭제하기 위한 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 삭제 성공하면 1, 실패하면 0 반환됨.
	 */
	public int removeMember(String memId);
	
	/**
	 * 해당 ID에 해당하는 회원정보가 존재하는지 확인하기 위한 메서드
	 * @param memId 체크할 회원id
	 * @return 해당 회원이 존재하면 true, 존재하지 않으면 false 리턴함.
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 전체 회원정보를 가져오기 위한 메서드
	 * @return 전체 회원정보를 담은 리스트
	 */
	public List<MemberVO> selectAll();
	
}
