<%@page import="com.icss.bean.DepartmentsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    pageContext.setAttribute("username", "Damon");
    request.setAttribute("username", "mac");
    session.setAttribute("username", "rose");
    application.setAttribute("username", "jack");
    
    
    DepartmentsBean bean = new DepartmentsBean();
    bean.setDepartment_id(999);
    bean.setDepartment_name("皮炎平");
    bean.setLocation_name("故宫");
    request.setAttribute("beans", bean);
    
    Map map = new HashMap();
    map.put("id", "WKN");
    map.put("name", "胃康宁");
    map.put("loc", "北平");
    request.setAttribute("maps", map);
    
    List list = new ArrayList();
    list.add("狄更斯世界");
    list.add("盗梦空间");
    request.setAttribute("lists", list);
    
    List allData = new ArrayList();
    allData.add(bean);
    request.setAttribute("allDatas", allData);
    
%>

    page:${pageScope.username } <br>
    request:${requestScope.username} <br>
    session:${sessionScope.username }  <br>
    application:${applicationScope.username}  <br>
    
    ${requestScope.beans.department_id }<br>
    
    ${requestScope.maps.name }<br>
    
    ${requestScope.lists[0] }<br>
    
    ${requestScope.allDatas[0].location_name }
    
    
    
    
    
</body>
</html>