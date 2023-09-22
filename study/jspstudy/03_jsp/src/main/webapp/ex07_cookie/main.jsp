<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<%-- 이렇게 짜면 유지보수가 힘듦, 복습용 --%>

</head>
<body>

<%
  String remember_me = ""; // 쿠기 remember_me 가 없으면 빈 문자열("")을 사용하기 위해서 초기화를 진행함
  Cookie[] cookies = request.getCookies();
  if(cookies != null) {
    for(int i = 0; i< cookies.length; i++){
      if(cookies[i].getName().equals("remember_me")){
        remember_me = cookies[i].getValue();
        break;
      }
    }
  }
  pageContext.setAttribute("remember_me", remember_me); // 속성으로 값 저장, 확인은 ${remember_me} 로 확인 가능

%>


  <div>
    <form method="post" action="${contextPath}/rememberme">  
      <div>
        <label for="id">아이디</label>
        <input type="text" name="id" id="id">
      </div>
      <div>
        <label for="pw">비밀번호</label>
        <input type="password" name="pw" id="pw">
      </div>
      <div>
        <button type="submit">로그인</button>
      </div>
      <div>
        <input type="checkbox" id="remember_me" name="remember_me">
        <label for="remember_me">아이디 기억</label>
      </div>
    </form>
  </div>
  
  <%-- check 박스 체크 안하면 null 값들어옴 --%>
  
  <script>
    // <body> 태그를 모두 읽은 뒤 function(){}을 실행한다.
    // <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    //  var="contextPath" 이거 가져다가 변수처리하여 사용
    $(function(){
      if('${remember_me}' !== ''){
        $('#id').val('${remember_me}');
        $('#remember_me').prop('checked', true);
      }
    });
  </script>




</body>
</html>
