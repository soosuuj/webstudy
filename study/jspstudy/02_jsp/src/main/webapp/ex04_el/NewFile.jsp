<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="ex04_el.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%-- 
    Jsp binding 영역 (어떤 값을 속성(Attribute)의 형태로 저장할 때 사용하는 영역)
     추가) 서블릿의 바인딩(저장영역) 3곳 - sevletContext, HttpSession, HttpServletRequest
     
     1. pageContext : this, 현재 페이지에서만 접근할 수 있다.
     2. request : HttpServletRequest, 응답 전까지 접근할 수 있다.
     3. session : HttpSession, 브라우저를 닫기 전까지 접근할 수 있다.
     4. application : ServletContext, 애플리케이션 종료 전까지 접근할 수 있다.  (홈페이지 문 닫을 때까지)
     
     Jsp binding 우선 순위(동일한 이름의 속성이 서로 다른 영역에 존재하는 경우 먼저 사용되는 속성이 있다.)
     높음           낮음    (영역 좁을 수록 우선순위 높음)
     pageContext > request > session > application
     
     Jsp binding 영역에 저장된 속성(Attribute)은 모두 표현언어(EL)로 표현할 수 있다. 
      (4곳에 저장 안되어있으면 le 로 사용 불가)
   --%>
  
  
  <%--
    표현언어(EL)
    1. Expression Language
    2. binding 영역에 저장된 값을 나타낼 때 사용할 수 있는 언어이다.
    3. Jsp 표현식 <%=값%>을 대체할 수 있다.
    4. 형식
      ${값}
    5. 표현언어의 binding 영역 EL 내장 객체
      1) pageContext 를 의미하는 pageScope 객체
      2) request     를 의미하는 requestScope 객체  (요것도 사용) 
      3) ★session     을 의미하는 sessionScope 객채 (많이 사용!!, 상대적으로 적은 데이터 저장, 로그인정보)
      4) application 을 의미하는 applicationScpoe 객체 (거의 쓸일 없음)
   --%>
   
   <%-- 각 영역에 속성 msg 저장하기, 이건 볼일 별로 없음, 다음장 코드 더 많이 본다. --%>
   <% 
     pageContext.setAttribute("msg", "pageContext");
     request.setAttribute("msg", "request");
     session.setAttribute("msg", "session");
     application.setAttribute("msg", "application");
   %>
   
   <%-- 우선 순위 확인하기, 출력 : pageScope --%>
   <div>${msg}</div>         <%--﻿ ${} -> 이거 다 변수값, 퍼블리셔와 의사소통 효율증가 --%>
   
   <%-- 각 영역의 속성 확인하기, 출력 : 전부 나옴 --%>
   <div>${pageScope.msg}</div>
   <div>${requestScope.msg}</div>
   <div>${sessionScope.msg}</div>
   <div>${applicationScope.msg}</div>
  
    <%-- 객체 사용하기 --%>
    <%
    // 객체를 만든 뒤 EL 사용이 가능한 binding 영역에 넣는다.
    Person person = new Person();
    person.setName("홍길동");
    person.setAge(30);
    pageContext.setAttribute("person", person);
    %>
    <div>이름 : ${person.name}</div>    <%-- EL은 person.name을 person.getName() 방식으로 호출해서 보여준다. --%>
    <div>나이 : ${person.age}</div>    <%-- EL은 person.age 를 person.getAge()방식으로 호출해서 보여준다. --%>
    
    <%-- Map 사용하기 (객체 역할을 완벽하게 대체할 수 있음) --%>
    <%
    // Map를 만든 뒤 EL 사용이 가능한 binding 영역에 넣는다.
    Map<String, Object> book = new HashMap<>(); // 뒤 타입 생략 가능
    book.put("title", "소나기");
    book.put("price", 10000);
    pageContext.setAttribute("book", book);   // "book" 이게 올린 이름!
    %>
    <div>제목 : ${book.title}</div>
    <div>가격 : ${book.price}</div>
    
  <%--
  EL 연산자  (특징 : 영문연산자가 있음)
  1. 산술 연산
    1) +
    2) -
    3) *
    4) / 또는 div
    5) % 또는 mod
    
    
   2. 크기 비교
    1) > 또는 gt
    2) < 또는 lt
    3) >= 또는 ge
    4) <= 또는 le
    5) == 또는 eq
    6) != 또는 ne
    
   3. 논리 연산
    1) 논리 AND : && 또는 and
    2) 논리 OR  : || 또는 or
    3) 논리 NOT : ! 또는 not
    
   4. 조건 연산
    (조건식) ? true일 때 : false일 때
    
   --%>
     
  <%--연습1. a=5, b=2, ${a+b}  ${a-b}  ${a*b}  ${a/b}  ${a div}  ${a%b}   ${a mod} --%>
  <%-- 자바랑 결과가 같을 수 없음,
       속성의 타입 5, 2 int로? 아님 object임!! 내부적으로 5.0 / 2.0 이렇게 나눠진거임 --%>
  
  <%
  pageContext.setAttribute("a", 5);
  pageContext.setAttribute("b", 2);
  %>
  
  <div>${a + b}</div>
  <div>${a - b}</div>
  <div>${a * b}</div>
  <div>${a / b}</div>
  <div>${a div b}</div>
  <div>${a % b}</div>
  <div>${a mod b}</div>
  
  
  <%-- 연습2 --%>
  <div>${a gt b}</div>
  <div>${a lt b}</div>
  <div>${a ge b}</div>
  <div>${a le b}</div>
  <div>${a eq b}</div>
  <div>${a ne b}</div>
  
  <%-- 연습3 편한걸로 사용하기--%>
  <div>${a eq 5 && b eq 2}</div>
  <div>${a eq 5 and b eq 2}</div>  
  <div>${a eq 5 || b eq 2}</div>
  <div>${a eq 5 or b eq 2}</div>
  <div>${!(a eq 5)}</div>
  <div>${not (a eq 5)}</div>
  
  <%--
    request 사용 시 주의사항 (속성이름과, 파라미터 구분해서 사용해야함)
    jsp -> jsp로 데이터를 보내면 이걸  ${param.name} 많이 쓰게 됨
    패턴을 배우게 되면 절대 쓸일 없음!!  ${param.name} 현실적으로 볼일 없음!!!
    패턴 jsp -> 서블릿 -> 자바 -> 서블릿 -> jsp  ... 경로가 정해져있음
    
    request에 저장해 두고, 꺼내쓴다고? 모르겠음... 
    a태그에 ? 붙여서 넘기기 등등 
    
    자바 -> 화면으로 데이터 넘겨주기 이제 배울 것임.
    그 때 쓰는게 이거..
    arraylist -> request.setAttribute 이거 써서 화면 출력..
    response에는 데이터 저장이 안됨 -> 대신 request 사용, 
    나중에는 request 로 보내고, request로 받고...  forward 써가지고 어쩌고 
    
    1. 속성(Attribute)을 저장한 경우 (el처리에서 이걸 많이씀)
      request.setAttribute("name", "홍길동")  --> ${name}
    
    2. 파라미터(Parameter)를 저장한 경우
      /ContextPath/URLMapping?name=홍길동    --> ${param.name}
    
   --%>
    
    
</body>
</html>