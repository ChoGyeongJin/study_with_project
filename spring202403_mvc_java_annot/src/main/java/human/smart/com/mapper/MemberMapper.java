package human.smart.com.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import human.smart.com.vo.MemberVO;

/* Mapper 인터페이스 정의 방법
- XML Mapper의 경우 Mapper를 구분하기 위한 용도로 namespace가 있었는데
  Mapper 인터페이스에서는 패키지로 인터페이스명으로 구분되기 때문에 네임스페이스가 없음
- DAO가 필요없어짐, Service클래스에서 직접 Mapper 인터페이스를 이용해서 DB관련 작업을 함
- Mapper 내의 메소드는 모두 추상메소드임(public abstract 생략됨, 내용이 없음)

- XML Mapper에서 사용하던 CRUD 태그들을 어노테이션을 이용해서 정의함
  (@Insert, @Select, @Update, @Delete)

- XML Mapper에서 정의된 SQL문은 어노테이션의 값(value)으로 정의함

- XML Mapper    vs.  자바 인터페이스 Mapper
  SQL구문 태그			 어노테이션
  아이디				 메소드 이름
  parameterType		 매개변수 타입
  resultType		 반환형
  SQL구문				 어노테이션의 값(value)

- MyBatis는 자바 인터페이스 Mapper와 XML Mapper를 동시에 사용할 수 있도록 지원함
  * 복잡한 SQL문은 XML Mapper를 사용함
  * XML Mapper를 사용할 경우 resources 폴더 아래에 자바 패키지와 같은 mapper폴더를 생성하고
    인터페이스 Mapper와 동일한 이름의 XML Mapper 파일을 생성함
  * 자바 인터페이스 Mapper에서는 어노테이션을 붙이지 않고 추상 메소드만 정의하고 SQL구문은 XML Mapper에 정의함  

 */

public interface MemberMapper {
	
	//회원정보 입력구문
	@Insert("insert into tbl_member (member_id, member_pw, member_name, handphone, email)\r\n"
			+ "	      	   values (#{member_id},#{member_pw},#{member_name},#{handphone},#{email})")	
	int	join(MemberVO memberVO);
	
	//로그인
	@Select("select * from tbl_member where member_id = #{member_id}")
	MemberVO login(String member_id);
	
	//비밀번호 가져오기
	@Select("select member_pw from tbl_member where member_id = #{member_id} and cancel_or_not=0")
	String getPassword(String member_id);
	
	//회원정보 가져오는 구문
	@Select("select * from tbl_member where m_idx = #{m_idx}")
	MemberVO getMember(int m_idx);
	
	//회원정보 수정구문
	@Update("update tbl_member set member_pw=#{member_pw}, member_name=#{member_name}, handphone=#{handphone},\r\n"
			+ "					         email=#{email}, last_modified_date=now() where m_idx=#{m_idx}")
	int updateMember(MemberVO memberVO);

	//회원탈퇴 처리구문 
	@Update("update tbl_member set last_modified_date=now(), cancel_or_not=-1 where m_idx=#{m_idx}")
	int cancel(int m_idx);

}
