package human.smart.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import human.smart.com.common.PageNav;
import human.smart.com.vo.BoardVO;
import human.smart.com.vo.SearchVO;

public interface BoardService {
	default List<BoardVO> getBoards(SearchVO searchVO){return null;}
	
	default PageNav setPageNav(PageNav pageNav, int pageNum, int pageBlock) {return null;}
	
	default int insert(BoardVO vo, HttpServletRequest request) {return 0;}
	
	default void updateReadCount(int b_idx) {}
	
	default BoardVO getBoard(int b_idx) {return null;}
	
	default int update(BoardVO vo, HttpServletRequest request) {return 0;}
	
	default void download(String originFileName, String saveFileName, 
			HttpServletRequest request, HttpServletResponse response) {}
	
	default int delete(int b_idx, HttpServletRequest request) {return 0;}

	default int getTotalCount(SearchVO vo) {return 0;}
}
