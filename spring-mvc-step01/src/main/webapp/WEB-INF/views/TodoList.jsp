<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
  <head>
    <title>sample</title>
  </head>
  <body>
  <c:forEach var="todoList" items="${todoList}" varStatus="status">
    <p>${status.count} : <c:out value="${todoList.username}" /></p>
  </c:forEach>
  </body>
</html>
