package ex07_binding;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Binding1
 */
@WebServlet("/binding1")
public class Binding1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Binding1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  /*
	   * 한번전달 request, 두고두고 쓰는것은 어디..?
	   * 웹은 stayless?? 
	   * 지금까지 배운 것 서버측 저장소, 클라이언트 저장소는 쿠기?? 다음시간...
	   * 
	   * binding
	   * 
	   * 1. 값을 저장할 수 있는 영역에 속성(Attribute)의 형태로 값을 저장하는 것을 의미한다. (속성을 변수라고 생각해도 됨)
	   * 2. HTML 태그의 속성(Attribute)과 상관 없는 개념이다.
	   * 3. 서버가 값을 저장할 영역을 제공한다.
	   * 4. 저장 영역
	   *   1) ServletContext : 컨텍스트(프로젝트, 애플리케이션) 종료 전까지 값을 저장할 수 있다.
	   *              애플리케이션이 실행중이라면 어디서든 값에 접근할 수 있다.(가져오거나, 바꾸거나 수정가능)
	   *              ex) 방문자 수 
	   *   2) ★HttpSession    : 세션 종료(브라우저 종료) 전까지 값을 저장할 수 있다. 웹 브라우저가 실행중이라면 어디서든 값에 접근할 수 있다. (시간 지정 가능) 
	   *                       웹브라우저 닫으면 정보가 소멸된다.
	   *                       ( ex) 은행 10분 동안 로그인 -> 세션(작업단위)으로 관리 / 로그인 하고 창 꺼버리기
	   *   3) ★HttpServletRequest : 요청 종료 전까지 값을 저장할 수 있다. 응답 전이라면 값에 접근할 수 있다.(1회용 정보)
	   *   
	   * 5. 속성(Attribute) 처리 메소드
	   *   1) setAttribute("속성", 값) : Object 타입으로 값을 저장한다.
	   *                   // String, int, long, double, boolean 다 가능 -> Object 최상위 클래스로 값 저장
	   *   2) getAttribute("속성")  : Object 타입의 값을 반환한다. 캐스팅(형변환)이 필요할 수 있다.
	   *   3) removeAttribute("속성") : 값을 제거한다.
	   */
	  
	  // ServletContext 영역에 값을 저장하기
	  ServletContext application = request.getServletContext();
	  application.setAttribute("msg", "ServletContext");
	  
	  // 1) HttpSession 영역에 값을 저장하기
	  HttpSession session = request.getSession();
	  session.setAttribute("msg", "HttpSession");
	  
	  // 2) HttpServletRequest 영역에 값을 저장하기 (이미 request 매개변수에 선언되어있다.)
	  request.setAttribute("msg", "HttpServletRequest");
	  
	  // 3) Binding2 서블릿으로 이동 (HttpServletRequest의 전달이 가능한 forward - request를 이용)
	  // 2가지 방법있음 - forward, redirect
	  // HttpServletRequest 유지시키면서 이동 forward, redirect 유지 안됨..
	  // 하기가 forward 코드임!! /servlet 코드는 적지 않음(내선 전화 앞 번호 누루지 않는 것과 같음)
	  request.getRequestDispatcher("/binding2").forward(request, response);
	  
	  
	  
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
