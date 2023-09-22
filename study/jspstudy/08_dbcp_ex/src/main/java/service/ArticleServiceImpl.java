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

  private ArticleDao dao = ArticleDao.getDao();
  private PageVo pageVo = new PageVo();

  @Override
  public ActionForward add(HttpServletRequest request) {
    
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String editor = request.getParameter("editor");
    
    ArticleDto dto = ArticleDto.builder()
                      .title(title)
                      .content(content)
                      .editor(editor)
                      .build();
    
    int addResult = dao.articleAdd(dto);
    
    String path = null;
    if(addResult == 1) {
      path = request.getContextPath() + "/getArticleList.do";
    } else if(addResult == 0) {
      path = request.getContextPath() + "/index.do";
    }
    
    return new ActionForward(path, true);
    
  }

  @Override
  public ActionForward list(HttpServletRequest request) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    int total = dao.articleCount();
    
    int display = 5;
    
    pageVo.setPaging(page, total, display);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageVo.getBegin());
    map.put("end", pageVo.getEnd());
    
    List<ArticleDto> articleList = dao.articleList(map);
    
    request.setAttribute("articleList", articleList);
    request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/getArticleList.do"));
    return new ActionForward("/article/list.jsp", false);
    
  }
  
  // 1. 플러스 히트 먼저 돌리고 컨디테일을 돌리기 때문에 -> 이 요청에 의해서 디테일이 실행됨
  // 컨트롤러가 다시 디테일ㅔ호출 -> 서비스 디테일 호출 우리 눈에 최종으로 보이는건 상세보기
  // 상세보기 요청 -> 조회수 증가 -> 상세보기 요청 // 요청을 2개(주소를 2개로 나눠서 )구분했다..
  // 
  @Override
  public ActionForward detail(HttpServletRequest request) {  // 상세보기
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));
    
    ArticleDto article = dao.articleDetail(article_no);  // select를 돌림 , 조회수를 늘리는 update도 하자
    
    request.setAttribute("article", article);
    return new ActionForward("/article/detail.jsp", false);
    
  }

  @Override
  public ActionForward edit(HttpServletRequest request) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));
    
    ArticleDto article = dao.articleDetail(article_no);
    
    request.setAttribute("article", article);
    return new ActionForward("/article/edit.jsp", false);
    
  }
  
  @Override
  public ActionForward modify(HttpServletRequest request) {
    
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    int article_no = Integer.parseInt(request.getParameter("article_no"));
    
    ArticleDto dto = ArticleDto.builder()
                    .title(title)
                    .content(content)
                    .article_no(article_no)
                    .build();
    
    int modifyResult = dao.articleModify(dto);
    
    String path = null;
    if(modifyResult == 1) {
      path = request.getContextPath() + "/getArticleDetail.do?article_no=" + article_no;
    } else {
      path = request.getContextPath() + "/index.do";
    }
    
    return new ActionForward(path, true);
    
  }
  
  @Override
  public ActionForward plusHit(HttpServletRequest request) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
    int article_no = Integer.parseInt(opt.orElse("0"));
    
    int plusHitResult = dao.articlePlusHit(article_no); // 기사 번호로 조회수 증가 
    
    
    String path = null;
    if(plusHitResult == 1) {
      path = request.getContextPath() + "/getArticleDetail.do?article_no=" + article_no;
    } else if(plusHitResult == 0) {
      path = request.getContextPath() + "index.do";
    }
    
    return new ActionForward(path, true);
  }
  
  @Override
  public ActionForward delete(HttpServletRequest request) {

    String articles = request.getParameter("articles");  // 파라미터로 받음
    
    int deleteResult = dao.aricleDelete(articles);
   
    String path = null;
    
    
    // 암튼 체크 될거니까, 1이면 딱 1개만 되고, 나머지 숫자 체크 안됨
    if(deleteResult > 0) {  // if(deleteResult == articles.split(",").length) {
      path = request.getContextPath() + "/getArticleList.do";
    } else {
      path = request.getContextPath() + "/index.do";
    }
   
    return new ActionForward(path, true);
  }
  
  
  
   
}
