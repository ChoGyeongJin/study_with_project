package human.smart.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import human.smart.com.dao.BoardDAO;
import human.smart.com.vo.SearchVO;
import lombok.AllArgsConstructor;

@Service("bTotalCount")
@AllArgsConstructor//모든 인스턴스 필드를 파라미터로 받는 생성자를 정의해서 의존 자동주입해줌
public class BoardTotalCountService implements BoardService {
	private BoardDAO dao;
	
	@Override
	public int getTotalCount(SearchVO vo) {
		return dao.getTotalCount(vo);
	}

}
