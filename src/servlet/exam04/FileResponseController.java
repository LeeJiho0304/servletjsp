package servlet.exam04;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="exam04.FileResponseController", urlPatterns="/exam04/FileResponseController")
public class FileResponseController extends HttpServlet {
	
	//클라이언트가 요청할때마다 실행
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = "사진1.jgp";
		String filePath = "C:/Temp/photo1.jpg";
		String contentType = "image/jpeg";
		
		//HTTP 응답에 Content-Type 헤더를 추가
		response.setContentType(contentType);
		//response.setHeader("Content-Type", contentType);   위에 내용과 동일
		
		//Browser의 종류 얻기
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			//IE일 경우 (Trident : Internet Expoloer11 버전, MSIE: Internet Expoloer 10버전 이하)
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			//Chrome, Edge, FireFox, Safari
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		System.out.println(fileName);
		
		
		//HTTP 응답에 Content-Disposition 헤더를 추가
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		//HTTP 응답을 본문에 파일 데이터 출력하기
		ServletOutputStream sos = response.getOutputStream();
		
		//방법1
		/*FileInputStream fis = new FileInputStream(filePath);
		byte[] data = new byte[1024];
		while(true) {
			int readByteNum = fis.read(data);
			if(readByteNum == -1) break;
			sos.write(data, 0, readByteNum);
		}
		sos.flush();
		fis.close();
		sos.close();*/
		
		//방법2
		Path path = Paths.get(filePath);
		Files.copy(path, sos);
		sos.flush();
		sos.close();
	}
}
