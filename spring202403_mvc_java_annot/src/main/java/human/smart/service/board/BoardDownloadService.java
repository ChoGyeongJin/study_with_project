package human.smart.service.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service("bDownload")
public class BoardDownloadService implements BoardService {
	
	@Override
	public void download(String originfile_name, String savefile_name, 
			HttpServletRequest request, HttpServletResponse response) {
		
		try{
			String saveDirectory = request.getServletContext().getRealPath("resources/uploads/");
			//파일을 찾아 입력 스트림 생성	
			File file = new File(saveDirectory, savefile_name);
			InputStream inStream = new FileInputStream(file);
			
			//한글 파일명 깨짐 방지 처리
			String client = request.getHeader("User-Agent");//User-Agent:사용자가 사용하는 웹 브라우저
			if(client.indexOf("WOW64")== -1){//WOW64: Windows on Windows 64-bit, 64비트 버전의 Windows에서 
				//32비트 응용프로그램을 실행할 수 있다는 의미로 인터넷 익스플로러(IE)의 실행결과에서 출력됨
				
				originfile_name = new String(originfile_name.getBytes("UTF-8"), "ISO-8859-1");
				//HTTP의 정보는 ASCII로 인코딩되므로 여기에 문자셋을 맞추기 위해서 먼저 파일 이름을 UTF-8로 된 배열 생성후
				//ISO-8859-1문자셋으로 문자열 객체를 생성함
			}else{
				
				originfile_name = new String(originfile_name.getBytes("KSC5601"), "ISO-8859-1");
			}
			
			//파일 다운로드용 응답헤더 설정
			response.reset();
			response.setContentType("application/octet-stream");//파일 다운로드를 위한 MIME타입 설정
			response.setHeader("Content-Disposition", "attachment; filename=\""+originfile_name+"\"");
			//위 MIME타입과 함께 설정되는 컨텐츠 처리 헤더 설정
			response.setHeader("Content-Length",""+file.length() );
			
			//response 내장 객체로부터 새로운 출력 스트림 생성
			OutputStream outStream = response.getOutputStream();
			
			//출력 스트림에 파일 내용 출력하기
			//파일 크기의 바이트 배열을 생성해서 버퍼로 사용
			byte [] buffer = new byte[(int)file.length()];
			int readBuffer = 0;
			while( (readBuffer = inStream.read(buffer)) > 0){
				outStream.write(buffer, 0, readBuffer);
			}
			
			//입출력 스트림 닫기
			inStream.close();
			outStream.close();
			
		}catch(Exception e){
			System.out.println("다운로드 중 예외 발생");
			e.printStackTrace();
		}
	}
	

}
