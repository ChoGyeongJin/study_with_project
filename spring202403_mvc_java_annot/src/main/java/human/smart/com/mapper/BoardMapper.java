package human.smart.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import human.smart.com.vo.BoardVO;
import human.smart.com.vo.SearchVO;

public interface BoardMapper {

	//게시글 입력
	//복잡한 SQL구문은 BoardMapper.xml파일에 구현함
	int insert(BoardVO boardVO);
	
	//모든 게시글 조회
	//복잡한 SQL구문은 BoardMapper.xml파일에 구현함
	List<BoardVO> getBoards(SearchVO searchVO);
	
	//총게시물수 조회
	//복잡한 SQL구문은 BoardMapper.xml파일에 구현함
	int getTotalCount(SearchVO searchVO);
	
	//조회수 업데이트
	@Update("update tbl_board set read_cnt = (read_cnt + 1) where b_idx = #{b_idx}") 
	int updateRead_cnt(int b_idx);
	
	//하나의 게시글 조회
  	@Select("select * from tbl_board where b_idx = #{b_idx}") 
  	BoardVO getBoard(int b_idx);
  		
  	//게시글 수정하기
    //복잡한 SQL구문은 BoardMapper.xml파일에 구현함
  	int update(BoardVO boardVO);
	
  	//게시글 삭제 처리하기
  	@Update("update tbl_board set del_or_not=-1, update_date=now() \r\n"
  			+ "				where b_idx=#{b_idx}") 
  	int delete(int b_idx);

}
