package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  private MemberService memberService = new MemberServiceImpl();

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8"); // 필터 없어져서 다시 데려옴
    
    // impl 에 적어 놓은 응답  response.setContentType("text/plain; charset=UTF-8"); 
    // 이걸 데려오고 싶다.. 
    response.setContentType("text/plain; charset=UTF-8");  // JSON 데이터를 문자열로 반환함, 모든 서비스에서 하지 않으려고 데려옴
    
    String requestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlMapping = requestURI.substring(contextPath.length());
    
    // redirect forward가 없
    switch(urlMapping) {
    case "/member/list.do":
      memberService.getMemberList(response);
      break;
    case "/member/add.do":
      memberService.memberAdd(request, response);
      break;
    case "/member/emailCheck.do":
      memberService.memberEmailCheck(request, response);
      break;
    case "/member/detail.do":
      memberService.memberDetail(request, response);
      break;
    case "/member/modify.do":
      memberService.memberModify(request, response);
      break;
    case "/member/delete.do":
      memberService.memberDelete(request, response);
      break;
    }
    
  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}