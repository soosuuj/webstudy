<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 하기, 모든 jsp에 넣는 기본 구성이라고 생각!, 
     sun -> java를 만든 회사, 지금은 없어짐 -> 오라클 
     태그 이름이 c로 시작 prefix="c" 
     속성  binding  영역에 만들 수 있음 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%--
    <c:set> 태그
    1. 속성(Attribute)을 저장할 때 사용한다.
    2. binding 영역을 지정할 수 있다.(디폴트는 pageContext에 저장임)
         영역              : 작성방법
      1) pageScope         : page
      2) requestScope      : request
      3) sessionScope      : session
      4) appplicationScope : appplication
      
    3. 형식
      <c:set var="속성명" value="값" scope="binding영역" / > 
                              스코프생략 시 어따 저장? pageContext
                              스스로 닫는 태그로 연습하기
   
    jstl은 모두 el 문법 지원 -> le문법으로 내용을 채울 수 있다..
   --%>
   
   <%-- 속성 저장하기, 만들어둔 속성은 같은 페이지에서 바로 바로 쓸 수 있음 --%>
   <c:set var="age" value="30"/>   <%-- pageContext.setAttribute("age", 30) --%>
   <c:set var="isAdult" value="${age >= 20}"/>
   
   <div>나이 : ${age}살</div>
   <div>${isAdult ? "성인" : "미성년자"}</div>
  
   <%-- 앞으로 자주 사용할 c:set / 출력 : /jsp (최종!!) --%>
   <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   <div>${contextPath}</div>

</body>
</html>