package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface ArticleService {
  public ActionForward register(HttpServletRequest request);
  public ActionForward getArticleList(HttpServletRequest request); // 페이지 정보 필요
  public ActionForward getArticleByNo(HttpServletRequest request);
  public ActionForward edit(HttpServletRequest request);
  public ActionForward modify(HttpServletRequest request);
  public ActionForward delete(HttpServletRequest request);
  
  
}