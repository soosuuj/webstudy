package ex06_session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  // 로그아웃 : session 초기화, 모든 정보를 날려버림
	  HttpSession session = request.getSession();
	  session.invalidate();
	  
	  // 로그인 화면으로 돌아가기  /login/ex06_session.login.jsp 잘못씀 ﻿서블릿은 패키지 경로가 없으니까 저렇게 쓰면안됨!!
	  // URLMapping 값 뒤에 .od 붙여서 서블릿으로 인식할 수 있게 작성 예정
    response.sendRedirect(request.getContextPath() + "/ex06_session/main.jsp");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
