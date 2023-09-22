package ex02_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/request")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 문자기반의 스트링을 만들 주겠다 response.getWriter() 서버가 사용자에게 보내기 위해서 문자를 보내겠다(태그 보내는 것)
	 * getContextPath -> /서블릿 이라는 텍스트가 빠져나옴...
	 * getContextPath/텍스트 를 사용자에게 보여주겠다는 것
	 * 
	 * HttpServletRequest request -> 요청을 처리하는 아이 
	 * 
	 * 숫자를 보냈으나  String으로 받음 
	 *  -> "모든 파라미터는 string으로 보냄 -> String으로 받아야함"
	 *  
	 *  파라미터는 한글로 보내면 깨진다 -> 인코딩 필요
	 *  
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 1. 요청을 UTF-8로 인코딩한다.(제일 먼저해야 함, 기본!!) -> 한글 보낼 수 있음
		request.setCharacterEncoding("UTF-8");
		
		// 2. 요청 파라미터를 받는다. 
		//  1) 요청 파라미터는 모두 String 타입이다.
		//  2) 요청 파라미터가 없으면 null(출력) 값으로 처리된다. 
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		
		// 3. 요청 파라미터의 타입을 조정한다. 
		//  타입을 조정할 때 Exception이 발생하지 않도록 요청 파라마미터의 null 처리 + 빈 문자열 처리가 필요하다.
		// 까먹지 말자 - Integer.parseInt
		// int age = Integer.parseInt(strAge);
		
		
		// 1) 고전 null 처리  -> 밖으로 선언을 빼주고 처리해야함
		/*
		int age = 0; 
		if(strAge != null) {
		  age = Integer.parseInt(strAge);
		}
		*/
		
		// 2) null 처리를 위한 java.util.Optional 클래스 처리 (java.util에 있는거임)
		//    -> 감싸는 작업 null 일 때 꺼낼 값을 지정함, 값이 있으면 그걸 꺼냄. 
		//    <T> 제네릭 -> 어떤 타입을 감쌀건지 물어보는 것
		//    ofNullable null 값도 같이 처리해줌 -> 2가지가 가능.. null 값 대비!
		//                          받은 것 중 하나가 null 이고, 아닐 수 도 있어
		//    opt.orElse -> 있으면 꺼내고, 없으면 0을 꺼내시오... 
		
		/*
		Optional<String> opt = Optional.ofNullable(strAge);
		int age = Integer.parseInt(opt.orElse("0")); 
		*/
		// opt로 감싼 strAge가 null이면 "0"을 꺼낸다. 아니면 원래 값 나옴
		
		// null 처리 + 빈 문자열 처리
		int age = 0;
		if(strAge != null && !strAge.isEmpty()) {
		  age = Integer.parseInt(strAge);
		}
		
		System.out.println(name + ", " + age);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    response.getWriter().append("Served at: ").append(request.getContextPath());
    // 여기다 포스트 밑 두줄 복사해서 기존 내용 없애고 덮어버림, 취미는 여기로 넘어옴
    
    // 1. 요청을 UTF-8로 인코딩 한다. -> post로 받는거라 또 필요!
    request.setCharacterEncoding("UTF-8");
    
    // 2. 동일한 요청 파리미터가 2개 이상 전달되는 경우 
    // getParameterValues() 메소드를 이용해서 파라미터들을 배열로 받는다.
    // 전부 여기로 넘어옴
    String[] tel = request.getParameterValues("tel");
    String[] hobbies = request.getParameterValues("hobbies");
    
    // for문 없이 배열에 있는 요소 확인 가능
    System.out.println(Arrays.toString(tel));
    System.out.println(Arrays.toString(hobbies));
    
    
    
    
    
    
    
    
	}

}
