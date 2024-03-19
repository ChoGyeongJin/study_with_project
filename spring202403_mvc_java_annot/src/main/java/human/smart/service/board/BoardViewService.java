package human.smart.service.board;

import org.springframework.stereotype.Service;

import human.smart.com.dao.BoardDAO;
import human.smart.com.vo.BoardVO;
import lombok.AllArgsConstructor;

@Service("bView")
@AllArgsConstructor//모든 인스턴스 필드를 파라미터로 받는 생성자를 정의해서 의존 자동주입해줌
public class BoardViewService implements BoardService {
	private BoardDAO dao;
	
	@Override
	public BoardVO getBoard(int board_idx) {
		return dao.getBoard(board_idx);
	}
	
}
