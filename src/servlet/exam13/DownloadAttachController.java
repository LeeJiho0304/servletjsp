package servlet.exam13;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.BoardService;
import util.Pager;

@WebServlet(name="exam13.DownloadAttachController", urlPatterns="/exam13/DownloadAttachController")
public class DownloadAttachController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardService boardService = (BoardService) request.getServletContext().getAttribute("boardService");
		Board board = boardService.getBoard(bno);
		
		String fileName = board.getBfileName();
		String filePath = "C:/Temp/download/" + board.getBsavedName();
		String contentType = board.getBfileType();
		
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
		
		Path path = Paths.get(filePath);
		Files.copy(path, sos);
		sos.flush();
		sos.close();
	}
}
