package servlet.exam05;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50)
@WebServlet(name="exam05.FileUploadController", urlPatterns="/exam05/FileUploadController")
public class FileUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 복원을 위해서 문자셋을 지정
		request.setCharacterEncoding("UTF-8");
		
		//문자 파트의 정보 얻기
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		System.out.println("title: " + title);
		System.out.println("desc: " + desc);

		//한 개의 파일 파트의 정보 얻기
		/*Part attachPart = request.getPart("attach");
		//파일이 실제로 전송되었는지 확인
		if(!attachPart.getSubmittedFileName().equals("") && attachPart.getSize() !=0) {
			String filename = attachPart.getSubmittedFileName();
			long fileSize = attachPart.getSize();
			String contentType = attachPart.getContentType();
			
			System.out.println("fileName: " + filename);
			System.out.println("fileSize: " + fileSize);
			System.out.println("contentType: " + contentType);
		}*/

		//두 개 이상의 파일 파트의 정보 얻기
		Collection<Part> parts = request.getParts();  //문자 파트도 포함되어 있음
		System.out.println(parts.size());  //파트의 총 갯수 (문자파트 2개 + 파일파트3개)
		for(Part part : parts) {
			//파일 파트인지 확인
			//파일이 실제로 전송되었는지 확인
			if(part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) { 
				//파일 정보 얻기
				String filename = part.getSubmittedFileName();
				long fileSize = part.getSize();
				String contentType = part.getContentType();
				
				System.out.println("fileName: " + filename);
				System.out.println("fileSize: " + fileSize);
				System.out.println("contentType: " + contentType);
				System.out.println();
				
				//파일을 파일 시스템에 저장
				String savedName = new Date().getTime() + "-" + filename;
				String filePath = "C:/Temp/download/" + savedName;  //실제 저장되는 경로
				part.write(filePath);
			}
			
		}
		response.sendRedirect("RequestParamInfoController");
	}
}
