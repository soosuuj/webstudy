<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

  // 요청 인코딩
  request.setCharacterEncoding("UTF-8");
  
  // 요청 파라미터
  String createdAt = request.getParameter("createdAt");
  String title = request.getParameter("title");
  String content = request.getParameter("content");
  
  // 요청 파라미터를 작성일자_제목.txt 파일에 저장하기
  
  // 저장할 디렉터리 경로
  String realPath = application.getRealPath("storage");
  
  // E:\study\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\03_jsp\storage
  // 저장할 디렉터리가 없으면 생성
  File dir = new File(realPath);
  if(!dir.exists()){
    dir.mkdirs();
  }
  
  // 저장할 파일명
  String filename = createdAt + "_" + title + ".txt";
  
  // 저장할 파일의 File 객체
  File file = new File(dir, filename);
  
  // 문자 기반 파일 출력 스트림 생성
  BufferedWriter bout = new BufferedWriter(new FileWriter(file));
  
  // 파일로 데이터 보내기 (파일 만들기)
  bout.write("제목 : " + title + "\n");
  bout.write("내용\n");
  bout.write(content);
  bout.flush();
  bout.close();
  
  // 파일 생성 여부를 확인하는 xx.jsp로 이동
  response.sendRedirect(request.getContextPath() + "/ex02_builtin_object/result.jsp?dir=storage&filename=" + URLEncoder.encode(filename, "UTF-8"));
  
%>

<%--`response.sendRedirect(...)`는 웹 애플리케이션에서 사용자를 다른 웹 페이지로 리다이렉트하는 데 사용되는 메소드. 
       주로 웹 페이지 간의 이동이나 다른 URL로 사용자를 이동시키는 데 활용.

1. `request.getContextPath()`: 이 부분은 현재 웹 애플리케이션의 컨텍스트 경로를 가져옵니다. 
컨텍스트 경로는 웹 애플리케이션의 루트 경로를 나타내며, 예를 들어 "/mywebapp"과 같이 형태가 될 수 있습니다.

2. `"/ex02_builtin_object/result.jsp"`: 이 부분은 리다이렉트할 대상 페이지의 경로를 나타냅니다. 
여기서는 "/ex02_builtin_object/result.jsp" 페이지로 이동하려고 합니다.

3. `?dir=storage&filename=" + URLEncoder.encode(filename, "UTF-8")`: 
이 부분은 쿼리 문자열(query string)을 나타냅니다. 이 부분은 URL에 추가적인 매개변수를 전달하는 데 사용됩니다.
   - `dir=storage`: "dir" 매개변수에 "storage" 값을 전달합니다.
   - `filename=`: "filename" 매개변수에는 "filename" 변수의 값을 전달합니다. 
   이 값은 URLEncoder를 사용하여 UTF-8 형식으로 URL-인코딩되어 있습니다. 
   URL-인코딩을 사용하는 이유는 URL에 특수 문자나 공백과 같은 무결성을 갖지 않는 문자가 포함될 경우 대비 위함.

종합하면, 이 코드는 현재 웹 애플리케이션의 컨텍스트 경로를 포함한 URL로 사용자를 
"/ex02_builtin_object/result.jsp" 페이지로 리다이렉트하고, 그 과정에서 "dir"과 "filename" 매개변수를 전달. 
이렇게 하면 "/ex02_builtin_object/result.jsp" 페이지에서 해당 매개변수 값을 사용하여 작업을 수행할 수 있습니다. --%>
