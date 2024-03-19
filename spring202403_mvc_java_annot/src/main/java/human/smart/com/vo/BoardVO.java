package human.smart.com.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardVO {
	private int b_idx; //게시글 번호
	private int m_idx; //회원 번호
	private String writer; //작성자
	private String title; //제목
	private String content; //내용
	private String originfile_name; //원본 파일명
	private String savefile_name; //저장 파일명
	private int read_cnt; //조회수
	private Date post_date; //작성일
	private Date update_date; //수정일
	private int del_or_not; //삭제 여부
	
	//[중요]게시글 등록 폼에서 입력된 값을 커맨드 객체로 받기 위해서 
	//첨부파일을 MultipartFile 타입의 필드로 정의해야 함
	private MultipartFile uploadFile;//첨부 파일

}
