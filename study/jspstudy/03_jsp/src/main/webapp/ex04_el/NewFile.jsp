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
    1. pageContext : this,               현재 페이지에서만 접근할 수 있다.
    2. request     : HttpServletRequest, 응답 전까지 접근할 수 있다.
    3. session     : HttpSession,        브라우저를 닫기 전까지 접근할 수 있다.
    4. application : ServletContext,     애플리케이션 종료 전까지 접근할 수 있다.
    
    Jsp binding 우선 순위 (동일한 이름의 속성이 서로 다른 영역에 존재하는 경우 먼저 사용되는 속성이 있다.)
    높음                                     낮음
    pageContext > request > session > application
    
    Jsp binding 영역에 저장된 속성(Attribute)은 모두 표현언어(EL)로 표현할 수 있다.    
  --%>
  
  <%--
    표현언어(EL)
    1. Expression Language
    2. binding 영역에 저장된 값을 나타낼 때 사용할 수 있는 언어이다.
    3. Jsp 표현식 <%=값%>을 대체할 수 있다.
    4. 형식
      ${값}
    5. binding 영역 EL 내장 객체
      1) pageContext 를 의미하는 pageScope 객체
      2) request     를 의미하는 requestScope 객체
      3) session     을 의미하는 sessionScope 객체
      4) application 을 의미하는 applicationScope 객체
  --%>
  
  <%-- 각 영역에 속성 msg 저장하기 --%>
  <%
    pageContext.setAttribute("msg", "pageContext");
    request.setAttribute("msg", "request");
    session.setAttribute("msg", "session");
    application.setAttribute("msg", "application");
  %>
  
  <%-- 우선 순위 확인하기 --%>
  <div>${msg}</div>
  
  <%-- 각 영역의 속성 확인하기 --%>
  <div>${pageScope.msg}</div>
  <div>${requestScope.msg}</div>
  <div>${sessionScope.msg}</div>
  <div>${applicationScope.msg}</div>
  
  <%--
  <% %>은 JSP 내에서 Java 코드를 실행할 때 사용되는 스크립트릿(Scriptlet) 구문입니다. 
  이를 사용하면 Java 코드를 직접 JSP 페이지 내에 작성할 수 있습니다. 
  스크립트릿은 Java 코드를 사용하여 동적인 로직을 구현할 때 유용합니다.

  ${}는 EL을 사용하여 변수를 출력하거나 간단한 연산 및 조건문을 
  JSP 페이지에서 수행할 때 사용됩니다. EL은 JSP 페이지를 더 간결하고 가독성 있게 만들며, 
  자주 사용되는 변수나 데이터를 표현할 때 특히 유용합니다.
  
   --%>
  
  <%-- 객체 사용하기 --%>
  <%
    // 객체를 만든 뒤 EL 사용이 가능한 binding 영역에 넣는다.
    Person person = new Person();
    person.setName("홍길동");
    person.setAge(30);
    pageContext.setAttribute("person", person);
  %>
  <div>이름 : ${person.name}</div>  <%-- EL은 person.name을 person.getName() 방식으로 호출해서 보여준다. --%>
  <div>나이 : ${person.age}</div>   <%-- EL은 person.age를  person.getAge()  방식으로 호출해서 보여준다. --%>
  
  <%--
  `pageContext.setAttribute("person", person);`는 JSP 페이지에서 사용할 수 있는 데이터를 설정하고 저장하는 코드. 
  `person`이라는 이름의 객체를 `pageContext`에 저장, 이를 특정 이름("person")으로 식별할 수 있도록 함. 

1. `pageContext`:
   - `pageContext`는 JSP 페이지에서 사용할 수 있는 내장 객체 중 하나.
   - `pageContext` 객체는 JSP 페이지의 생명 주기와 관련된 정보를 제공하고, 
   JSP 페이지 내에서 데이터를 공유하는 데 사용.

2. `setAttribute("person", person)`:
   - `setAttribute` 메서드를 호출하여 데이터를 저장하고 설정합니다.
   - `"person"`은 저장할 데이터에 대한 식별자 또는 키(key)입니다. 
   이를 통해 나중에 데이터를 검색하거나 사용할 수 있습니다.
   - `person`은 저장할 데이터, 여기서는 `Person` 객체입니다.
   
   +추가
  `setAttribute("person", person)`에서 전달되는 것은 객체를 통으로 저장하는 것입니다. 
  `setAttribute` 메소드를 사용하여 데이터를 저장할 때, 전체 객체를 저장할 수도 있습니다. 
  이 경우, 객체와 그 객체를 식별하는 키(key)를 지정하여 저장합니다.

위의 코드에서는 `"person"`이라는 키(key)를 사용하여 `person` 객체를 `pageContext`에 저장하였습니다. 
이렇게 저장한 객체는 이후에 JSP 페이지 내에서 `${person}`와 같이 EL(Expression Language)을 사용하여 
해당 객체에 접근할 수 있습니다.

저장된 객체를 통째로 가져올 수 있으므로, JSP 페이지에서 해당 객체의 메소드를 호출하거나 
속성에 접근하여 데이터를 표시하거나 조작하는 데 사용할 수 있습니다. 
이것은 JSP에서 데이터를 동적으로 표시하거나 처리하는 데 매우 편리한 방법 중 하나입니다.

위 코드는 `person`이라는 이름으로 `Person` 객체를 `pageContext`에 저장합니다. 
해당 JSP 페이지에서는 `person`이라는 이름으로 저장된 데이터에 접근할 수 있게 됩니다. 
예를 들어, 이후에 `${person.name}` 또는 `${person.age}`와 같이 EL(Expression Language)을 사용하여 `
Person` 객체의 속성에 접근할 수 있습니다. 이를 통해 JSP 페이지에서 Java 객체를 사용하여 
동적인 데이터를 표시하거나 조작할 수 있습니다.
  
   --%>
  
  <%-- Map 사용하기 --%>
  <%
    // Map을 만든 뒤 EL 사용이 가능한 binding 영역에 넣는다.
    Map<String, Object> book = new HashMap<>();
    book.put("title", "소나기");
    book.put("price", 10000);
    pageContext.setAttribute("book", book);
  %>
  <div>제목 : ${book.title}</div>
  <div>가격 : ${book.price}</div>
  
  <%--
    EL 연산자
    
    1. 산술
      1) +
      2) -
      3) *
      4) / 또는 div
      5) % 또는 mod
      
    2. 크기 비교
      1) >  또는 gt
      2) <  또는 lt
      3) >= 또는 ge
      4) <= 또는 le
      5) == 또는 eq
      6) != 또는 ne
    
    3. 논리 연산
      1) 논리 AND : && 또는 and
      2) 논리 OR  : || 또는 or
      3) 논리 NOT : !  또는 not
    
    4. 조건 연산
      (조건식) ? true일 때 : false일 때
  --%>
  <%
    pageContext.setAttribute("a", 5);
    pageContext.setAttribute("b", 2);
  %>
  <div>${a + b}</div>
  <div>${a / b}</div>
  <div>${a * b}</div>
  <div>${a / b}</div>
  <div>${a div b}</div>
  <div>${a % b}</div>
  <div>${a mod b}</div>
  
  <div>${a gt b}</div>
  <div>${a lt b}</div>
  <div>${a ge b}</div>
  <div>${a le b}</div>
  <div>${a eq b}</div>
  <div>${a ne b}</div>
  
  <div>${a eq 5 && b eq 2}</div>
  <div>${a eq 5 and b eq 2}</div>
  <div>${a eq 5 || b eq 2}</div>
  <div>${a eq 5 or b eq 2}</div>
  <div>${!(a eq 5)}</div>
  <div>${not (a eq 5)}</div>

  <%--
    request 사용 시 주의사항
    
    1. 속성(Attribute)을 저장한 경우
      request.setAttribute("name", "홍길동") --->>> ${name}
    
    2. 파라미터(Parameter)를 저장한 경우
      /ContextPath/URLMapping?name=홍길동    --->>> ${param.name}
      
      1. **속성(Attribute)을 저장한 경우:**
   - `request.setAttribute("name", "홍길동")`와 같이 `request` 객체에 속성을 저장하는 경우, 
   이 속성은 해당 요청 범위 내에서 사용할 수 있습니다. 요청 범위는 현재 HTTP 요청 내에서만 데이터가 유효하며, 
   다른 JSP 페이지 또는 서블릿에서도 접근할 수 있습니다.
   - `${name}`과 같이 EL(Expression Language)을 사용하여 `name`이라는 속성을 가져올 수 있습니다. 
   `${name}`은 `request` 객체에 저장된 `name` 속성 값을 나타냅니다.

      2. **파라미터(Parameter)를 저장한 경우:**
   - `/ContextPath/URLMapping?name=홍길동`과 같이 URL에 쿼리 문자열(parameter)로 파라미터를 전달한 경우, 
   이러한 파라미터는 `${param.name}`과 같이 EL을 사용하여 접근할 수 있습니다.
   - `${param.name}`은 현재 요청의 쿼리 문자열에서 `name` 파라미터 값을 가져옵니다. 
   이는 주로 사용자가 웹 페이지로 전달한 데이터를 가져오는 데 사용됩니다.
   - URL 파라미터는 주로 GET 방식 요청에서 사용되며, 웹 페이지에 데이터를 동적으로 전달할 때 유용합니다.

두 가지 방법은 JSP 페이지에서 데이터를 동적으로 가져오는 데 사용됩니다. 
속성은 주로 서블릿 또는 다른 JSP 페이지에서 데이터를 공유하는 데 사용되며, 
파라미터는 주로 웹 브라우저에서 서버로 데이터를 전달할 때 사용됩니다. 
이러한 방법을 통해 JSP 페이지는 동적으로 데이터를 표시하거나 처리할 수 있으며, 
웹 애플리케이션의 유연성을 높일 수 있습니다.
      
  --%>

</body>
</html>