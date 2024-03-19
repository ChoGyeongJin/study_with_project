package human.smart.service.member;

import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import human.smart.com.mapper.MemberMapper;
import human.smart.com.vo.MemberVO;
import lombok.AllArgsConstructor;

@Service("mJoin")
@AllArgsConstructor
public class MemberJoinService implements MemberService {
	private MemberMapper memberMapper;
	private BCryptPasswordEncoder cryptPasswordEncoder;
	
	public int join(MemberVO memberVO) {
		int result = 0;//회원가입 실패시 결과값
		
		//회원가입 정보 중 비밀번호 가져오기
		String member_pw = memberVO.getMember_pw();
		//비밀번호를 암호화시켜서 결과 확인하기
		String encodePassword = cryptPasswordEncoder.encode(member_pw);
		System.out.println("암호화된 비밀번호: "+encodePassword);
		System.out.println("암호화된 비밀번호 길이: "+encodePassword.length());
		
		//원 비밀번호와 암호화된 비밀번호가 일치하는지 여부 확인하기
		System.out.println("원 비밀번호와 암호화된 비밀번호 일치 여부: "+cryptPasswordEncoder.matches(member_pw, encodePassword));
		
		//암호화된 비밀번호를 memberVO에 세팅하기
		memberVO.setMember_pw(encodePassword);
		
		result=memberMapper.join(memberVO);
		
		return result;
	}

}
