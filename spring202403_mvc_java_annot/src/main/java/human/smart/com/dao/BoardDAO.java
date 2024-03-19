package human.smart.com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import human.smart.com.vo.BoardVO;
import human.smart.com.vo.SearchVO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor//final이 붙은 인스턴스 필드에 대한 생성자를 생성하여 의존 자동주입함
public class BoardDAO {
	
	//MyBatis를 사용할 때 각각의 Mapper 내에 있는 SQL구문을 Mapper의
	//네임스페이스와 SQL구문의 id값으로 구분하므로 각 Mapper파일의 네임스페이스를 
	//상수로 정의해서 SqlSession메소드 호출시 사용함
	public static final String MAPPER = "human.smart.com.BoardMapper";
	
	//MyBatis를 이용한 DB연결 및 작업은 SqlSession객체가 담당함
	private final SqlSession sqlSession;
	
	//게시글 입력
	public int insert(BoardVO vo) {
		return sqlSession.insert(MAPPER+".insert", vo);
	}
	
	//모든 게시글 조회
	public List<BoardVO> getBoards(SearchVO sVo){
		return sqlSession.selectList(MAPPER+".getBoards", sVo);
	}
	
	//조회수 업데이트
	public void updateRead_cnt(int b_idx) {
		sqlSession.update(MAPPER+".updateRead_cnt", b_idx);
	}
	
	//하나의 게시글 조회
	public BoardVO getBoard(int b_idx){
		return sqlSession.selectOne(MAPPER+".getBoard", b_idx);
	}
	
	//게시글 수정하기
	public int update(BoardVO vo) {
		return sqlSession.update(MAPPER+".update", vo);
	}
	
	//게시글 삭제 처리하기
	public int delete(int b_idx) {
		return sqlSession.update(MAPPER+".delete", b_idx);
	}

	//총 게시글 수 조회
	public int getTotalCount(SearchVO vo) {
		return sqlSession.selectOne(MAPPER+".getTotalCount", vo);
	}
}
