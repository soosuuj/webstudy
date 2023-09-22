<%-- 
  지시어(directive)
  1. page 지시어    : page 설정, Java import  (바로 아래 @으로 시작하는 것)
  2. include 지시어 : 다른 페이지를 현재 페이지에 포함할 때 사용 (상단 고정 메뉴)
  3. taglib 지시어  : JSTL(Java Standard Tag Library) 사용할 때 사용
  
   --%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- HTML 주석 ctrl+shift+/ : "소스 보기"에서 보인다.    Java 관련 코드는 주석처리하지 못한다.-->
 <%-- JSP 주석 - 직접 써야함 : "소스 보기"에서 안 보인다. Java 관련 코드를 주석처리할 수 있다. --%>
  
  <%-- 스크립트릿(scriptlet) : Java 코드 --%>
  <% int a = 10; %>
  
  <%-- 표현식(expression) : Java 값(변수, 반환값이 있는 메소드 호출 결과) --%>
  <div><%=a%></div>         <%-- 화면에 10 출력--%>
  
  
  <%-- 
      Java와 JavaScript의 관계 
      1. Java 변수를 JavaScript에서 사용할 수 있다.
      2. JavaScript 변수를 Java에서 사용할 수 없다.
  --%>
  
  <%-- Java 변수 선언 --%>
  <% String msg = "Hello World"; %>
  
  <%-- Java 변수를 JavaScript에서 사용하기 --%>
  <script>
    alert('<%=msg%>');
  </script>
  
  
  
  
  
  <%-- 연습1. 이름과 나이를 변수에 저장한 뒤 보여주자 --%>
  <% String name = "홍길동"; 
     int age = 30; %>

  <ul>
    <li>이름 : <%=name%></li>
    <li>나이 : <%=age%></li> 
  </ul>

  <%-- 선언부(declaration) : Java 메소드 정의(만들때만 사용, 호출x) 
       클래스x -> 함수 느낌, 이름만 적고 호출--%>
  <%!
    // 0~9 사이의 난수를 반환하는 getRundom() 메소드 정의하기
    public int getRandom(){
      return (int)(Math.random() * 10);
  }
  %>
  <div>0~9 사이 난수 : <%=getRandom() %></div>
  
  
  <%-- 연습2. getRandom() 호출 결과가 5 이상이면 큰수 아니면 작은 수를 출력하자. --%>

<%!
  public String getRandom2(){
    if((int)(Math.random() * 10) >= 5){
      return "큰수";
    } else {
      return "작은수";
    }
  }
%>
  
 <div><%=getRandom2() %></div>
 
 <hr>
 <%  if(getRandom() >=5) {  %>
   <div>큰 수</div>
 <% } else { %>
   <div>작은 수</div>
 <% } %>
 
  <hr>
  <%-- 연습3. <select> 태그에 1월~12월까지 등록해서 출력하자. --%>

  <div>
    <select name="month">
      <% for (int month = 1; month <= 12; month++) { %>
        <option value="<%= month %>"><%= month %>월</option>
      <% } %>
    </select>
  </div>

  <hr>
  <%-- 연습4. 현재 날짜를 yyyy-MM-dd 형식의 문자열로 반환하는 getToday() 메소드를 만들고 호출하자. --%>
   
<%!

  public String getToday() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return dtf.format(today);
  }
%>

<div>
  현재 날짜: <%= getToday() %>
</div>




</body>
</html>