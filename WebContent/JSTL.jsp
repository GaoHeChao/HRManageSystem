<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${ 3>2 }" var="val" scope="page">
   <c:out value="ok"></c:out><br>
   ${val} <br>
</c:if>
测试值：${val}<br>

<c:set var="level" value="1"></c:set>
<c:choose>
   <c:when test="${level==1 }">
      <c:out value="管理员"></c:out><br>
   </c:when>
   <c:when test="${level==2 }">
      <c:out value="操作员"></c:out><br>
   </c:when>
   <c:otherwise>
      <c:out value="显示错误"></c:out><br>
   </c:otherwise>
</c:choose>
</body>
</html>