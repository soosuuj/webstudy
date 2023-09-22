package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.ArticleDto;
import repository.ArticleDao;
import util.PageVo;

public class ArticleServiceImpl implements ArticleService {

  // 모든 서비스가 공동으로 사용하는 ArticleDao, PageVo 객체 가져오기
  private ArticleDao dao = ArticleDao.getDao();
  private PageVo pageVo = new PageVo();

  @Override
  public ActionForward register(HttpServletRequest request) {
    
    // 등록할 제목 작성자 내용
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String editor = request.getParameter("editor");
    
    // 제목 + 작성자 +내용  -> ArticleDto 객체
    ArticleDto dto = ArticleDto.builder()
                      .title(title)
                      .content(content)
                      .editor(editor)
                      .build();
    
    // ArticleDao의 register 메소드 호출
    int registerResult = dao.register(dto);
    
    // 등록 성공(registerResult == 1), 등록 실패(registerResult == 0)
    String path = null;
    if(registerResult == 1) {
      path = request.getContextPath() + "/article/list.do";
    } else if(registerResult == 0) {
      path = request.getContextPath() + "/index.do";
    }
    
    // 어디로 어떻게 이동하는지 반환 (insert 수행 후에는 반드시 redirect 이동한다.)
    return new ActionForward(path, true);
    
  }

  @Override
  public ActionForward getArticleList(HttpServletRequest request) {
    
    /* page, total, display 정보가 있어야 목록을 가져올 수 있다. */
    
    // 전달된 페이지 번호 (페이지 번호의 전달이 없으면 1 페이지를 연다.)
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    int total = dao.getArticleCount();
    
    int display = 5;  // 고정 값 사용(원하면 파라미터로 받아 오는 것으로 변경도 가능함)
    
    // PageVo의 모든 정보 계산하기
    pageVo.setPaging(page, total, display);
    
    // 게시글 목록을 가져올 때 사용할 변수들을 Map으로 만듬
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageVo.getBegin());
    map.put("end", pageVo.getEnd());
    
    // DB로부터 게시글 목록 가져오기
    List<ArticleDto> articleList = dao.getArticleList(map);
    
    // 게시글 목록과 paging을 /Article/list.jsp로 전달하기 위하여 request에 저장한 뒤 forward한다.
    request.setAttribute("articleList", articleList);
    request.setAttribute("paging", pageVo.getPaging(request.getContextPath()+ "/article/list.do"));
    return new ActionForward("/article/list.jsp", false);
    
  }
  
  @Override
  public ActionForward getArticleByNo(HttpServletRequest request) {
    // 상세 조회할 게시글의 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));  // 0번이라 조회 안됨, 이거 안해주면 예외 발생 - 예외 회피 위해 작성
    
    // DB로부터 게시글 가져오기
    ArticleDto article = dao.getArticleByNo(article_no);
    
    
    // 게시글을 /Article/detail.jsp에 전달하기 위해서 forward 처리
    request.setAttribute("article", article);
    return new ActionForward("/article/detail.jsp", false);
  }

  @Override
  public ActionForward edit(HttpServletRequest request) {
    
    // 편집할 게시글의 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0")); 
    
    // DB로부터 게시글 가져오기
    ArticleDto article = dao.getArticleByNo(article_no);
    
    // 게시글을 /Article/edit.jsp에 전달하기 위해서 forward 처리
    request.setAttribute("article", article);
    return new ActionForward("/article/edit.jsp", false);

    
  }

  @Override
  public ActionForward modify(HttpServletRequest request) {
    
    // 수정할 게시글 정보
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    int article_no = Integer.parseInt(request.getParameter("article_no"));
    
    // 수정할 게시글 정보를 ArticleDto 객체로 생성
    ArticleDto dto = ArticleDto.builder()
                      .title(title)
                      .content(content)
                      .article_no(article_no)
                      .build();
    
    // 수정하기
    int modifyResult = dao.modify(dto);
    
    // 수정 성공(modifyResult == 1), 수정 실패(modify == 0)
    
    String path = null; 
    if(modifyResult == 1) {
      path = request.getContextPath() +"/article/detail.do?article_no=" + article_no;
    } else {
      path = request.getContextPath()+"/index.do";
    }
    // update 이후에는 redirect 한다.
    return new ActionForward(path, true);
  }
  
  @Override
  public ActionForward delete(HttpServletRequest request) {

    // 삭제할 게시글의 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0")); 
    
    
    // 삭제하기 
    int deleteResult = dao.delete(article_no);
    
    // 삭제 성공(deleteResult == 1), 삭제 실패 0
    
  String path = null;
  
  if(deleteResult == 1) {
    path = request.getContextPath() + "/article.list.do";
  } else if (deleteResult == 0) {
    path = request.getContextPath() + "/index.do";
  }
    // delete 이후에는 redirect 한다.
    return new ActionForward(path, true);
   
    
  }
  
  
 
  
}


