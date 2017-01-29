<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Components</title>
</head>
<body>
<table>
    <tr>
        <th>Name</th>
        <th>Prize</th>
    </tr>
    <c:forEach var="component" items="${cps}">
        <tr>
            <td>${component.name}</td>
            <authz:authorize access="hasRole('ADMIN')">
                <td>${component.prize}</td>
            </authz:authorize>
        </tr>
    </c:forEach>
</table>
</body>
</html>
