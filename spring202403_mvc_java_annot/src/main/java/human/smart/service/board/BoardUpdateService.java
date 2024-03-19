package human.smart.service.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import human.smart.com.dao.BoardDAO;
import human.smart.com.vo.BoardVO;
import lombok.AllArgsConstructor;

@Service("bUpdate")
@AllArgsConstructor//모든 인스턴스 필드를 파라미터로 받는 생성자를 정의해서 의존 자동주입해줌
public class BoardUpdateService implements BoardService {
	private BoardDAO dao;
	
	@Override
	public int update(BoardVO vo, HttpServletRequest request) {
		MultipartFile uploadFile = vo.getUploadFile();
		
		if(uploadFile.getSize() != 0) {//파일 업로드가 된 경우(새로운 파일을 선택한 경우)
			
			//1. 저장 디렉터리에 저장할 새로운 파일명 만들기
			String originFileName = uploadFile.getOriginalFilename(); //원본 파일 이름
			String ext = originFileName.substring(originFileName.lastIndexOf("."));//파일 확장자를 추출함
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String saveFileName = now+ext;//새로운 파일이름: 업로드 일시.확장자
			
			//2. 지정된 경로에 파일 저장하기
			String saveDirectory = request.getServletContext().getRealPath("resources/uploads/");
			String fullPath = saveDirectory + saveFileName;
			try {
				uploadFile.transferTo(new File(fullPath));//지정된 경로에 파일 저장
			}catch(IllegalStateException | IOException e) {
				System.out.println("파일 저장 중 예외 발생");
				e.printStackTrace();
			}
			
			//3. 파일 관련 값들을 BoardVo에 저장하기
			vo.setOriginfile_name(originFileName);
			vo.setSavefile_name(saveFileName);
		}
			
		return dao.update(vo);
	}
	

}
