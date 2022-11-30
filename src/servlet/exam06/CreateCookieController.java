package servlet.exam06;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="exam06.CreateCookieController", urlPatterns="/exam06/CreateCookieController")
public class CreateCookieController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿠키 생성
		Cookie cookie1 = new Cookie("useremail", "winter@mycompany.com");
		//cookie1.setDomain("localhost");  //해당 도메인으로 갈 때만 Cookie를 전달
		//cookie1.setPath("/"); // "/" : 앞에 localhost 만 맞으면 쿠키를 모두 보내겠다   //해당 경로로 요청할 때만 Cookie를 전달
		//cookie1.setMaxAge(60);  //유지 기간을 설정(파일 시스템에 저장)
		//cookie1.setHttpOnly(true);  //JavaScript 에서 쿠키를 읽지 못하도록 설정
		//cookie1.setSecure(true);  //https 에서만 Cookie를 전달 
		
		Cookie cookie2 = new Cookie("userid", "winter");
		
		
		//쿠키를 응답 헤더에 추가하기
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		response.sendRedirect("ContentController");
	}
}
