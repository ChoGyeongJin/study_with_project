package human.smart.com.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import human.smart.com.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor//final이 붙은 인스턴스 필드에 대한 생성자를 생성하여 의존 자동주입함
public class MemberDAO {
	
	/* MyBatis를 사용할 때 각각의 Mapper 내에 있는 SQL문을 Mapper의 네임스페이스와 
	   SQL문의 id값으로 구분함 => 각 Mapper 파일의 네임스페이스를 상수로 정의해서
	   SqlSession 메소드 호출시 사용함
	 */
	public static final String MAPPER = "human.smart.com.MemberMapper";
	
	//MyBatis를 이용한 DB 연결 및 작업을 담당할 SqlSession 객체를 필드로 정의함
	private final SqlSession sqlSession;
	
	//회원정보 입력하기: join(MemberDTO dto): int
	public int join(MemberVO vo) throws SQLException{
		return sqlSession.insert(MAPPER+".join", vo);
	}
	
	//모든 회원정보 가져오기:getMembers():ArrayList<MemberDTO>
	public List<MemberVO> getMembers() throws SQLException{
		return sqlSession.selectList(MAPPER+".getMembers");
	}

	//회원정보 수정하기
	public MemberVO updateMember(MemberVO vo) throws SQLException{
		MemberVO newVO = null;		
		if(sqlSession.update(MAPPER+".updateMember", vo) == 1) {//회원정보 업데이트 성공
			newVO = getMember(vo.getM_idx());
		}
		return newVO;
	}
	
	//로그인 처리하기
	public MemberVO login(String member_id) throws SQLException{
		return sqlSession.selectOne(MAPPER+".login", member_id);
	}
	
	//비밀번호 가져오기
	public String getPassword(String member_id) {
		return sqlSession.selectOne(MAPPER+".getPassword", member_id);
	}

	//한명의 회원정보 가져오기
	public MemberVO getMember(int m_idx) throws SQLException{
		return sqlSession.selectOne(MAPPER+".getMember", m_idx);
	}
	
	//회원 탈퇴하기
	public int cancel(int m_idx) throws SQLException {
		return sqlSession.update(MAPPER+".cancel", m_idx);
	}


	

}
