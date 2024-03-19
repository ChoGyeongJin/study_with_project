package human.smart.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import human.smart.com.dao.BoardDAO;
import human.smart.com.vo.BoardVO;
import human.smart.com.vo.SearchVO;
import lombok.AllArgsConstructor;

@Service("bList")
@AllArgsConstructor//모든 인스턴스 필드를 파라미터로 받는 생성자를 정의해서 의존 자동주입해줌
public class BoardListService implements BoardService {
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> getBoards(SearchVO searchVO){
		//BoardMapper.xml에서 게시물 목록을 가져올 때 limit함수의 시작인덱스값으로 사용하기 위해
		//SearchVo클래스의 startIdx값을 (pageNum-1)*10으로 세팅함
		searchVO.setStartIdx((searchVO.getPageNum()-1)*10);
		return dao.getBoards(searchVO);
	}
	

}
