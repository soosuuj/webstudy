package ex08_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie1
 */
@WebServlet("/cookie1")
public class Cookie1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookie1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  /*
	   * 쿠기
	   * 1. 클라이언트측에 저정되는 정보를 의미한다.
	   * 2. 보안에는 취약하므로 개인 정보 같은 민감 정보는 저장하지 않는다.
	   */
	  
	  // 쿠기 만들기, 모든 쿠키 값은 String 타입으로 저장한다.
	  // 공백   - URLEncoder.encode
	  // name, age, address 이게 쿠키 이름
	  Cookie cookie1 = new Cookie("name", "홍길동");
	  Cookie cookie2 = new Cookie("age", "20");
	  Cookie cookie3 = new Cookie("address", URLEncoder.encode("서울특별시 종로구", "UTF-8"));
    
	  // 쿠키가 저장될 경로 설정하기 (생략하면 컨텍스트패스 경로에 저장된다.)
	  // -> 특정 주소 조건이 만족해야 쿠키를 사용할 수 있다.
	  cookie1.setPath("/servlet");         // 컨텍스트패스 로 저장 : request.getContextPath()
	  cookie2.setPath("/servlet/cookie1"); //서블릿경로에 저장 request.getRequestURI()
	                                       // cookie3은 경로 설정을 생략했으므로 컨텍스트 패스에 저장된다.
	  
	  
	  // 쿠키 삭제는 유지되는 시간을 0으로 설정하고 다시 저장하면 된다!!! -> 삭제라는 개념이 없음
	  // 쿠키 정보가 유지되는 시간 설정(생략하면 세션 쿠키가 된다. 브라우저를 닫으면 지워진다.) 
	  cookie1.setMaxAge(60 * 60);           // 1시간 60초 * 60분
	  cookie2.setMaxAge(60 * 60 * 24 * 7);  // 7일
	                                        // cookie3은 시간 설정을 생략했으므로 세션 쿠기가 된다.
	  
	  // 쿠기를 브라우저에 저장하기(응답으로 처리한다.)
	  response.addCookie(cookie1);
	  response.addCookie(cookie2);
	  response.addCookie(cookie3);
	  
	  
	  // Cookie2 서블릿으로 redirect 이동
	  response.sendRedirect("/servlet/cookie2");
	  
	  
	  
	  
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
