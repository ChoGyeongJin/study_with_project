package human.smart.service.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import human.smart.com.mapper.MemberMapper;
import human.smart.com.vo.MemberVO;
import lombok.AllArgsConstructor;

@Service("mLogin")
@AllArgsConstructor
public class MemberLoginService implements MemberService {
	private MemberMapper memberMapper;
	private BCryptPasswordEncoder cryptPasswordEncoder;
	
	@Override
	public MemberVO login(String member_id, String member_pw) {
		MemberVO memberVO = null;//회원가입 실패시 결과값
			
		//아이디를 이용해서 DB에 저장된 암호화된 비밀번호를 가져와서
		//로그인으로 입력된 비밀번호와 비교함
		String encodePassword = memberMapper.getPassword(member_id);
		if(encodePassword != null) {//아이디가 유효한 경우
			
			if(cryptPasswordEncoder.matches(member_pw, encodePassword)) {
			//암호화된 비밀번호와 로그인으로 입력된 비밀번호가 일치하는 경우
			//matches(원 비빌번호, 암호화된 비밀번호)	
				memberVO = memberMapper.login(member_id);
			}
		}
		
		return memberVO;
	}

}
