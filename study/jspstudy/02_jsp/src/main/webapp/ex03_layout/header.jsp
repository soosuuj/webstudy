<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%-- 파라미터를 보낼 수 있다. name="title" 이걸 받아옴 main에서/
     ﻿request 는 선언하지 않고 사용하는 내장 객체 중 하나  
     Optional 블로그 추가 설명
     orElse ->  request를 옵셔널로 감싸고 null 값도 처리할 수 있게 싸는 것
     null 이면 환영합니다. // 처음 실행 화면 타이틀은 "환영합니다." 뜸 
     contextPath /jsp 였던거 변수처리해서 바꿈 --%>
<% 
  request.setCharacterEncoding("UTF-8");
  Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
  String title = opt.orElse("환영합니다.");
  String contextPath = request.getContextPath();
%>
<title><%=title %></title>

<%-- resource 밑에 js(jQuery) 붙여와서 만듦  jQuery -> js -> css--%>
<script src="<%=contextPath%>/resource/js/lib/jquery-3.7.1.min.js"></script>

<%-- 태그 위에 만들면 인식이 안됨 -> 
해결하려면? onload = function(){이 안의 자바 스크립트 코드는 다 읽은 다음에 처리, 
밑에다 스크립트 만든 효과!!} -> header.js에 추가 설명과 예제 있음--%>  
<script src="<%=contextPath%>/resource/js/header.js?dt=<%=System.currentTimeMillis()%>"></script>

<%-- 실행할 때마다 경로를 바꿔주자! 
     캐싱 -> 안바꾸는거 볼라고??? 
     캐싱을 회피하기위한 임의의  마지막에 파라미터 ?ver=1.0 바꿀 때마다 변경
     자동화 : > ?dt= 타임스탬프 -> 한글자 바꿀 때마다 변경, 개발 중엔 이걸로 사용
     -> 더이상 css 수정하지 않는다면 ver으로 바꿔 놓는게 좋음
     
     * 더이상 상대경로 ../../ 사용 안함, 절대 경로!! 
     프로젝트 변경 시 폴더 구조가 달라질 수 있으므로 어셈블리로 URL확인할 수 있어야함 --%>
<link rel="stylesheet" href="<%=contextPath%>/resource/css/header.css?dt=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="<%=contextPath%>/resource/css/main.css?dt=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="<%=contextPath%>/resource/css/footer.css?dt=<%=System.currentTimeMillis()%>">
</head>
<body>


  <nav class="gnb">
    <div class="logo"></div>
    <ul>
      <li><a href="main1.jsp">main1</a></li>
      <li><a href="main2.jsp">main2</a></li>
      <li><a href="main3.jsp">main3</a></li>
    </ul>
  </nav>

  
  
<%--
</body>
</html>
 --%>