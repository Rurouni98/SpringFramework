<%--
  Created by IntelliJ IDEA.
  User: bitcamp
  Date: 24. 7. 15.
  Time: 오전 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>name-tel</title>
    <style>
        div > p {
            display: inline-block;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <form action="/name-tel.do" method="post">
        <input type="text" name="name">
        <input type="text" name="tel">
        <input type="submit" value="전송">
    </form>
<%--    <div>--%>
        <!--java에서 전송한 데이터를 el표기법을 이용해서 표출한다.
        자바에서도 데이터를 전송할 때 키: 밸류 매핑된 데이터로 오기 때문에
        키를 이용해서 밸류값을 사용한다.-->
<%--        <p>${nm}</p>--%>
<%--        <p>${tl}</p>--%>
        <!--자바에서 전송한 데이터가 클래스 타입일 경우 전송한 키.그 클래스의 멤버변수명으로 데이터를 꺼내서 사용할 수 있다.-->
<%--        <p>${nameTelDto.name}</p>--%>
<%--        <p>${nameTelDto.tel}</p>--%>
        <!--jstl의 forEach 구문으로 List 형태로 자바에서 넘어온 데이터를 하나씩 꺼내서 표출한다.-->
<%--    </div>--%>
    <c:forEach items="${nameTelList}" var="aaa">
        <div>
            <p>${aaa.name}</p>
            <p>${aaa.tel}</p>
        </div>
    </c:forEach>
</body>
</html>
