package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.ArticleService;
import service.ArticleServiceImpl;


/**
 * Servlet implementation class ArticleController
 */
@WebServlet("*.do")
public class ArticleController extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ArticleController() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // articleFilter 실행 후 Controller 실행
    
    // 요청 인코딩(articleFilter가 수행함) + 응답 타입과 인코딩
    // request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    
    // 요청 주소 확인
    String requestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlMapping = requestURI.substring(contextPath.length());
    
    // 어디로 어떻게 이동할 것인지 알고 있는 ActionForward 객체
    ActionForward af = null;
    
    // ArticleService 객체 생성
    ArticleService articleService = new ArticleServiceImpl();

    // 요청에 따른 처리
    switch(urlMapping) {
    // 단순 이동 (forward 처리) select의 결과를 request에 저장해서 화면에 보여주는 것까지 처리해줘야함
    case "/article/write.do":
      af = new ActionForward("/article/write.jsp", false);
      break;
    case "/index.do":
      af = new ActionForward("/index.jsp", false);
      break;
      
    // 서비스 처리
    case "/article/register.do":
      af = articleService.register(request);
      break;
    case "/article/list.do":  
      af = articleService.getArticleList(request); 
      break;
    case "/article/detail.do":
      af = articleService.getArticleByNo(request);
      break;
    case "/article/edit.do" :
      af = articleService.edit(request);
      break;
    case "/article/modify.do" :
      af = articleService.modify(request);
      break;
    case "/article/delete.do" :
     af = articleService.delete(request);
      break;
    }
    
    // 이동
    if(af != null) {
      if(af.isRedirect()) {
        response.sendRedirect(af.getPath());
      } else {
        request.getRequestDispatcher(af.getPath()).forward(request, response);
      }
    }
    
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}