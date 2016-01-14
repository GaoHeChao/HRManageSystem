<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="950" border="0" align="center" cellpadding="0"
	cellspacing="0">
          <tr>
            <td height="80" bgcolor="#FFFFFF"><img src="<%=path%>/img/bannal.jpg" width="950"
				height="80"> </td>
          </tr>
          <tr>
            <td height="24" align="right" bgcolor="#FFFFFF">
            <a href="admin/default.jsp">首页</a> 
            <a href="<%=path%>/QueryDeptOnPageServlet">部门查询</a> 
            <a href="<%=path%>/QueryJobsServlet">职务查询</a> 
            <a href="<%=path %>/QueryEmpsServlet">员工查询</a> 
            <a href="<%=path %>/LogoutServlet">退出登陆</a> </td>
          </tr>
          <%
          String adlevel = "";
          String name = session.getAttribute("sname").toString();
          String level = session.getAttribute("slevel").toString();
          if(name != null && level.equals("1")){
        	  adlevel = "管理员";
          }else{
        	  adlevel = "操作员";
          }
          %>
          <tr>
            <td height="24" align="right" bgcolor="#0099CC"> 当前用户：<%=name %> 身份:<%=adlevel %> </td>
          </tr>
        </table>
</body>
</html>