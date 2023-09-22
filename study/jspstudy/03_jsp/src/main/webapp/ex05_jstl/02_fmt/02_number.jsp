<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <c:set var="n" value="12345.6789"/>
  
  <div><fmt:formatNumber value="${n}" pattern="#,##0"/></div>
  <div><fmt:formatNumber value="${n}" pattern="#,##0.00"/></div>
  
  <div><fmt:formatNumber value="0.5" type="percent"/></div>
  <div><fmt:formatNumber value="${n}" type="currency" currencySymbol="$"/></div>
  <div><fmt:formatNumber value="${n}" type="currency" currencySymbol="￦"/></div>
  
  <%-- 
  12,346
  12,345.68
  50%
  $12,346
  ￦12,346
   --%>
  
  <%--
  주어진 코드는 JSTL의 `<fmt:formatNumber>` 태그를 사용하여 숫자를 다양한 형식으로 포맷하고 출력하는
   예제입니다. 각 줄에 대한 설명은 다음과 같습니다:

1. `<c:set var="n" value="12345.6789"/>`:
   - 이 줄은 `<c:set>` 태그를 사용하여 변수 `n`을 생성하고 값을 설정합니다.
   - `var` 속성은 변수의 이름을 지정하며, `n`은 변수 이름입니다.
   - `value` 속성은 변수에 할당될 값을 지정합니다. 여기에서는 숫자 12345.6789를 할당합니다.

2. `<div><fmt:formatNumber value="${n}" pattern="#,##0"/></div>`:
   - 이 줄은 `<fmt:formatNumber>` 태그를 사용하여 변수 `n`을 지정된 패턴에 따라 포맷하고 출력합니다.
   - `value` 속성은 포맷할 숫자를 나타내며, `${n}`은 이전에 생성한 변수 `n`을 참조합니다.
   - `pattern` 속성은 "#,##0"으로 설정되어 있어, 천 단위로 쉼표를 찍어 표시합니다.

3. `<div><fmt:formatNumber value="${n}" pattern="#,##0.00"/></div>`:
   - 이 줄은 `<fmt:formatNumber>` 태그를 사용하여 변수 `n`을 지정된 패턴에 따라 포맷하고 출력합니다.
   - `pattern` 속성은 "#,##0.00"으로 설정되어 있어, 소수점 이하 두 자리까지 표시하며 천 단위로 쉼표를 찍습니다.

4. `<div><fmt:formatNumber value="0.5" type="percent"/></div>`:
   - 이 줄은 `<fmt:formatNumber>` 태그를 사용하여 0.5를 백분율 형태로 포맷하고 출력합니다.
   - `type` 속성은 "percent"로 설정되어 있어, 입력값을 백분율 형태로 변환합니다.

5. `<div><fmt:formatNumber value="${n}" type="currency" currencySymbol="$"/></div>`:
   - 이 줄은 `<fmt:formatNumber>` 태그를 사용하여 변수 `n`을 달러 통화로 포맷하고 출력합니다.
   - `type` 속성은 "currency"로 설정되어 있어, 입력값을 통화 형태로 변환합니다.
   - `currencySymbol` 속성은 화폐 기호를 지정합니다. 여기에서는 달러 기호("$")가 사용됩니다.

6. `<div><fmt:formatNumber value="${n}" type="currency" currencySymbol="￦"/></div>`:
   - 이 줄은 `<fmt:formatNumber>` 태그를 사용하여 변수 `n`을 원화로 포맷하고 출력합니다.
   - `currencySymbol` 속성은 화폐 기호를 지정합니다. 여기에서는 대한민국 원화 기호("￦")가 사용됩니다.

이 코드는 숫자를 다양한 형식으로 포맷하여 출력하는 간단한 예제를 보여줍니다. 
JSTL의 `<fmt:formatNumber>` 태그를 사용하면 숫자 데이터를 원하는 형식으로 표시하기가 매우 쉽습니다.
  
   --%>
  
</body>
</html>