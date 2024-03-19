package human.smart.com.vo;

import lombok.Data;

@Data
public class SearchVO {
	
	private String searchField;
	private String searchWord;
	private int pageNum;//효율적인 게시글 목록 조회를 위해 추가해줌(BoardMapper.xml에서 사용)
	private int pageBlock;//효율적인 게시글 목록 조회를 위해 추가해줌
	//BoardController의 list()메소드의 매개변수를 변경해줌
	private int startIdx; //BoardMapper.xml에서 게시물 목록을 가져올 때  limit함수의 매개값으로 사용
	//BoardListService의 getBoards()메소드에서 startIdx값을 (pageNum-1)*10으로 세팅함

}
