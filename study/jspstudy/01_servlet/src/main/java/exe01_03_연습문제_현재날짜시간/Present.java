package exe01_03_연습문제_현재날짜시간;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Present
 */
@WebServlet("/present")
public class Present extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Present() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩 
	   request.setCharacterEncoding("UTF-8");
		
		// 2. 요청 파라미터를 받는다.
		String type = request.getParameter("type");
		
		String result = null;
		switch(type){
		case "1" : // 현재 날짜 요청
		  result = LocalTime.now().toString();
		  break;
		case "2" : // 현재 시간 요청
		  result = LocalDateTime.now().toString();
		  break;
		}
		
		// 응답 타입과 인코딩
    response.setContentType("text/html; charset=UTF-8");
		
		// 응답 출력 스트림 생성
		PrintWriter out = response.getWriter();
		
		// 응답 만들기
    out.println("<script>");
    out.println("alert('요청 결과는 " + result + "입니다.')");
    out.println("history.back()");  // 이전 화면으로 이동하기
    out.println("</script>");
    out.flush();
    out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
