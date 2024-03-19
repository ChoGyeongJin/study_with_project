package human.smart.service.board;

import org.springframework.stereotype.Service;

import human.smart.com.dao.BoardDAO;
import lombok.AllArgsConstructor;

@Service("bUpdateCount")
@AllArgsConstructor//모든 인스턴스 필드를 파라미터로 받는 생성자를 정의해서 의존 자동주입해줌
public class BoardUpdateCountService implements BoardService {
	private BoardDAO dao;
	
	@Override
	public void updateReadCount(int b_idx) {
		dao.updateRead_cnt(b_idx);
	}
	

}
