<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <c:set var="now" value="<%=new Date()%>"/>
  
  <div><fmt:formatDate value="${now}" pattern="yyyy-MM-dd E요일"/></div>
  <div><fmt:formatDate value="${now}" pattern="a h:mm:ss"/></div>
  <div><fmt:formatDate value="${now}" pattern="H:mm:ss"/></div>
  
  
  <%--
  주어진 코드는 JSTL(JSP Standard Tag Library)을 사용하여 현재 날짜와 시간을 가져오고 서로 다른 형식으로 출력하는 예제입니다. 각 줄에 대한 설명은 다음과 같습니다:

1. `<c:set var="now" value="<%=new Date()%>"/>`:
   - `<c:set>` 태그를 사용하여 변수 `now`를 생성하고 값을 설정.
   - `var` 속성은 변수의 이름을 지정하며, `now`는 변수 이름입니다.
   - `value` 속성은 변수에 할당될 값을 지정합니다. 스크립트릿(`<%=new Date()%>`)을 사용하여 
   현재 날짜와 시간을 생성하고 변수 `now`에 할당.

2. `<div><fmt:formatDate value="${now}" pattern="yyyy-MM-dd E요일"/></div>`:
   - `<fmt:formatDate>` 태그를 사용하여 날짜를 특정 패턴에 맞게 포맷하고 해당 값을 출력.
   - `value` 속성은 포맷할 날짜를 나타내며, `${now}`는 이전에 생성한 변수 `now`를 참조합니다.
   - `pattern` 속성은 날짜 및 시간을 어떤 형식으로 포맷할지를 정의.
   "yyyy-MM-dd E요일" 패턴을 사용하여 연도, 월, 일, 그리고 요일을 나타냄.

3. `<div><fmt:formatDate value="${now}" pattern="a h:mm:ss"/></div>`:
   - 이 줄은 다시 `<fmt:formatDate>` 태그를 사용하여 
   날짜를 다른 패턴에 맞게 포맷하고 해당 값을 출력합니다.
   - `pattern` 속성은 "a h:mm:ss"로 설정되어 있어, 
   오전(AM) 또는 오후(PM)를 나타내는 "a"와 시간, 분, 초를 나타내는 "h:mm:ss"를 표시.

4. `<div><fmt:formatDate value="${now}" pattern="H:mm:ss"/></div>`:
   - `<fmt:formatDate>` 태그를 사용하여 날짜를 다른 패턴에 맞게 포맷하고 해당 값을 출력.
   - `pattern` 속성은 "H:mm:ss"로 설정되어 있어, 
   24시간 형식으로 시간을 나타내고 있습니다. "H"는 시간(0-23)을 나타냅니다.

이 코드는 현재 날짜와 시간을 다양한 형식으로 포맷하여 출력하는 간단한 예제를 보여줍니다. 
JSTL의 `<fmt:formatDate>` 태그를 사용하면 날짜 및 시간 데이터를 원하는 형식으로 표시하기가 매우 쉽습니다.
  
   --%>
  
  
  
  <%-- JSTL(JSP Standard Tag Library)은 JavaServer Pages(JSP)를 쉽게 사용하고 
  관리하기 위한 표준 태그 라이브러리입니다. 
  JSTL은 JSP 페이지에서 자주 사용되는 작업을 간단한 태그로 캡슐화하여 
  JSP 코드를 더 깔끔하고 가독성 있게 만들어줍니다.
  웹 애플리케이션 개발을 간편하게 할 수 있습니다.

JSTL은 주로 다음과 같은 작업을 수행하기 위해 사용됩니다:

1. **반복문 및 조건문 처리**: 
`<c:forEach>` 태그를 사용하여 컬렉션의 요소를 반복하고, 
`<c:if>` 태그를 사용하여 조건문을 처리할 수 있습니다.

2. **데이터 출력**: 
`<c:out>` 태그를 사용하여 변수나 표현식의 값을 안전하게 출력할 수 있습니다.

3. **컬렉션 및 맵 처리**: 
`<c:forEach>`를 사용하여 컬렉션과 맵을 반복하고,
 `<c:choose>` 및 `<c:when>` 태그를 사용하여 다중 조건을 처리할 수 있습니다.

4. **URL 및 매개변수 관리**: 
`<c:url>` 태그를 사용하여 URL을 동적으로 생성하고, 
`<c:param>` 태그를 사용하여 매개변수를 설정할 수 있습니다.

5. **페이지 리다이렉션 및 포워딩**: 
`<c:redirect>`와 `<c:forward>` 태그를 사용하여 페이지 리다이렉션 및 
포워딩을 수행할 수 있습니다.

6. **날짜 및 숫자 포맷팅**: 
`<fmt:formatDate>` 및 `<fmt:formatNumber>` 태그를 사용하여 날짜와 숫자를 
원하는 형식으로 포맷할 수 있습니다.

7. **국제화 지원**: 
JSTL은 국제화와 로케일 관리를 지원하기 위한 
`<fmt:setLocale>` 및 `<fmt:setBundle>` 태그를 제공합니다.

JSTL을 사용하면 JSP 페이지에서 Java 코드를 최소화하고 
태그 기반의 프로그래밍 스타일을 채택할 수 있으므로 코드 유지 보수가 쉽고 
생산성이 향상됩니다. 또한, JSTL은 웹 애플리케이션의 디자인과 비즈니스 로직을
 분리하여 관리하기 용이하게 도와줍니다. 
 
 --%>

</body>
</html>