package exe01_01_연습문제_로그인;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class exercise
 */
@WebServlet("/exercise")
public class exercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public exercise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
    // 2. 요청 파라미터를 받는다. 
		String id = request.getParameter("id");
		    
    // 3. 요청 파라미터의 타입을 조정한다. 
//		아이디
		if (id == null || id.trim().isEmpty()) {
		  response.sendRedirect("/errorPage.jsp");
		}
    System.out.println("아이디:" + id );
    
//    비밀번호
    String pw = request.getParameter("pw");
    System.out.println("비밀번호:" + pw);
    
//    이름
    String name = request.getParameter("name");
    System.out.println("이름:" + name);

	}

	private void alert(String string) {
    // TODO Auto-generated method stub
    
  }

  /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
