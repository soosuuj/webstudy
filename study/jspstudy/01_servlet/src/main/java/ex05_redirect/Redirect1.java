  package ex05_redirect;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Redirect1
 */
@WebServlet("/redirect1")
public class Redirect1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Redirect1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // -> request 에서 alice를 받음 
	  
	  /*
	   * redirect
	   * 1. 다른 경로로 이동하는 방식 중 하나이다.
     * 2. 서버가 다른 경로를 응답하면 클라이언트가 해당 경로로 직접 이동하는 방식이다.
     *    (서버가 응답을해주면 다시 이동한다는게 포인트, 클라이언트가 서버로 2번 찾아감)
     * 3. 경로를 작성할 때 ContextPath 와 URLMapping을 모두 작성한다. 
	   */
	  
	  // 요청 파라미터 
	  // ★요청 파라미터 한글인 경우 안됨! request로 앨리스 받았으니까 uft-8로 인코딩하려는 시도.. 
	  // request.setCharacterEncoding("UTF-8");
	  String name = request.getParameter("name");
	  
	  // redirect할 경로를 응답함 (이리로 다시 가라), 
	  // 요청 파라미터 코드를 추가하고 +로 name 을 붙여준다. -> redirect2번으로 alice 넘어감
	  // [앨]은(는), 0에서 255까지의 허용 범위 바깥에 있으므로 인코딩될 수 없습니다.
	  // ★보내는 데이터 문제.. + name  -> name을 인코딩! -> ★URLEncoder.encode(name, "UTF-8")★
	  // 
	  response.sendRedirect("/servlet/redirect2?name=" + URLEncoder.encode(name, "UTF-8"));
	  
	   
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
