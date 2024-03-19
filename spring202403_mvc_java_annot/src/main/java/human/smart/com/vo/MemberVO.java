package human.smart.com.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {//데이터를 저장해서 전달하는 역할을 주로하는 클래스: DTO(Data Transfer Object)
	private int m_idx;
	private String member_id;
	private String member_pw;
	private String member_name;
	private String handphone;
	private String email;
	private int grade;
	private Date join_date;
	private Date update_date;
	private Date cancel_date;
	private int cancel_or_not;
		
}
