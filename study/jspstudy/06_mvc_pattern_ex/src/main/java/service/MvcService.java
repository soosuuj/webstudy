package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface MvcService {
  
  public ActionForward getAge(HttpServletRequest request);  // 응답은 알아서 response 안함
  
  public void getAbs(HttpServletRequest request, HttpServletResponse reponse) throws IOException;
  
  public ActionForward getInfo(HttpServletRequest request);
  

}
