<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <%-- 이해정도.. --%>

  <c:set var="str" value="Hello World" />
  
  <h4>${fn:length(str)}</h4>   <%-- 출력 : 11, 값을 처리하는 함수 호출 --%>
  <h4>${fn:substring(str, 0, 5)}</h4>      <%-- 출력 : Hello  --%>
  <h4>${fn:substringBefore(str, ' ')}</h4> <%-- 출력 : Hello  --%>
  <h4>${fn:substringAfter(str, ' ')}</h4>  <%-- 출력 : World  --%>
  <h4>${fn:indexOf(str, ' ')}</h4>         <%-- 출력 : 5 --%>
  <h4>${fn: replace(str, ' ', '_')}</h4>   <%-- 출력 : Hello_World --%>
  
  <c:if test="${fn:startsWith(str, 'H')}">
    <h4>H로 시작한다.</h4>                   <%-- H로 시작한다. --%>
  </c:if>
  <c:if test="${fn:endsWith(str, 'H')}">
    <h4>H로 끝난다.</h4>
  </c:if> 
  <c:if test="${fn:contains(str, 'H')}">     <%-- H를 포함한다. --%>
    <h4>H를 포함한다.</h4>
  </c:if>
  <c:if test="${fn:containsIgnoreCase(str, 'h')}">  <%-- H, h를 포함한다. --%>
    <h4>H, h를 포함한다.</h4>
  </c:if>
  
  <c:set var="words" value="${fn:split(str, ' ')}" /> <!-- 배열 생성 -->
  <c:forEach var="word" items="${words}">         <%-- Hello \n  World / split 나눠진것을 하나로 합쳐주는 역할--%>
      <h4>${word}</h4>
  </c:forEach>
  <h4>${fn:join(words, ' ')}</h4>                 <%-- Hello World  / join으로 묶음--%>
  
  <%-- 
  주어진 코드는 주어진 문자열 "str"을 공백을 기준으로 분할하고 
  각 단어를 배열로 저장한 다음, 배열의 각 항목을 반복적으로 출력하고, 
  마지막으로 다시 단어들을 합쳐서 출력하는 기능을 수행합니다.

1. `<c:set>` 태그를 사용하여 "words"라는 변수를 생성하고, 
이 변수에 "str" 문자열을 공백을 기준으로 분할하여 배열로 저장합니다.
   
   ```jsp
   <c:set var="words" value="${fn:split(str, ' ')}" />
   ```

   이 코드는 "str" 문자열을 공백을 기준으로 분할하고, 
   분할된 단어들을 "words" 변수에 저장합니다.

2. `<c:forEach>` 태그를 사용하여 "words" 배열의 각 항목을 반복적으로 출력합니다. 
이를 통해 배열의 각 단어를 개별적인 `<h4>` 태그로 렌더링합니다.

   ```jsp
   <c:forEach var="word" items="${words}">
       <h4>${word}</h4>
   </c:forEach>
   ```

   이 부분은 "words" 배열의 각 항목을 "word" 변수에 할당하고, 
   각 단어를 `<h4>` 태그로 출력합니다.

3. 마지막으로, `<h4>` 태그를 사용하여 "words" 배열의 모든 항목을 공백으로 구분하여 
다시 하나의 문자열로 합칩니다.

   ```jsp
   <h4>${fn:join(words, ' ')}</h4>
   ```

   이 코드는 "words" 배열의 모든 항목을 " " (공백)을 구분자로 사용하여 합칩니다. 
   그리고 이를 `<h4>` 태그로 감싸서 출력합니다.

이 코드는 주어진 문자열을 분할하여 각 단어를 출력하고, 
그 다음에는 모든 단어를 다시 하나의 문자열로 합쳐서 출력합니다.
  
   --%>
   
   <c:set var="str2" value="<script>alert('hahaha')</script>" />  
   ${str2}                    <%-- 출력 : 경고창 hahaha  --%>
   ${fn:escapeXml(str2)}      <%-- 출력 :  <script>alert('hahaha')</script> --%>
  
  
  

</body>
</html>