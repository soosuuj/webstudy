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
  <div><fmt:formatDate value="${now}" pattern="a h:mm:ss"/> </div>
  <div><fmt:formatDate value="${now}" pattern="H:mm:ss"/> </div>
  
<%-- 아래의 코드는 JSTL `<c:set>`을 사용하여 현재 날짜와 시간을 "now" 변수에 설정하고, 
   `<fmt:formatDate>`를 사용하여 "now" 변수의 값을 다양한 형식으로 출력하는 예제입니다.
   
   <fmt:formatDate>는 JSTL(JavaServer Pages Standard Tag Library)의 일부로 제공되는 태그 중 하나입니다. 
   이 태그는 날짜 및 시간 값을 원하는 형식으로 형식화하여 출력하는 데 사용됩니다. 
   주로 날짜와 시간을 다양한 형식으로 표시하거나 출력할 때 사용됩니다.

1. `<c:set var="now" value="<%=new Date()%>"/>`
   - 현재 날짜와 시간을 생성하여 "now"라는 변수에 저장합니다.
   - `<%= ... %>` 구문은 Java 코드를 실행하는 부분입니다.
   - `new Date()`는 현재 날짜와 시간을 나타내는 객체를 생성합니다.
   - `<c:set>` 태그를 사용하여 "now" 변수에 이 값을 저장합니다.

2. `<div><fmt:formatDate value="${now}" pattern="yyyy-MM-dd E요일"/></div>`
   - `<fmt:formatDate>` 태그를 사용하여 "now" 변수의 값을 특정 패턴에 따라 형식화합니다.
   - `value="${now}"`에서 "${now}"는 JSP 변수 "now"의 값을 가져옵니다.
   - `pattern="yyyy-MM-dd E요일"`은 날짜를 "년-월-일 요일" 형식으로 출력하도록 지정합니다.
   - 예를 들어, "2023-09-15 목요일"과 같은 형식으로 출력됩니다.

3. `<div><fmt:formatDate value="${now}" pattern="a h:mm:ss"/> </div>`
   - "now" 변수의 값을 오전/오후 표시와 시간(12시간 형식)으로 출력합니다.
   - `pattern="a h:mm:ss"`에서 "a"는 오전/오후를 나타내며, "h:mm:ss"는 시간을 나타냅니다.
   - 예를 들어, "오후 3:45:30"과 같은 형식으로 출력됩니다.

4. `<div><fmt:formatDate value="${now}" pattern="H:mm:ss"/> </div>`
   - "now" 변수의 값을 24시간 형식으로 출력합니다.
   - `pattern="H:mm:ss"`에서 "H"는 24시간 형식으로 시간을 나타냅니다.
   - 예를 들어, "15:45:30"과 같은 형식으로 출력됩니다.

이러한 코드를 실행하면 현재 날짜와 시간이 다양한 형식으로 포맷팅되어 화면에 출력됩니다. --%>

</body>
</html>